package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Unlocked extends DoorState {
  private static final Logger LOGGER = LoggerFactory.getLogger(Unlocked.class);


  public Unlocked(Door door) {
    super(door, "unlocked");
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      LOGGER.info("Door " + door.getId() + " is now open.");
    } else {
      LOGGER.warn("Door " + door.getId() + " is already open.");
    }
  }

  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      LOGGER.info("Door " + door.getId() + " is now closed.");
    } else {
      LOGGER.warn("Door " + door.getId() + " is already closed.");
    }
  }

  @Override
  public void lock() {
    if (door.isClosed()) {
      door.setState(new Lock(door));
      LOGGER.info("Door " + door.getId() + " is now locked.");
    } else {
      LOGGER.warn("Cannot lock the door "+ door.getId() +  " because it's open.");
    }
  }

  @Override
  public void unlock() {
    LOGGER.warn("Cannot unlock the door " + door.getId() + " because it's already unlocked.");
  }

  @Override
  public void unlock_shortly() {
    LOGGER.warn("Cannot unlock shortly the door " + door.getId() + " because it's already unlocked.");
  }
}
