package baseNoStates;

import java.util.ArrayList;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Area {
  private String id;
  // List of doors that each area has.
  // Defined as protected so subclasses can access it.
  protected ArrayList<Door> doors;
  private static final Logger LOGGER = LoggerFactory.getLogger(Area.class);

  // Default constructor that initializes the area ID and creates an empty list of doors.
  public Area(String id) {
    this.id = id;
    this.doors = new ArrayList<>();
  }

  // Abstract method to find an area by its ID.
  public abstract Area findAreaById(String id);

  public String getId() {
    return id;
  }

  // Abstract method to get all spaces associated with the area.
  public abstract ArrayList<Space> getSpaces();

  // Abstract method to get all doors that give access to other areas or spaces.
  public abstract ArrayList<Door> getDoorsGivingAccess();

  public void setDoors(ArrayList<Door> doors) {
    this.doors = doors;
  }

  @Override
  public String toString() {
    return "Area(" + ", id=" + id + '\'' + ")";
  }

  // Converts the area to a JSON object.
  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    return json;
  }

  public abstract void accept(Visitor visitor);
}