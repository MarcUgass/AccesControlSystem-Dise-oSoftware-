package baseNoStates.Fita2;

import baseNoStates.Fita1.Partition;
import baseNoStates.Fita1.Space;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interface that implements the Visitor pattern to traverse the composite structure
 * of spaces and partitions.
 */
public interface Visitor {
  /** Logger for the Visitor class */
  static final Logger LOGGER = LoggerFactory.getLogger(Visitor.class);

  /**
   * Visit a Space object
   * @param space The Space object to visit
   */
  void visitSpace(Space space);

  /**
   * Visit a Partition object
   * @param partition The Partition object to visit
   */
  void visitPartition(Partition partition);
}
