package baseNoStates;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Area {
  private String id;
  // Llista de portes que té cada àrea.
  // Es defineix com a protected perquè les subclasses puguin accedir-hi.
  protected ArrayList<Door> doors;

  // Constructor per defecte que inicialitza l'ID de l'àrea i crea una llista buida de portes.
  public Area(String id) {
    this.id = id;
    this.doors = new ArrayList<>();
  }

  // Mètode abstracte per buscar una àrea pel seu ID.
  public abstract Area findAreaById(String id);

  public String getId() {
    return id;
  }

  // Mètode abstracte per obtenir totes les espais associats a l'àrea.
  public abstract ArrayList<Space> getSpaces();

  // Mètode abstracte per obtenir totes les portes que donen accés a altres àrees o espais.
  public abstract ArrayList<Door> getDoorsGivingAccess();

  public void setDoors(ArrayList<Door> doors) {
    this.doors = doors;
  }

  @Override
  public String toString() {
    return "Area(" + ", id=" + id + '\'' + ")";
  }

  // Converteix l'àrea en un objecte JSON.
  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    return json;
  }

  public abstract void accept(Visitor visitor);
}