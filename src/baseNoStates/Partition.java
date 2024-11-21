package baseNoStates;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Class that represents a partition (composite area).
 * A partition can contain other sub-areas and spaces.
 */
public class Partition extends Area {
  // List of sub-areas (children) associated with this partition
  private ArrayList<Area> childs = new ArrayList<>();
  private Partition partition;

  private static final Logger LOGGER = LoggerFactory.getLogger(Partition.class);

  public Partition(String id, Partition parent) { //constructor
    super(id);
    this.partition = parent;
  }

  /*
  //Function that searches for an area by its identifier
  @Override
  public Area findAreaById(String id) {
    if (this.getId().equals(id)) {
      return this;  //if the id matches, returns this partition
    }
    for (Area area : childs) { //if it doesn't match, look in all sub-areas
      Area found = area.findAreaById(id);
      if (found != null) {
        return found;
      }
    }
    //if nothing is found, returns null
    return null;
  }
  */


  //Gets all spaces contained in this partition and its sub-areas
  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : childs) {
      spaces.addAll(area.getSpaces());
    }
    return spaces;
  }


  //Getter that looks at all zones for doors that give access to them
  @Override
  public ArrayList<baseNoStates.Door> getDoorsGivingAccess() {
    ArrayList<baseNoStates.Door> doors = new ArrayList<>();
    for (Area area : childs) {
      doors.addAll(area.getDoorsGivingAccess());
    }
    return doors;
  }
  public ArrayList<Area> getChilds() {
    return childs;
  }

  //Add a new sub-area to the partition
  public void addChild(Area area) {
    childs.add(area);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitPartition(this);
    for (Area area : childs) {
      area.accept(visitor); // Recursively visit sub-areas
    }
  }
}
