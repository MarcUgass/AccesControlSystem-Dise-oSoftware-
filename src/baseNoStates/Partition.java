package baseNoStates;

import java.util.ArrayList;

/*
 * Classe que representa una partició (àrea composta).
 * Una partició pot contenir altres sub-àrees i espais.
 */
public class Partition extends Area {
  // Llista de sub-àrees (fills) associades a aquesta partició.
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

  //Obté tots els espais continguts en aquesta partició i les seves sub-àrees.
  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : childs) {
      spaces.addAll(area.getSpaces());
    }
    return spaces;
  }

  //getter que mira de totes les zones les portes que hi tenen accés
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();
    for (Area area : childs) {
      doors.addAll(area.getDoorsGivingAccess());
    }
    return doors;
  }

  public ArrayList<Area> getChilds() {
    return childs;
  }

  //afegir una nova sub-àrea a la partició

  public void addChild(Area area) {
    childs.add(area);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
    for (Area area : childs) {
      area.accept(visitor); // Recursivamente visita las sub-áreas
    }
  }
}
