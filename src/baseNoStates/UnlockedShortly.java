package baseNoStates;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class UnlockedShortly extends DoorState implements Observer {

  public UnlockedShortly(Door door) {
    super(door, "unlocked_shortly");
    ClockTimer timer = ClockTimer.getInstance();
    timer.addObserver(this);
    timer.startTimer();
  }

  // private void startTimer() {
  //  Timer timer = new Timer();
  //   timer.schedule(new TimerTask() {
  //     @Override
  //    public void run() {
  //     checkDoorState();
  //   }
  //   }, 10000);
  //  }


  private void checkDoorState() {
    if (door.isClosed()) {
      door.setState(new Lock(door));
      System.out.println("Door " + door.getId() + " is now closed.");
    } else {
      door.setState(new Propped(door));
      System.out.println("Door " + door.getId() + " is still unlocked.");
    }
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
      door.setState(new Propped(door));
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
    door.setState(new Unlocked(door));
    System.out.println("Door " + door.getId() + " is now unlocked.");
  }

  @Override
  public void unlock_shortly() {
    System.out.println("Door " + door.getId() + " is already unlocked.");
  }

  @Override
  public void update(Observable o, Object arg) {
    if (door.isClosed()) {
      door.setState(new Lock(door));
      System.out.println("Door " + door.getId() + " is now closed.");
    } else {
      door.setState(new Propped(door));
      System.out.println("Door " + door.getId() + " is still unlocked.");
    }
  }
}
