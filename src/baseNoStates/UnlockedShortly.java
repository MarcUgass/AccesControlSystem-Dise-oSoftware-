package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;
import java.util.Observer;

/**
 * Class that represents the "unlocked shortly" state of a door.
 * In this state, the door is unlocked for a short period of time.
 */
public class UnlockedShortly extends DoorState implements Observer {
  private final static Logger LOGGER = LoggerFactory.getLogger(UnlockedShortly.class);

  public UnlockedShortly(Door door) {
    super(door, "unlocked_shortly");
    ClockTimer timer = ClockTimer.getInstance();
    timer.addObserver(this);
    timer.startTimer();
  }

  /**
   * Checks the current state of the door and decides the next state
   */
  private void checkDoorState() {
    if (door.isClosed()) {
      LOGGER.info("Door " + door.getId() + " is now closed.");
      door.setState(new Lock(door));
    } else {
      LOGGER.info("Door " + door.getId() + " is still unlocked.");
      door.setState(new Propped(door));
    }
  }

  /**
   * Opens the door
   */
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
      door.setState(new Propped(door));
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
      LOGGER.warn("Cannot lock the door " + door.getId() + " because it's open.");
    }
  }

  @Override
  public void unlock() {
    LOGGER.info("Door " + door.getId() + " is now unlocked.");
    door.setState(new Unlocked(door));
  }

  @Override
  public void unlock_shortly() {
    LOGGER.warn("Cannot unlock shortly the door " + door.getId() + " because it's already unlocked.");
  }

  /**
   * Updates the state of the door based on the current time
   * @param o Observable object
   * @param arg Object argument
   */
  @Override
  public void update(Observable o, Object arg) {
    if (door.isClosed()) {
      LOGGER.info("Door " + door.getId() + " is now closed.");
      door.setState(new Lock(door));
    } else {
      LOGGER.info("Door " + door.getId() + " is still unlocked.");
      door.setState(new Propped(door));
    }
  }
}
