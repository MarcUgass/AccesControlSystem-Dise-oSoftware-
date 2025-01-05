package baseNoStates.Fita1;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class ClockTimer extends Observable {
  /*
   * Class responsible for managing a 10 second timer
   * to lock after the UnlockShortly state.
   * Implements Observer and Singleton design patterns.
   */
  private int seconds;
  // Single instance of ClockTimer to implement Singleton pattern.
  private static ClockTimer clockInstance = null;

  // Private constructor to prevent creation of external instances.
  private ClockTimer() {
    this.seconds = 0;
    startTimer();
  }

  /*
   * Starts a timer with 10 second intervals.
   * After 10 seconds, observers are notified.
   */
  public void startTimer() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        seconds += 10;
        if (seconds >= 10) {
          timer.cancel(); // Stops the timer after 10 seconds.
        }
        setChanged();
        notifyObservers(seconds); // Notifies observers with updated time.
      }
    }, 10000, 10000);
  }

  // Returns the single instance of ClockTimer (Singleton pattern).
  public static ClockTimer getInstance() {
    if (clockInstance == null) {
      clockInstance = new ClockTimer();
    }
    return clockInstance;
  }
}