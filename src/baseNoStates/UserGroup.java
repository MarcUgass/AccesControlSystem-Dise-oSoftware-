package baseNoStates;

import java.util.ArrayList;

public class UserGroup {
  private final String id;
  private final Schedule schedule;
  private final ArrayList<String> actions;
  private final ArrayList<Area> areas;
  private final ArrayList<User> users;

  public UserGroup(String id, Schedule schedule, ArrayList<String> actions,
                   ArrayList<Area> areas, ArrayList<User> users) {
    this.id = id;
    this.schedule = schedule;
    this.actions = actions;
    this.areas = areas;
    this.users = users;
  }

  //all getters
  public ArrayList<User> getUsers() {
    return users;
  }

  public ArrayList<Area> getAreas() {
    return areas;
  }

  public ArrayList<String> getActions() {
    return actions;
  }

  public Schedule getSchedule() {
    return schedule;
  }
}
