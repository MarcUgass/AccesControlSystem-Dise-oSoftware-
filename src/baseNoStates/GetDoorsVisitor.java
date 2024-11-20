package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;


public class GetDoorsVisitor implements Visitor {
  private ArrayList<Door> doors = new ArrayList<>();

  public ArrayList<Door> getDoors() {
    return doors;
  }

  @Override
  public void visit(Space space) {
    doors.addAll(space.getDoorsGivingAccess());
  }

  @Override
  public void visit(Partition partition) {
    for (Area area : partition.getChilds()) {
      area.accept(this); // Hacer el recorrido recursivo
    }
  }
}

