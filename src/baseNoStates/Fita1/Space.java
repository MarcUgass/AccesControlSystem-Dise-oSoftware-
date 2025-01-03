package baseNoStates.Fita1;

import baseNoStates.Fita2.Visitor;

import java.util.ArrayList;

public class Space extends Area {
  private Partition parent;
  private ArrayList<Door> doors = new ArrayList<>();

  public Space(String id, Partition parent) {
    super(id);
    this.parent = parent;
  }

  // Gets the list of doors associated with this space
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doors; // returns the doors associated with this space
  }

  public ArrayList<Door> getDoors() {
    return doors; // returns the doors associated with this space
  }

  public void addDoor(Door door) {
    doors.add(door);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitSpace(this);
  }
}
