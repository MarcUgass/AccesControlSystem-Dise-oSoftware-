package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
/*
 * Classe per gestionar els grups d'usuaris.
 * Cada grup té un horari, accions permeses, zones accessibles i una llista d'usuaris.
 */

public class DirectoryUsersGroups {
  private static final ArrayList<UserGroup> userGroups = new ArrayList<>();

  private static final Integer ONE = 1;
  private static final Integer YEAR = 2024;

  //Crea els grups d'usuaris i els afegeix al directori.
  public static void makeUsersGroups() {
    // Employees
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

    // Managers
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

    // Admin
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
  }

  // we search a concrete user by his credential
  // returns a User object if it finds it
  public static User findUserByCredential(final String credential) {
    for (UserGroup userGroup : userGroups) {
      for (User user : userGroup.getUsers()) {
        if (user.getCredential().equals(credential)) {
          return user;
        }
      }
    }
    return null;
  }

  // we search a concrete userGroup by a user that belongs to it
  // returns a UserGroup object if it finds it
  public static UserGroup findUserGroupByUser(final String credential) {
    for (UserGroup userGroup : userGroups) {
      for (User user : userGroup.getUsers()) {
        if (user.getCredential().equals(credential)) {
          return userGroup;
        }
      }
    }
    return null;
  }
}
