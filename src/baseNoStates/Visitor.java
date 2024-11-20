package baseNoStates;

public interface Visitor {
  void visit(Space space);
  void visit(Partition partition);
}
