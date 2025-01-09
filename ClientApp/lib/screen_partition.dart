import 'package:flutter/material.dart';
import 'package:flutterapp/tree.dart';
import 'package:flutterapp/screen_space.dart';
import 'package:flutterapp/requests.dart';

class ScreenPartition extends StatefulWidget {
  // The unique identifier for the screen partition
  final String id;

  const ScreenPartition({super.key, required this.id});

  @override
  State<ScreenPartition> createState() => _ScreenPartitionState();
}

class _ScreenPartitionState extends State<ScreenPartition> {
  // Future variable to hold the tree data
  late Future<Tree> futureTree;

  @override
  void initState() {
    super.initState();
    // Initialize the futureTree with the tree data based on the provided id
    futureTree = getTree(widget.id);
  }

// future with listview
// https://medium.com/nonstopio/flutter-future-builder-with-list-view-builder-d7212314e8c9
  @override
  Widget build(BuildContext context) {
    return FutureBuilder<Tree>(
      future: futureTree,
      builder: (context, snapshot) {
        // Check if the future has completed successfully
        if (snapshot.hasData) {
          return Scaffold(
            appBar: AppBar(
              // Set the app bar color and title based on the tree data
              backgroundColor: Theme.of(context).colorScheme.primary,
              foregroundColor: Theme.of(context).colorScheme.onPrimary,
              title: Text(snapshot.data!.root.id),
              actions: <Widget>[
                IconButton(
                  icon: const Icon(Icons.home),
                  onPressed: () {
                    // Navigate back to the main screen
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
              itemCount: snapshot.data!.root.children.length,
              itemBuilder: (BuildContext context, int i) =>
                  _buildRow(snapshot.data!.root.children[i], i),
              separatorBuilder: (BuildContext context, int index) =>
              const Divider(),
            ),
          );
        } else if (snapshot.hasError) {
          // Display error message if the future fails
          return Text("${snapshot.error}");
        }
        // By default, show a progress indicator while loading
        return Container(
            height: MediaQuery.of(context).size.height,
            color: Colors.white,
            child: Center(
              child: CircularProgressIndicator(),
            ));
      },
    );
  }

  Widget _buildRow(Area area, int index) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Expanded(
          child: ListTile(
            // Display the title based on the type of area (Partition or Space)
            title: Text(
              area is Partition ? 'Partition ${area.id}' : 'Space ${area.id}',
              style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            onTap: () {
              // Navigate to the selected area
              if (area is Partition) {
                _navigateDownPartition(area.id);
              } else {
                _navigateDownSpace(area.id);
              }
            },
          ),
        ),
        ElevatedButton.icon(
          // Button to lock or unlock the area
          icon: Icon(area.locked ? Icons.lock : Icons.lock_open),
          label: Text(area.locked ? "Unlock" : "Lock"),
          style: ElevatedButton.styleFrom(
            backgroundColor: area.locked ? Colors.orange : Colors.blue,
          ),
          onPressed: () async {
            // Toggle the lock state of the area
            if (area.locked) {
              await unlockArea(area); // Unlock the area if it is currently locked
            } else {
              await lockArea(area); // Lock the area if it is currently unlocked
            }
            setState(() {
              area.locked = !area.locked; // Update local state to reflect the new lock status
            });
          },
        ),
      ],
    );
  }

  void _navigateDownPartition(String childId) {
    // Navigate to the child partition screen
    Navigator.of(context)
        .push(MaterialPageRoute<void>(builder: (context) => ScreenPartition(id: childId,))
    );
  }

  void _navigateDownSpace(String childId) {
    // Navigate to the child space screen
    Navigator.of(context)
        .push(MaterialPageRoute<void>(builder: (context) => ScreenSpace(id: childId,))
    );
  }

}
