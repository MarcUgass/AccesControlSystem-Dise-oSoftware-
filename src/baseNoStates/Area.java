package baseNoStates;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Area {
  private String id;
  // llista de portes que té cada area,
  // s'utilitza protected, ja que les subclases poden accedir-hi
  protected ArrayList<Door> doors;

  public Area(String id) {  //constructor per defecte
    this.id = id;
    this.doors = new ArrayList<>();
  }

  public abstract Area findAreaById(String id);

  public String getId() {
    return id;
  }

  public abstract ArrayList<Space> getSpaces();

  public abstract ArrayList<Door> getDoorsGivingAccess();

  public void setDoors(ArrayList<Door> doors) {
    this.doors = doors;
  }


  @Override
  public String toString() {
    return "Area(" + ", id=" + id + '\'' + ")";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    return json;
  }
}
