package baseNoStates.Fita1;


import java.util.ArrayList;
import java.util.Arrays;

import baseNoStates.Fita2.FindAreaByIdVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryAreas {
  public static Area root; // Root of the areas tree
  private static ArrayList<Door> alldoors; // List of all doors
  private static ArrayList<Area> allareas; // List of all areas
  private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryAreas.class);

  // Creates the areas tree according to the statement
  public static void makeAreas() {
    Partition building = new Partition("building", null);
    Partition basement = new Partition("basement", building);
    Partition groundFloor = new Partition("ground_floor", building); // Changed to camelCase
    Partition floor1 = new Partition("floor1", building);
    building.addChild(basement);
    building.addChild(groundFloor); // Added groundFloor to hierarchy
    building.addChild(floor1);

    // Creation of spaces associated with each partition
    final Space parking = new Space("parking", basement);
    final Space hall = new Space("hall", groundFloor);
    final Space room1 = new Space("room1", groundFloor);
    final Space room2 = new Space("room2", groundFloor);
    final Space room3 = new Space("room3", floor1);
    final Space corridor = new Space("corridor", floor1);
    final Space itRoom = new Space("IT", floor1); // Renamed to itRoom
    final Space stairs = new Space("stairs", building);
    final Space exterior = new Space("exterior", building);

    // Assignment of subspaces to corresponding partitions
    basement.addChild(parking);
    groundFloor.addChild(hall);
    groundFloor.addChild(room1);
    groundFloor.addChild(room2);
    floor1.addChild(room3);
    floor1.addChild(corridor);
    floor1.addChild(itRoom);
    building.addChild(stairs);
    building.addChild(exterior);

    root = building; // Assigns the root of the tree

    LOGGER.debug("Assignment of subspaces to corresponding partitions");

    // Creation of doors between spaces
    final Door d1 = new Door("D1", exterior, parking);
    final Door d2 = new Door("D2", stairs, parking);
    final Door d3 = new Door("D3", exterior, hall);
    final Door d4 = new Door("D4", stairs, hall);
    final Door d5 = new Door("D5", hall, room1);
    final Door d6 = new Door("D6", hall, room2);
    final Door d7 = new Door("D7", stairs, corridor);
    final Door d8 = new Door("D8", corridor, room3);
    final Door d9 = new Door("D9", corridor, itRoom);

    LOGGER.debug("Creation of doors between spaces");

    // Assignment of doors to each space
    parking.addDoor(d1);
    parking.addDoor(d2);
    hall.addDoor(d3);
    hall.addDoor(d4);
    hall.addDoor(d5);
    hall.addDoor(d6);
    room1.addDoor(d5);
    room2.addDoor(d6);
    room3.addDoor(d8);
    corridor.addDoor(d7);
    corridor.addDoor(d8);
    corridor.addDoor(d9);
    itRoom.addDoor(d9);
    stairs.addDoor(d2);
    stairs.addDoor(d4);
    stairs.addDoor(d7);
    exterior.addDoor(d1);
    exterior.addDoor(d3);

    LOGGER.debug("Assignment of doors to each space");

    // Initialize lists of doors and areas
    alldoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
    allareas = new ArrayList<>(Arrays.asList(
        building, basement, groundFloor, floor1, parking, hall, room1,
        room2, room3, corridor, itRoom, stairs, exterior
    ));

    LOGGER.debug("Initialization of lists of doors and areas");

  }

  // Searches for an area by its ID within the areas tree
  /*
  Fita2
  public static Area findAreaById(String id) {
    if (id == "building") {
      LOGGER.info("Area with id " + id + " found");
      return root;
    }
    FindAreaByIdVisitor visitor = new FindAreaByIdVisitor(id);
    root.accept(visitor);
    return visitor.getFoundArea();
  }
   */

  //Fita3
  public static Area findAreaById(String id) {
    if (id.equals("ROOT")) {
      // Special id that means that the wanted area is the root.
      // This is because the Flutter app client doesn't know the
      // id of the root, differently from the simulator
      LOGGER.info("Area with id " + id + " found");
      return root;
    } else {
      FindAreaByIdVisitor visitor = new FindAreaByIdVisitor(id);
      root.accept(visitor);
      return visitor.getFoundArea();
    }
  }

  // Searches for a door by its ID within the doors list
  public static Door findDoorById(String id) {
    for (Door door : alldoors) {
      if (door.getId().equals(id)) {
        LOGGER.info("door with id " + id + " found");
        return door;
      }
    }
    LOGGER.warn("door with id " + id + " not found");
    return null;
  }

  // Returns the list of all doors (updates if necessary)
  public static ArrayList<Door> getAllDoors() {
    LOGGER.info("Returning all doors");
    return alldoors;
  }
}
