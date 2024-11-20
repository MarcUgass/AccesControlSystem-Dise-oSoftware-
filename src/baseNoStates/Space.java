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

  // Returns the area if its identifier matches the given one, or null otherwise
  @Override
  public Area findAreaById(String id) {
    if (this.getId().equals(id)) {
      return this;
    }
    return null;
  }
  // Gets a list containing this space, since there are no sub-areas

  @Override
  public ArrayList<Space> getSpaces() {
    return new ArrayList<>(Arrays.asList(this));
  }
  // Gets the list of doors associated with this space

  @Override
  public ArrayList<baseNoStates.Door> getDoorsGivingAccess() {
    return doors; // returns the doors associated with this space
  }

  public void addDoor(baseNoStates.Door door) {
    doors.add(door);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
