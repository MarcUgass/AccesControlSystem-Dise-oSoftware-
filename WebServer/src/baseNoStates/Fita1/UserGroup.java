package baseNoStates.Fita1;

import java.util.ArrayList;

/**
 * Class that represents a user group with an id, schedule, actions, areas and users.
 */
public class UserGroup {
  private final String id;
  private final Schedule schedule;
  private final ArrayList<String> actions;
  private final ArrayList<Area> areas;
  private final ArrayList<User> users;

  /**
   * Constructor for the UserGroup class
   * @param id Id of the user group
   * @param schedule Schedule of the user group
   * @param actions Actions of the user group
   * @param areas Areas of the user group
   * @param users Users of the user group
   */
  public UserGroup(String id, Schedule schedule, ArrayList<String> actions,
                   ArrayList<Area> areas, ArrayList<User> users) {
    this.id = id;
    this.schedule = schedule;
    this.actions = actions;
    this.areas = areas;
    this.users = users;
  }

  /**
   * Getter for the users
   * @return Users of the user group
   */
  public ArrayList<User> getUsers() {
    return users;
  }

  /**
   * Getter for the areas
   * @return Areas of the user group
   */
  public ArrayList<Area> getAreas() {
    return areas;
  }

  /**
   * Getter for the actions
   * @return Actions of the user group
   */
  public ArrayList<String> getActions() {
    return actions;
  }

  /**
   * Getter for the schedule
   * @return Schedule of the user group
   */
  public Schedule getSchedule() {
    return schedule;
  }
}
