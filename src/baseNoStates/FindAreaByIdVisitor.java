package baseNoStates;

public class FindAreaByIdVisitor implements Visitor {
  private String id;
  private Area foundArea;

  public FindAreaByIdVisitor(String id) {
    this.id = id;
  }

  public Area getFoundArea() {
    return foundArea;
  }

  @Override
  public void visit(Space space) {
    if (space.getId().equals(id)) {
      foundArea = space;
    }
  }

  @Override
  public void visit(Partition partition) {
    if (partition.getId().equals(id)) {
      foundArea = partition;
    }
  }
}
