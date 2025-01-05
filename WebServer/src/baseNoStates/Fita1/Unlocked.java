package baseNoStates.Fita1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that represents the unlocked state of a door.
 * In this state, the door can be opened, closed and locked.
 */
public class Unlocked extends DoorState {
  /** Logger for the Unlocked class */
  private static final Logger LOGGER = LoggerFactory.getLogger(Unlocked.class);

  /**
   * Constructor for the Unlocked class
   * @param door The door that is in this state
   */
  public Unlocked(Door door) {
    super(door, "unlocked");
  }

  /**
   * Opens the door if it is closed
   */
  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      LOGGER.info("Door " + door.getId() + " is now open.");
    } else {
      LOGGER.warn("Door " + door.getId() + " is already open.");
    }
  }

  /**
   * Closes the door if it is open
   */
  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      LOGGER.info("Door " + door.getId() + " is now closed.");
    } else {
      LOGGER.warn("Door " + door.getId() + " is already closed.");
    }
  }

  /**
   * Locks the door if it is closed
   */
  @Override
  public void lock() {
    if (door.isClosed()) {
      door.setState(new Lock(door));
      LOGGER.info("Door " + door.getId() + " is now locked.");
    } else {
      LOGGER.warn("Cannot lock the door "+ door.getId() +  " because it's open.");
    }
  }

  /**
   * Cannot unlock an already unlocked door
   */
  @Override
  public void unlock() {
    LOGGER.warn("Cannot unlock the door " + door.getId() + " because it's already unlocked.");
  }

  /**
   * Cannot unlock shortly an already unlocked door
   */
  @Override
  public void unlockShortly() {
    LOGGER.warn("Cannot unlock shortly the door " + door.getId()
        + " because it's already unlocked.");
  }
}
