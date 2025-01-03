package baseNoStates.Fita2;

import baseNoStates.Fita1.Area;
import baseNoStates.Fita1.Partition;
import baseNoStates.Fita1.Space;

import java.util.ArrayList;

/**
 * Visitor that collects all Space objects in an area hierarchy.
 * Implements the Visitor pattern to traverse and gather spaces.
 */
public class GetSpacesVisitor implements Visitor {
  // List to store all found spaces
  private final ArrayList<Space> spaces = new ArrayList<>();

  /**
   * Visits a Space and adds it to the collection
   * @param space The Space to visit and collect
   */
  @Override
  public void visitSpace(Space space) {
    spaces.add(space);
  }

  /**
   * Visits a Partition and recursively visits all contained areas
   * @param partition The Partition to visit
   */
  @Override
  public void visitPartition(Partition partition) {
    for (Area area : partition.getChilds()) {
      area.accept(this);
    }
  }

  /**
   * Returns the list of all spaces found during traversal
   * @return ArrayList containing all Space objects
   */
  public ArrayList<Space> getSpaces() {
    return spaces;
  }
}
