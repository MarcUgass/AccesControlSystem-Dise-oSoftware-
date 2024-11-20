package baseNoStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to manage user groups.
 * Each group has a schedule, allowed actions, accessible areas and a list of users.
 * The groups are: employees, managers and administrators, each with different permissions.
 */
public class DirectoryUsersGroups {
  private static final ArrayList<UserGroup> userGroups = new ArrayList<>();

  private static final Integer ONE = 1;
  private static final Integer YEAR = 2024;

  private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryUsersGroups.class);

  /**
   * Creates user groups and adds them to the directory.
   * Configures schedules, actions and areas for each group.
   */
  public static void makeUsersGroups() {
    // Employees group configuration
    // Access: Ground floor, floor1, exterior, stairs
    // Schedule: Sep 1 - Mar 1, Mon-Fri 9:00-17:00
    // Actions: Can only unlock shortly, open and close
    LocalDate startDateEmployee = LocalDate.of(YEAR, Month.SEPTEMBER, ONE);
    LocalDate endDateEmployee = LocalDate.of(YEAR, Month.MARCH, ONE);

    LocalTime startTimeEmployee = LocalTime.of(9, 0);
    LocalTime endTimeEmployee = LocalTime.of(17, 0);

    ArrayList<DayOfWeek> daysEmployee = new ArrayList<>(Arrays.asList(
        DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));

    Schedule scheduleEmployee = new Schedule(startDateEmployee, endDateEmployee,
        daysEmployee, startTimeEmployee, endTimeEmployee);

    ArrayList<String> actionsEmployee = new ArrayList<>(Arrays.asList(
        Actions.UNLOCK_SHORTLY, Actions.OPEN, Actions.CLOSE));

    ArrayList<Area> areasEmployee = new ArrayList<>(Arrays.asList(
        DirectoryAreas.findAreaById("ground_floor"),
        DirectoryAreas.findAreaById("floor1"),
        DirectoryAreas.findAreaById("exterior"),
        DirectoryAreas.findAreaById("stairs")));

    ArrayList<User> usersEmployee = new ArrayList<>(Arrays.asList(
        new User("Ernest", "74984"), new User("Eulalia", "43295")));

    UserGroup employee = new UserGroup("employee", scheduleEmployee,
        actionsEmployee, areasEmployee, usersEmployee);

    userGroups.add(employee);

    LOGGER.debug("Employee group created");

    // Managers group configuration
    // Access: All building areas
    // Schedule: Sep 1 - Mar 1 next year, Mon-Sat 8:00-20:00
    // Actions: All actions allowed
    LocalDate dateInicioManagers = LocalDate.of(YEAR, Month.SEPTEMBER, ONE);
    LocalDate dateFinManagers = LocalDate.of(2025, Month.MARCH, ONE);

    LocalTime timeInicioManagers = LocalTime.of(8, 0);
    LocalTime timeFinManagers = LocalTime.of(20, 0);

    ArrayList<DayOfWeek> daysManagers = new ArrayList<>(Arrays.asList(
        DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));

    Schedule scheduleManagers = new Schedule(dateInicioManagers, dateFinManagers,
        daysManagers, timeInicioManagers, timeFinManagers);

    ArrayList<String> actionsManagers = new ArrayList<>(Arrays.asList(
        Actions.OPEN, Actions.CLOSE, Actions.LOCK, Actions.UNLOCK,
        Actions.UNLOCK_SHORTLY));

    ArrayList<Area> areasManagers = new ArrayList<>(Arrays.asList(
        DirectoryAreas.findAreaById("building")));

    ArrayList<User> usersManagers = new ArrayList<>(Arrays.asList(
        new User("Manel", "95783"), new User("Marta", "05827")));

    UserGroup managers = new UserGroup("managers", scheduleManagers,
        actionsManagers, areasManagers, usersManagers);

    userGroups.add(managers);

    LOGGER.debug("Managers group created");

    // Admin group configuration
    // Access: All building areas
    // Schedule: Jan 1 2024 - Jan 1 2100, All days 00:00-23:59
    // Actions: All actions allowed
    LocalDate startDateAdmin = LocalDate.of(YEAR, Month.JANUARY, ONE);
    LocalDate endDateAdmin = LocalDate.of(2100, Month.JANUARY, ONE);

    LocalTime startTimeAdmin = LocalTime.of(0, 0);
    LocalTime endTimeAdmin = LocalTime.of(23, 59);

    ArrayList<DayOfWeek> daysAdmin = new ArrayList<>(Arrays.asList(
        DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,
        DayOfWeek.SUNDAY));

    Schedule scheduleAdmin = new Schedule(startDateAdmin, endDateAdmin,
        daysAdmin, startTimeAdmin, endTimeAdmin);

    ArrayList<String> actionsAdmin = new ArrayList<>(Arrays.asList(
        Actions.OPEN, Actions.CLOSE, Actions.LOCK, Actions.UNLOCK,
        Actions.UNLOCK_SHORTLY));

    ArrayList<Area> areasAdmin = new ArrayList<>(Arrays.asList(
        DirectoryAreas.findAreaById("building")));

    ArrayList<User> usersAdmin = new ArrayList<>(Arrays.asList(
        new User("Ana", "11343")));

    UserGroup admin = new UserGroup("admin", scheduleAdmin,
        actionsAdmin, areasAdmin, usersAdmin);

    userGroups.add(admin);

    LOGGER.debug("Admin group created");
  }

  /**
   * Searches for a specific user by their credential.
   * @param credential The credential to search for
   * @return User object if found, null otherwise
   */
  public static User findUserByCredential(final String credential) {
    for (UserGroup userGroup : userGroups) {
      for (User user : userGroup.getUsers()) {
        if (user.getCredential().equals(credential)) {
          LOGGER.info("User with credential " + credential + " found");
          return user;
        }
      }
    }
    LOGGER.warn("User with credential " + credential + " not found");
    return null;
  }

  /**
   * Searches for a specific userGroup by a user that belongs to it.
   * @param credential The credential of the user to search for
   * @return UserGroup object if found, null otherwise
   */
  public static UserGroup findUserGroupByUser(final String credential) {
    for (UserGroup userGroup : userGroups) {
      for (User user : userGroup.getUsers()) {
        if (user.getCredential().equals(credential)) {
          LOGGER.info("User group with user " + credential + " found");
          return userGroup;
        }
      }
    }
    LOGGER.warn("User group with user " + credential + " not found");
    return null;
  }
}
