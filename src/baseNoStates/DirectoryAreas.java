package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryAreas {
  public static Area root;
  private static ArrayList<Door> alldoors;
  private static ArrayList<Area> allareas;

  //funcio per construir l'arbre d'arees donat a l'enunciat
  public static void makeAreas() {
    Partition building = new Partition("building", null);
    Partition basement = new Partition("basement", building);
    Partition ground_floor = new Partition("ground_floor", building);
    Partition floor1 = new Partition("floor1", building);
    building.addChild(basement);
    building.addChild(ground_floor);
    building.addChild(floor1);

    Space parking = new Space("parking", basement);
    Space hall = new Space("hall", ground_floor);
    Space room1 = new Space("room1", ground_floor);
    Space room2 = new Space("room2", ground_floor);
    Space room3 = new Space("room3", floor1);
    Space corridor = new Space("corridor", floor1);
    Space IT = new Space("IT", floor1);
    Space stairs = new Space("stairs", building);
    Space exterior = new Space("exterior", building);

    basement.addChild(parking);

    ground_floor.addChild(hall);
    ground_floor.addChild(room1);
    ground_floor.addChild(room2);

    floor1.addChild(room3);
    floor1.addChild(corridor);
    floor1.addChild(IT);

    building.addChild(stairs);
    building.addChild(exterior);

    root = building;

    //basement
    Door d1 = new Door("D1", exterior, parking);
    Door d2 = new Door("D2", stairs, parking);
    //ground_floor
    Door d3 = new Door("D3", exterior, hall);
    Door d4 = new Door("D4", stairs, hall);
    Door d5 = new Door("D5", hall, room1);
    Door d6 = new Door("D6", hall, room2);
    //first floor
    Door d7 = new Door("D7", stairs, corridor);
    Door d8 = new Door("D8", corridor, room3);
    Door d9 = new Door("D9", corridor, IT);

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

    IT.addDoor(d9);
    stairs.addDoor(d2);
    stairs.addDoor(d4);
    stairs.addDoor(d7);
    exterior.addDoor(d1);
    exterior.addDoor(d3);



    alldoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
    allareas = new ArrayList<>(Arrays.asList(building, basement, ground_floor, floor1, parking, hall, room1, room2, room3, corridor, IT, stairs, exterior));
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

  //refresh request
  public static ArrayList<Door> getAllDoors() {
    System.out.println(alldoors);
    return alldoors;
  }

}
