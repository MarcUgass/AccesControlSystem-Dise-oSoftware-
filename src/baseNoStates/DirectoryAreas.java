package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryAreas {
  public static Area root;
  private static ArrayList<Door> alldoors;
  private static ArrayList<Area> allareas;

  // Funció per construir l'arbre d'árees donat a l'enunciat
  public static void makeAreas() {
    Partition building = new Partition("building", null);
    Partition basement = new Partition("basement", building);
    Partition groundFloor = new Partition("ground_floor", building); // canviat a camelCase
    Partition floor1 = new Partition("floor1", building);
    building.addChild(basement);
    building.addChild(groundFloor); // canviat aquí també
    building.addChild(floor1);

    final Space parking = new Space("parking", basement);
    final Space hall = new Space("hall", groundFloor); // declarada final
    final Space room1 = new Space("room1", groundFloor); // declarada final
    final Space room2 = new Space("room2", groundFloor); // declarada final
    final Space room3 = new Space("room3", floor1); // declarada final
    final Space corridor = new Space("corridor", floor1); // declarada final
    final Space itRoom = new Space("IT", floor1); // Renombrada a itRoom
    final Space stairs = new Space("stairs", building); // declarada final
    final Space exterior = new Space("exterior", building); // declarada final

    basement.addChild(parking);

    groundFloor.addChild(hall);
    groundFloor.addChild(room1);
    groundFloor.addChild(room2);

    floor1.addChild(room3);
    floor1.addChild(corridor);
    floor1.addChild(itRoom);

    building.addChild(stairs);
    building.addChild(exterior);

    root = building;

    // basement
    final Door d1 = new Door("D1", exterior, parking);
    final Door d2 = new Door("D2", stairs, parking);
    // groundFloor
    final Door d3 = new Door("D3", exterior, hall);
    final Door d4 = new Door("D4", stairs, hall);
    final  Door d5 = new Door("D5", hall, room1);
    final  Door d6 = new Door("D6", hall, room2);
    // first floor
    final Door d7 = new Door("D7", stairs, corridor);
    final Door d8 = new Door("D8", corridor, room3);
    final  Door d9 = new Door("D9", corridor, itRoom);

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

    alldoors = new ArrayList<>(Arrays.asList(
        d1, d2, d3, d4, d5, d6, d7, d8, d9
    ));
    allareas = new ArrayList<>(Arrays.asList(
        building, basement, groundFloor, floor1, parking, hall, room1,
        room2, room3, corridor, itRoom, stairs, exterior
    ));
  }

  public static Area findAreaById(String id) {
    return root.findAreaById(id);
  }

  public static Door findDoorById(String id) {
    for (Door door : alldoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null;
  }

  // Refresh request
  public static ArrayList<Door> getAllDoors() {
    System.out.println(alldoors);
    return alldoors;
  }
}
