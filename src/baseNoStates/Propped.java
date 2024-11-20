package baseNoStates;

//Classe que representa l'estat "propped" d'una porta (porta oberta sostinguda)
public class Propped extends DoorState {
  public Propped(Door door) {
    super(door, "propped");
  }

  @Override
  public void open() {
    System.out.println("Cannot open the door because it's already open.");
  }

  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      door.setState(new Lock(door));
      System.out.println("Door " + door.getId() + " is now closed.");
    } else {
      System.out.println("Door " + door.getId() + " is already closed.");
    }
  }

  @Override
  public void lock() {
    System.out.println("Cannot lock the door because it's open.");
  }

  @Override
  public void unlock() {
    System.out.println("Cannot unlock the door because it's already unlocked.");
  }

  @Override
  public void unlock_shortly() {
    System.out.println("Cannot unlock the door because it's already unlocked.");
  }
}
