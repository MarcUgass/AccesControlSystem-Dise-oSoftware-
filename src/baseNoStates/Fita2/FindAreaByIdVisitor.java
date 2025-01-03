package baseNoStates.Fita2;

import baseNoStates.Fita1.Area;
import baseNoStates.Fita1.Partition;
import baseNoStates.Fita1.Space;

/**
 * Visitor that finds an Area by its ID.
 * Implements the Visitor pattern to traverse the area hierarchy.
 */
public class FindAreaByIdVisitor implements Visitor {
  // ID of the area we're looking for
  private String id;
  // Reference to store the found area
  private Area foundArea;

  /**
   * Constructor that takes the ID to search for
   * @param id The ID of the area to find
   */
  public FindAreaByIdVisitor(String id) {
    this.id = id;
  }

  /**
   * Returns the found area after traversal
   * @return The area with matching ID, or null if not found
   */
  public Area getFoundArea() {
    LOGGER.info("Return area with id: " + id);
    return foundArea;
  }

  /**
   * Visits a Space and checks if its ID matches
   * @param space The Space to visit
   */
  @Override
  public void visitSpace(Space space) {
    if (space.getId().equals(id)) {
      foundArea = space;
    }
  }

  /**
   * Visits a Partition and checks if its ID matches.
   * If no match, recursively visits all contained spaces.
   * @param partition The Partition to visit
   */
  @Override
  public void visitPartition(Partition partition) {
    if (partition.getId().equals(id)) {
      foundArea = partition;
      LOGGER.info("Found area with id: " + id);
    } else {
      for (Area area : partition.getSpaces()) {
        area.accept(this);
        if (foundArea != null) {
          break;
        }
      }
    }
  }
}
