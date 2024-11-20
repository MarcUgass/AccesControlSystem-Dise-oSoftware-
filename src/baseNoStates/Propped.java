package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Class that represents the "propped" state of a door.
 * In this state, the door is open and cannot be locked until it is closed.
 */
public class Propped extends DoorState {
  private static final Logger LOGGER = LoggerFactory.getLogger(Propped.class);

  /**
   * Constructor for the Propped state
   * @param door The door that will be in this state
   */
  public Propped(Door door) {
    super(door, "propped");
  }

  /**
   * Cannot open a door that is already open
   */
  @Override
  public void open() {
    LOGGER.warn("Cannot open the door " + door.getId() + "because it's already open");
  }

  /**
   * Closes the door and changes its state to locked
   */
  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      door.setState(new Lock(door));
      LOGGER.info("Door " + door.getId() + " is now closed.");
    } else {
      LOGGER.warn("Door " + door.getId() + " is already closed.");
    }
  }

  /**
   * Cannot lock a door that is open
   */
  @Override
  public void lock() {
    LOGGER.warn("Cannot lock the door " + door.getId() + "because it's open");
  }

  /**
   * Cannot unlock a door that is already unlocked
   */
  @Override
  public void unlock() {
    LOGGER.warn("Cannot unlock the door " + door.getId() + "because it's already unlocked");
  }

  /**
   * Cannot temporarily unlock a door that is already unlocked
   */
  @Override
  public void unlock_shortly() {
    LOGGER.warn("Cannot unlock shortly the door " + door.getId() + "because it's already unlocked");
  }
}
