package baseNoStates.Fita1;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;
import org.slf4j.Logger;

/*
 * Class representing a door with dynamic state.
 * Uses the State pattern to manage actions like opening, closing or locking.
 */
public class Door {
  private final String id;
  private boolean closed;
  //boolean locked;
  private DoorState state; // Current door state (locked/unlocked)
  private Space fromSpace;  // Space where the door comes from
  private Space toSpace;  // Space where the door goes to

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Door.class);


  public Door(String id, Space fromSpace, Space toSpace) {
    this.id = id;
    closed = true;
    this.state = new Unlocked(this); // Initial state
    this.fromSpace = fromSpace;
    this.toSpace = toSpace;
  }

  /*
   * Processes a request for the door.
   * @param request The request to be processed.
   */
  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      LOGGER.warn("Request not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        state.open();
        break;
      case Actions.CLOSE:
        state.close();
        break;
      case Actions.LOCK:
        state.lock();
        break;
      case Actions.UNLOCK:
        state.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        state.unlockShortly();
        break;
      default:
        LOGGER.warn("Action " + action + " not implemented.");
    }
  }

  public Area getFromSpace() {
    return fromSpace;
  }

  public Area getToSpace() {
    return toSpace;
  }

  public boolean isClosed() {
    return closed;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public void setState(DoorState state) {
    this.state = state;
  }

  public String getStateName() {
    return state.getStateName();
  }

  public String getId() {
    return id;
  }


  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}