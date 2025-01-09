abstract class Area{
  late String id;
  late List<dynamic> children;
  bool locked = false;

  // Constructor to initialize the Area with an id and its children
  Area(this.id, this.children);
}

class Partition extends Area {
  // Constructor for Partition, which is a type of Area
  Partition(String id, List<Area> children) : super(id, children);
}

class Space extends Area {
  // Constructor for Space, which is also a type of Area but has Doors as children
  Space(String id, List<Door> children) : super(id, children);
}

class Door {
  late String id;
  late bool closed;
  late String state;

  // Constructor to initialize the Door with its properties
  Door({required this.id, this.state = "unlocked", this.closed = true});
}

// This class represents a tree structure of Areas and Doors
class Tree {
  late Area root;

  // Constructor that takes a Map representing the tree structure
  Tree(Map<String, dynamic> dec) {
    // Determine the type of root based on the 'class' field in the input map
    if (dec['class'] == "partition") {
      List<Area> children = <Area>[]; // List to hold child Areas
      // Iterate through the areas defined in the input map
      for (Map<String, dynamic> area in dec['areas']) {
        // If the area is a Partition, add it to the children list
        if (area['class'] == "partition") {
          children.add(Partition(area['id'], <Area>[]));
        // If the area is a Space, add it to the children list
        } else if (area['class'] == "space") {
          children.add(Space(area['id'], <Door>[]));
        } else {
          // If the area type is unknown, trigger an assertion error
          assert(false);
        }
      }
      // Set the root to a new Partition with the collected children
      root = Partition(dec['id'], children);
    } else if (dec['class'] == "space") {
      List<Door> children = <Door>[]; // List to hold child Doors
      // Iterate through the access doors defined in the input map
      for (Map<String, dynamic> d in dec['access_doors']) {
        // Add each door to the children list
        children.add(Door(id: d['id'], state: d['state'], closed: d['closed']));
      }
      // Set the root to a new Space with the collected doors
      root = Space(dec['id'], children);
    } else {
      // If the root type is unknown, trigger an assertion error
      assert(false);
    }
  }
}
