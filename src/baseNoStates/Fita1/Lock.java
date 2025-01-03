package baseNoStates.Fita1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
 * Class that represents the "locked" state of a door.
 * In this state, the door cannot be opened or unlocked directly without a specific action.
 */

public class Lock extends DoorState {
  private static final Logger LOGGER = LoggerFactory.getLogger(Lock.class);

  public Lock(Door door) {
    super(door, "locked");
  }

  @Override
  public void open() {
    LOGGER.warn("Cannot open the door " + door.getId() + "because it's locked.");
  }

  @Override
  public void close() {
    LOGGER.info("Cannot close the door " + door.getId() + " because it's already closed.");
  }

  @Override
  public void lock() {
    LOGGER.info("Cannot lock the door " + door.getId() + " because it's already locked.");
  }

  @Override
  public void unlock() {
    LOGGER.info("Door " + door.getId() + " is now unlocked.");
    door.setState(new Unlocked(door));
  }

  // This function can be implemented this way,
  // even though we need another one, because it can be called from another state
  @Override
  public void unlockShortly() {
    LOGGER.info("Door " + door.getId() + " is now unlocked shortly.");
    door.setState(new UnlockedShortly(door));
  }
}
