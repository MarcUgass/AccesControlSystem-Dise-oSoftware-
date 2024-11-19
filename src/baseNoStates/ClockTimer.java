package baseNoStates;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class ClockTimer extends Observable {
  /*
   * La classe ClockTimer té la responsabilitat de gestionar
   * el temporitzador de deu segons per bloquejar després de l'estat UnlockShortly.
   * Forma part dels patrons de disseny Observer i Singleton.
   */
  private int seconds;
  // Instància única de ClockTimer per implementar el patró Singleton.
  private static ClockTimer clockInstance = null;

  // Constructor privat per evitar la creació d'instàncies externes.
  private ClockTimer() {
    this.seconds = 0;
    startTimer();
  }

  /*
   * Després de deu segons, actualitza l'estat del temporitzador i
   * crida al mètode update dels observadors en notificar-los.
   */
  public void startTimer() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        seconds += 10;
        if (seconds >= 10) {
          timer.cancel();
        }
        setChanged();
        notifyObservers(seconds);
      }
    }, 10000, 10000);
    // Inicia el temporitzador amb un retard de 10 segons i intervals de 10 segons.
  }

  // Retorna l'única instància de ClockTimer (patró Singleton).
  public static ClockTimer getInstance() {
    if (clockInstance == null) {
      clockInstance = new ClockTimer();
    }
    return clockInstance;
  }
}
