package baseNoStates;

public abstract class DoorState {
  protected Door door;
  protected String name;

  /*
   * Classe abstracta que defineix els estats d'una porta.
   * Cada estat especifica el comportament de la porta per
   * a accions com obrir, tancar, bloquejar, etc.
   * Utilitza el patró de disseny State.
   */
  public DoorState(Door door, String name) {
    this.door = door;
    this.name = name;
  }
  //PATRO STATE

  public abstract void open();

  public abstract void close();

  public abstract void lock();

  public abstract void unlock();

  public abstract void unlock_shortly();

  public String getStateName() {
    return name;
  }
}
