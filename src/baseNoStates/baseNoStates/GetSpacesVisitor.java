package baseNoStates;

import java.util.ArrayList;

public class GetSpacesVisitor implements Visitor{
  private final ArrayList<Space> spaces = new ArrayList<>();

  @Override
  public void visitSpace(Space space) {
    spaces.add(space);
  }

  @Override
  public void visitPartition(Partition partition) {
    for (Area area : partition.getChilds()) {
      area.accept(this);
    }
  }

  public ArrayList<Space> getSpaces() {
    LOGGER.info("Class GetSpace, Returning spaces" + spaces);
    return spaces;
  }
}
