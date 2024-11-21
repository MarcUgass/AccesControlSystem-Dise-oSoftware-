package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Visitor {
  static final Logger LOGGER = LoggerFactory.getLogger(Visitor.class);

  void visitSpace(Space space);
  void visitPartition(Partition partition);
}
