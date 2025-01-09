import 'package:flutter/material.dart';
import 'package:flutterapp/tree.dart';
import 'package:flutterapp/screen_space.dart';
import 'package:flutterapp/requests.dart';

class ScreenPartition extends StatefulWidget {
  final String id;

  const ScreenPartition({super.key, required this.id});

  @override
  State<ScreenPartition> createState() => _ScreenPartitionState();
}

class _ScreenPartitionState extends State<ScreenPartition> {
  late Future<Tree> futureTree;

  @override
  void initState() {
    super.initState();
    // Initialize the future to fetch tree data
    futureTree = getTree(widget.id);
  }

  // Building the UI with FutureBuilder to fetch and display data
  @override
  Widget build(BuildContext context) {
    return FutureBuilder<Tree>(
      future: futureTree,
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          // Get room and door counts from the tree data
          Map<String, int> counts = snapshot.data!.countRoomsAndDoors();
          return Scaffold(
            appBar: AppBar(
              backgroundColor: Theme.of(context).colorScheme.primary,
              foregroundColor: Theme.of(context).colorScheme.onPrimary,
              title: Text("${snapshot.data!.root.id}"),
              actions: <Widget>[
                IconButton(
                  icon: const Icon(Icons.home),
                  onPressed: () {
                    Navigator.of(context).pushAndRemoveUntil(
                      MaterialPageRoute(builder: (context) => const ScreenPartition(id: "building")),
                          (Route<dynamic> route) => false, // Clear the navigation stack
                    );
                  },
                ),
                // Display the room and door count in the app bar
                Padding(
                  padding: const EdgeInsets.only(right: 16),
                  child: Center(
                    child: Text(
                      'Rooms: ${counts['rooms']} | Doors: ${counts['doors']}',
                      style: const TextStyle(fontSize: 16),
                    ),
                  ),
                ),
              ],
            ),
            body: ListView.separated(
              padding: const EdgeInsets.all(16.0),
              itemCount: snapshot.data!.root.children.length,
              itemBuilder: (BuildContext context, int i) =>
                  _buildRow(snapshot.data!.root.children[i], i), // Build list item for each child
              separatorBuilder: (BuildContext context, int index) => const Divider(),
            ),
          );
        } else if (snapshot.hasError) {
          // Error handling if the request fails
          return Text("${snapshot.error}");
        }
        // Show a loading spinner while waiting for the data
        return Container(
            height: MediaQuery.of(context).size.height,
            color: Colors.white,
            child: Center(
              child: CircularProgressIndicator(),
            ));
      },
    );
  }

  // Helper function to build each row in the list
  Widget _buildRow(Area area, int index) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Expanded(
          child: ListTile(
            title: Text(
              area is Partition ? 'Partition ${area.id}' : 'Space ${area.id}',
              style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            onTap: () {
              // Navigate to the next screen based on the type of area (Partition or Space)
              if (area is Partition) {
                _navigateDownPartition(area.id);
              } else {
                _navigateDownSpace(area.id);
              }
            },
          ),
        ),
        // Lock/Unlock button for each area
        ElevatedButton.icon(
          icon: Icon(area.locked ? Icons.lock : Icons.lock_open),
          label: Text(area.locked ? "Unlock" : "Lock"),
          style: ElevatedButton.styleFrom(
            backgroundColor: area.locked ? Colors.orange : Colors.blue, // Change color based on locked state
          ),
          onPressed: () async {
            // Perform lock or unlock action based on the current state
            if (area.locked) {
              await unlockArea(area);
            } else {
              await lockArea(area);
            }
            setState(() {
              area.locked = !area.locked; // Update local state after action
            });
          },
        ),
      ],
    );
  }

  // Navigate to the child partition screen
  void _navigateDownPartition(String childId) {
    Navigator.of(context)
        .push(MaterialPageRoute<void>(builder: (context) => ScreenPartition(id: childId,)));
  }

  // Navigate to the child space screen
  void _navigateDownSpace(String childId) {
    Navigator.of(context)
        .push(MaterialPageRoute<void>(builder: (context) => ScreenSpace(id: childId,)));
  }
}
