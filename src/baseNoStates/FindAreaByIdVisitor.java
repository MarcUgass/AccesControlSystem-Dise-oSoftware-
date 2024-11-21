package baseNoStates;

public class FindAreaByIdVisitor implements Visitor {
  private String id;
  private Area foundArea;

  public FindAreaByIdVisitor(String id) {
    this.id = id;
  }

  public Area getFoundArea() {
    LOGGER.info("Return area with id: " + id);
    return foundArea;
  }

  @Override
  public void visitSpace(Space space) {
    if (space.getId().equals(id)) {
      foundArea = space;
    }
  }

  @Override
  public void visitPartition(Partition partition) {
    /*
    if (partition.getId().equals(id)) {
      foundArea = partition;
    }
    */
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
