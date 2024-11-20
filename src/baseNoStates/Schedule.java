package baseNoStates;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

// Classe que representa un horari amb dates, hores i dies de la setmana.

public class Schedule {
  private LocalDate dateInici;
  private LocalDate dateFin;
  private LocalTime timeInici;
  private LocalTime timeFin;
  private ArrayList<DayOfWeek> days;


  public Schedule(final LocalDate dateInitial, final LocalDate dateFinal,
                  final ArrayList<DayOfWeek> daysWeek,
                  final LocalTime timeInit, final LocalTime timeFinal) {
    this.dateInici = dateInitial;
    this.dateFin = dateFinal;
    this.days = daysWeek;
    this.timeInici = timeInit;
    this.timeFin = timeFinal;
  }

  public boolean isSchedule(final LocalDateTime now) {
    boolean daysTrue = days.contains(now.getDayOfWeek());

    boolean dateTrue = now.toLocalDate().isAfter(dateInici)
        && now.toLocalDate().isBefore(dateFin);

    boolean timeTrue = now.toLocalTime().isAfter(timeInici)
        && now.toLocalTime().isBefore(timeFin);

    return daysTrue && dateTrue && timeTrue;
  }

  public LocalDate getDateInici() {
    return dateInici;
  }

  public LocalDate getDateFin() {
    return dateFin;
  }

  public LocalTime getTimeInici() {
    return timeInici;
  }

  public LocalTime getTimeFin() {
    return timeFin;
  }

  public ArrayList<DayOfWeek> getDays() {
    return days;
  }
}