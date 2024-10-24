package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class Space extends Area{
  private Partition parent;
  private ArrayList<Door> doors = new ArrayList<>();

  public Space(String id, Partition parent) {
    super(id);
    this.parent = parent;
  }

//Space no te sub-arees, torna una llista buida
  @Override
  public Area findAreaById(String id) {
    if(this.getId().equals(id)){
      return this;  //si el id coincideix, retorna aquest espai
    }
    return null; //si no coincideix, no busca mes perque no conté sub-arees
  }

  @Override
  public ArrayList<Space> getSpaces() {
    return new ArrayList<>(Arrays.asList(this)); //retorna una llista buida ja que no te sub-arees
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doors; //retorna les portes associades a aquest space
  }

  public void addDoor(Door door) {
    doors.add(door);
  }

}
