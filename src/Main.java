// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

import baseNoStates.Fita1.DirectoryAreas;
import baseNoStates.Fita1.DirectoryUsersGroups;
import baseNoStates.Fita1.WebServer;

public class Main {
  public static void main(String[] args) {
    DirectoryAreas.makeAreas();
    DirectoryUsersGroups.makeUsersGroups();
    WebServer.getInstance();
  }
}