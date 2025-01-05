package baseNoStates.Fita1;

public abstract class DoorState {
  protected Door door;
  protected String name;

  /*
   * Abstract class that defines the states of a door.
   * Each state specifies the door's behavior for
   * actions like opening, closing, locking, etc.
   * Uses the State design pattern.
   */
  public DoorState(Door door, String name) {
    this.door = door;
    this.name = name;
  }
  // STATE PATTERN

  public abstract void open();

  public abstract void close();

  public abstract void lock();

  public abstract void unlock();

  public abstract void unlockShortly();

  public String getStateName() {
    return name;
  }
}
