package baseNoStates;

/*
 * Classe que representa l'estat "bloquejat" d'una porta.
 * En aquest estat, la porta no es pot obrir ni desbloquejar directament sense una acció específica.
 */

public class Lock extends DoorState {

  public Lock(Door door) {
    super(door, "locked");
  }

  @Override
  public void open() {
    System.out.println("Cannot open the door because it's locked.");
  }

  @Override
  public void close() {
    System.out.println("Door " + door.getId() + " is already closed.");
  }

  @Override
  public void lock() {
    System.out.println("Door " + door.getId() + " is already locked.");
  }

  @Override
  public void unlock() {
    door.setState(new Unlocked(door));
    System.out.println("Door " + door.getId() + " is now unlocked.");
  }

  //Aquesta funcio es pot implementar d'aquesta manera,
  // encara que necessitem una altre, perque es pot cirdar desde una altre state
  @Override
  public void unlock_shortly() {
    door.setState(new UnlockedShortly(door));
    System.out.println("Door " + door.getId() + " will be unlocked shortly.");
  }
}
