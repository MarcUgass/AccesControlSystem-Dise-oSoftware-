package baseNoStates.requests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import baseNoStates.Fita1.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  private static final Logger LOGGER = LoggerFactory.getLogger(RequestReader.class);

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void addReason(String reason) {
    reasons.add(reason);
  }


  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
            + "credential=" + credential
            + ", userName=" + userName
            + ", action=" + action
            + ", now=" + now
            + ", doorID=" + doorId
            + ", closed=" + doorClosed
            + ", authorized=" + authorized
            + ", reasons=" + reasons
            + "}";
  }

  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
  public void process() {
    User user = DirectoryUsersGroups.findUserByCredential(credential);
    Door door = DirectoryAreas.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";
    authorize(user, door);
    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing
  private void authorize(User user, Door door) {
    if (user == null) {
      authorized = false;
      addReason("user doesn't exists");
      LOGGER.warn("User doesn't exists");
    } else {
      //TODO: get the who, where, when and what in order to decide, and if not
      // authorized add the reason(s)
      UserGroup userGroup = DirectoryUsersGroups.findUserGroupByUser(user.getCredential());

      List<String> actions = userGroup.getActions();
      List<Area> areas = userGroup.getAreas();
      ArrayList<User> users = userGroup.getUsers();

      boolean isSchedule = userGroup.getSchedule().isSchedule(now);

      boolean actionsTrue = actions.contains(action);

      boolean areaTrue = false;

      if (areas.size() == 1 && areas.get(0).getId().equals("building")) {
        areaTrue = true;
        LOGGER.info("Area is building");
      } else {
        for (Area area : areas) {
          if (door.getFromSpace() == area || door.getToSpace() == area) {
            areaTrue = true;
            LOGGER.info("Area is " + area.getId());
          }
        }
      }

      if (areaTrue && isSchedule && actionsTrue) {
        authorized = true;
      } else {
        authorized = false;
        LOGGER.warn("User is not authorized" + " areaTrue: " + areaTrue + " isSchedule: "
            + isSchedule + " actionsTrue: " + actionsTrue);
      }
      //authorized = true;
    }
  }
}

