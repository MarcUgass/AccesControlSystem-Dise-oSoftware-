package baseNoStates;

import java.util.ArrayList;

public class Partition extends Area {
  //private String id;
  private ArrayList<Area> childs = new ArrayList<>();
  private Partition partition;

  public Partition(String id, Partition parent) { //constructor
    super(id);
    this.partition = parent;
  }

  //funcio que busca un àrea pel seu identificador
  @Override
  public Area findAreaById(String id) {
    if (this.getId().equals(id)) {
      return this;  //si el id coincideix, retorna aquesta particio
    }
    for (Area area : childs) { //si no coincideix, mira a totes les sub-arees
      Area found = area.findAreaById(id);
      if (found != null) {
        return found;
      }
    }
    //si no troba res, retorna null
    return null;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area: childs){
      spaces.addAll(area.getSpaces());
    }
    return spaces;
  }

  //getter que mira de totes les zones les portes que hi tenen accés
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();
    for (Area area: childs){
      doors.addAll(area.getDoorsGivingAccess());
    }
    return null;
  }


  public void addChild(Area area) {
    childs.add(area);
  }
}
