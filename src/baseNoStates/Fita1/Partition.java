package baseNoStates.Fita1;

import java.util.ArrayList;

import baseNoStates.Fita2.GetSpacesVisitor;
import baseNoStates.Fita2.Visitor;
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

  public Partition(String id, Partition parent) {
    super(id);
    this.partition = parent;
  }


  //Gets all spaces contained in this partition and its sub-areas
  public ArrayList<Space> getSpaces() {
    GetSpacesVisitor visitor = new GetSpacesVisitor();
    accept(visitor);
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : childs) {
      spaces.addAll(visitor.getSpaces());
    }
    return spaces;
  }


  //Getter that looks at all zones for doors that give access to them
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
