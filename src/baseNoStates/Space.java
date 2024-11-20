package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class Space extends Area {
  private Partition parent;
  private ArrayList<Door> doors = new ArrayList<>();

  public Space(String id, Partition parent) {
    super(id);
    this.parent = parent;
  }

  // Retorna l'àrea si el seu identificador coincideix amb l'indicat, o null en cas contrari.
  @Override
  public Area findAreaById(String id) {
    if (this.getId().equals(id)) {
      return this;
    }
    return null;
  }
  // Obté una llista que conté aquest espai, ja que no hi ha sub-àrees

  @Override
  public ArrayList<Space> getSpaces() {
    return new ArrayList<>(Arrays.asList(this));
  }
  // Obté la llista de portes associades a aquest espai.

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doors; //retorna les portes associades a aquest space
  }

  public void addDoor(Door door) {
    doors.add(door);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
