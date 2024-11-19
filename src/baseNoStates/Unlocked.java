package baseNoStates;

public class Unlocked extends DoorState {

  public Unlocked(Door door) {
    super(door, "unlocked");
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      System.out.println("Door " + door.getId() + " is now open.");
    } else {
      System.out.println("Door " + door.getId() + " is already open.");
    }
  }

  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      System.out.println("Door " + door.getId() + " is now closed.");
    } else {
      System.out.println("Door " + door.getId() + " is already closed.");
    }
  }

  @Override
  public void lock() {
    if (door.isClosed()) {
      door.setState(new Lock(door));
      System.out.println("Door " + door.getId() + " is now locked.");
    } else {
      System.out.println("Cannot lock the door because it's open.");
    }
  }

  @Override
  public void unlock() {
    System.out.println("Door " + door.getId() + " is already unlocked.");
  }

  @Override
  public void unlock_shortly() {
    System.out.println("Door " + door.getId() + " is already unlocked.");
  }
}
