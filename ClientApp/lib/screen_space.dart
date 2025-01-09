import 'package:flutter/material.dart';
import 'package:flutterapp/tree.dart';
import 'package:flutterapp/requests.dart';
import 'package:flutterapp/screen_partition.dart';


class ScreenSpace extends StatefulWidget {
  
  final String id;
  const ScreenSpace({super.key, required this.id});

  @override
  State<ScreenSpace> createState() => _ScreenSpaceState();
}

class _ScreenSpaceState extends State<ScreenSpace> {
  // Future variable to hold the tree data
  late Future<Tree> futureTree;

  @override
  void initState() {
    super.initState();
    // Initialize the futureTree with the tree data based on the widget's ID
    futureTree = getTree(widget.id);
  }

  /*
  late Tree tree;

  @override
  void initState() {
    super.initState();
    tree = getTree(widget.id);
  }
   */

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<Tree>(
      future: futureTree,
      builder: (context, snapshot) {
        // Check if the data is available
        if (snapshot.hasData) {
          return Scaffold(
            appBar: AppBar(
              // AppBar configuration
              backgroundColor: Theme.of(context).colorScheme.primary,
              foregroundColor: Theme.of(context).colorScheme.onPrimary,
              title: Text(snapshot.data!.root.id), // Display the root ID as the title
              actions: <Widget>[
                IconButton(
                  icon: const Icon(Icons.home),
                  onPressed: () {
                    // Navigate to the ScreenPartition when the home button is pressed
                    Navigator.of(context).pushAndRemoveUntil(
                      MaterialPageRoute(builder: (context) => const ScreenPartition(id: "building")),
                      (Route<dynamic> route) => false, // Remove all previous routes
                    );
                  },
                ),
                //TODO other actions
              ],
            ),
            body: ListView.separated(
              // ListView with separators between items
              padding: const EdgeInsets.all(16.0),
              itemCount: snapshot.data!.root.children.length, // Number of children in the tree
              itemBuilder: (BuildContext context, int i) =>
                  _buildRow(snapshot.data!.root.children[i], i), // Build each row
              separatorBuilder: (BuildContext context, int index) =>
              const Divider(), // Separator between items
            ),
          );
        } else if (snapshot.hasError) {
          // Display error message if there's an error
          return Text("${snapshot.error}");
        }
        // Default progress indicator while loading
        return Container(
            height: MediaQuery.of(context).size.height,
            color: Colors.white,
            child: Center(
              child: CircularProgressIndicator(),
            ));
      },
    );
  }

  // Method to build each row in the ListView
  Widget _buildRow(Door door, int index) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Expanded(
          child: Text(
            'Door ${door.id}', // Display the door ID
            style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
          ),
        ),
        // Button to unlock the door
        door.state == 'locked'
            ? ElevatedButton.icon(
          icon: const Icon(Icons.lock),
          label: const Text("Unlock"),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.blue, // Blue for unlocking
          ),
          onPressed: () async {
            await unlockDoor(door); // Call the unlock function
            setState(() => door.state = 'unlocked'); // Update the visual state
          },
        )
            : ElevatedButton.icon(
          icon: const Icon(Icons.lock_open),
          label: const Text("Lock"),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.orange, // Orange for locking
          ),
          onPressed: () async {
            await lockDoor(door); // Call the lock function
            setState(() => door.state = 'locked'); // Update the visual state
          },
        ),
        const SizedBox(width: 8), // Space between buttons
        // Button to open or close the door
        door.closed
            ? ElevatedButton.icon(
          icon: const Icon(Icons.door_front_door),
          label: const Text("Open"),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.green, // Green for opening
          ),
          onPressed: () async {
            await openDoor(door); // Call the open function
            setState(() => door.closed = false); // Update the visual state
          },
        )
            : ElevatedButton.icon(
          icon: const Icon(Icons.door_back_door),
          label: const Text("Close"),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.red, // Red for closing
          ),
          onPressed: () async {
            await closeDoor(door); // Call the close function
            setState(() => door.closed = true); // Update the visual state
          },
        ),
      ],
    );
  }
}
