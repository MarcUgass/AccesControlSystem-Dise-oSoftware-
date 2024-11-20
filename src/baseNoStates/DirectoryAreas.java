package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryAreas {
  public static Area root; // Arrel de l'arbre d'àrees.
  private static ArrayList<Door> alldoors; // Llista de totes les portes.
  private static ArrayList<Area> allareas; // Llista de totes les àrees.

  // Crea l'arbre d'àrees segons l'enunciat.
  public static void makeAreas() {
    Partition building = new Partition("building", null);
    Partition basement = new Partition("basement", building);
    Partition groundFloor = new Partition("ground_floor", building); // Canviat a camelCase.
    Partition floor1 = new Partition("floor1", building);
    building.addChild(basement);
    building.addChild(groundFloor); // Afegit groundFloor a la jerarquia.
    building.addChild(floor1);

    // Creació d'espais associats a cada partició.
    final Space parking = new Space("parking", basement);
    final Space hall = new Space("hall", groundFloor);
    final Space room1 = new Space("room1", groundFloor);
    final Space room2 = new Space("room2", groundFloor);
    final Space room3 = new Space("room3", floor1);
    final Space corridor = new Space("corridor", floor1);
    final Space itRoom = new Space("IT", floor1); // Renombrada a itRoom.
    final Space stairs = new Space("stairs", building);
    final Space exterior = new Space("exterior", building);

    // Assignació de subespais a les particions corresponents.
    basement.addChild(parking);
    groundFloor.addChild(hall);
    groundFloor.addChild(room1);
    groundFloor.addChild(room2);
    floor1.addChild(room3);
    floor1.addChild(corridor);
    floor1.addChild(itRoom);
    building.addChild(stairs);
    building.addChild(exterior);

    root = building; // Assigna l'arrel de l'arbre.

    // Creació de portes entre els espais.
    final Door d1 = new Door("D1", exterior, parking);
    final Door d2 = new Door("D2", stairs, parking);
    final Door d3 = new Door("D3", exterior, hall);
    final Door d4 = new Door("D4", stairs, hall);
    final Door d5 = new Door("D5", hall, room1);
    final Door d6 = new Door("D6", hall, room2);
    final Door d7 = new Door("D7", stairs, corridor);
    final Door d8 = new Door("D8", corridor, room3);
    final Door d9 = new Door("D9", corridor, itRoom);

    // Assignació de portes a cada espai.
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

    // Inicialitza les llistes de portes i àrees.
    alldoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
    allareas = new ArrayList<>(Arrays.asList(
        building, basement, groundFloor, floor1, parking, hall, room1,
        room2, room3, corridor, itRoom, stairs, exterior
    ));
  }

  // Busca una àrea pel seu ID dins de l'arbre d'àrees.
  public static Area findAreaById(String id) {
    return root.findAreaById(id);
  }

  // Busca una porta pel seu ID dins de la llista de portes.
  public static Door findDoorById(String id) {
    for (Door door : alldoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null;
  }

  // Retorna la llista de totes les portes (actualitza si cal).
  public static ArrayList<Door> getAllDoors() {
    System.out.println(alldoors);
    return alldoors;
  }
}
