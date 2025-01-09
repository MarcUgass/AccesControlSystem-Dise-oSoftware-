abstract class Area {
  late String id;
  late List<dynamic> children;
  bool locked = false; // Estado por defecto de bloqueo

  Area(this.id, this.children); // Constructor que inicializa el ID y los hijos
}

class Partition extends Area {
  Partition(String id, List<Area> children) : super(id, children); // Constructor de Partition
}

class Space extends Area {
  Space(String id, List<Door> children) : super(id, children); // Constructor de Space
}

class Door {
  late String id;
  late bool closed;
  late String state; // Estado (locked/unlocked)

  Door({required this.id, this.state = "unlocked", this.closed = true}); // Constructor de Door
}

// La clase Tree representa el árbol de áreas, que puede contener particiones y espacios
class Tree {
  late Area root;

  Tree(Map<String, dynamic> dec) {
    // Inicialización de la raíz según el tipo de clase que se define en dec
    if (dec['class'] == "partition") {
      List<Area> children = <Area>[];
      for (Map<String, dynamic> area in dec['areas']) {
        if (area['class'] == "partition") {
          children.add(Partition(area['id'], <Area>[])); // Si es partición, añadir como partición
        } else if (area['class'] == "space") {
          children.add(Space(area['id'], <Door>[])); // Si es espacio, añadir como espacio
        } else {
          assert(false); // Error si el tipo no es reconocido
        }
      }
      root = Partition(dec['id'], children); // Establecer la raíz como partición
    } else if (dec['class'] == "space") {
      List<Door> children = <Door>[];
      for (Map<String, dynamic> d in dec['access_doors']) {
        children.add(Door(id: d['id'], state: d['state'], closed: d['closed'])); // Añadir puertas al espacio
      }
      root = Space(dec['id'], children); // Establecer la raíz como espacio
    } else {
      assert(false); // Error si el tipo no es reconocido
    }
  }

  // Función para contar las habitaciones (particiones) y las puertas
  Map<String, int> countRoomsAndDoors() {
    int roomCount = 0;
    int doorCount = 0;

    // Función recursiva para contar en las particiones y espacios
    void count(Area area) {
      if (area is Partition) {
        roomCount++; // Contamos particiones
        for (var child in area.children) {
          count(child); // Llamada recursiva para contar dentro de particiones anidadas
        }
      } else if (area is Space) {
        roomCount++; // Contamos espacios
        doorCount += area.children.length; // Contamos puertas en cada espacio
      }
    }

    count(root); // Comienza el conteo desde la raíz
    return {'rooms': roomCount, 'doors': doorCount}; // Devuelve el conteo de habitaciones y puertas
  }
}
