package baseNoStates;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class that represents a schedule with dates, times and days of the week.
 */
public class Schedule {
  private LocalDate dateInici;
  private LocalDate dateFin;
  private LocalTime timeInici;
  private LocalTime timeFin;
  private ArrayList<DayOfWeek> days;

  /**
   * Constructor for the Schedule class
   * @param dateInitial Initial date
   * @param dateFinal Final date
   * @param daysWeek Days of the week
   * @param timeInit Initial time
   * @param timeFinal Final time
   */
  public Schedule(final LocalDate dateInitial, final LocalDate dateFinal,
                  final ArrayList<DayOfWeek> daysWeek,
                  final LocalTime timeInit, final LocalTime timeFinal) {
    this.dateInici = dateInitial;
    this.dateFin = dateFinal;
    this.days = daysWeek;
    this.timeInici = timeInit;
    this.timeFin = timeFinal;
  }

  /**
   * Checks if the current date and time matches the schedule
   * @param now Current date and time
   * @return True if the current date and time matches the schedule, false otherwise
   */
  public boolean isSchedule(final LocalDateTime now) {
    boolean daysTrue = days.contains(now.getDayOfWeek());

    boolean dateTrue = now.toLocalDate().isAfter(dateInici)
        && now.toLocalDate().isBefore(dateFin);

    boolean timeTrue = now.toLocalTime().isAfter(timeInici)
        && now.toLocalTime().isBefore(timeFin);

    return daysTrue && dateTrue && timeTrue;
  }

  /**
   * Getter for the initial date
   * @return Initial date
   */
  public LocalDate getDateInici() {
    return dateInici;
  }

  /**
   * Getter for the final date
   * @return Final date
   */
  public LocalDate getDateFin() {
    return dateFin;
  }

  /**
   * Getter for the initial time
   * @return Initial time
   */
  public LocalTime getTimeInici() {
    return timeInici;
  }

  /**
   * Getter for the final time
   * @return Final time
   */
  public LocalTime getTimeFin() {
    return timeFin;
  }

  /**
   * Getter for the days of the week
   * @return Days of the week
   */
  public ArrayList<DayOfWeek> getDays() {
    return days;
  }
}