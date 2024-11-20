package baseNoStates;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class ClockTimer extends Observable {
  /*
   * Classe responsable de gestionar un temporitzador de 10 segons
   * per bloquejar després de l'estat UnlockShortly.
   * Implementa els patrons de disseny Observer i Singleton.
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
   * Inicia un temporitzador amb intervals de 10 segons.
   * Després de 10 segons, es notifica als observadors.
   */
  public void startTimer() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        seconds += 10;
        if (seconds >= 10) {
          timer.cancel(); // Atura el temporitzador després dels 10 segons.
        }
        setChanged();
        notifyObservers(seconds); // Notifica els observadors amb el temps actualitzat.
      }
    }, 10000, 10000);
  }

  // Retorna l'única instància de ClockTimer (patró Singleton).
  public static ClockTimer getInstance() {
    if (clockInstance == null) {
      clockInstance = new ClockTimer();
    }
    return clockInstance;
  }
}