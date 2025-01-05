import 'package:flutter/material.dart';
import 'package:flutterapp/tree.dart';
import 'package:flutterapp/requests.dart';

class ScreenSpace extends StatefulWidget {
  final String id;
  const ScreenSpace({super.key, required this.id});

  @override
  State<ScreenSpace> createState() => _ScreenSpaceState();
}

class _ScreenSpaceState extends State<ScreenSpace> {
  late Future<Tree> futureTree;

  @override
  void initState() {
    super.initState();
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
        // anonymous function
        if (snapshot.hasData) {
          return Scaffold(
            appBar: AppBar(
              backgroundColor: Theme.of(context).colorScheme.primary,
              foregroundColor: Theme.of(context).colorScheme.onPrimary,
              title: Text(snapshot.data!.root.id),
              actions: <Widget>[
                IconButton(icon: const Icon(Icons.home), onPressed: () {}
                  // TODO go home page = root
                ),
                //TODO other actions
              ],
            ),
            body: ListView.separated(
              // it's like ListView.builder() but better because it includes a separator between items
              padding: const EdgeInsets.all(16.0),
              itemCount: snapshot.data!.root.children.length,
              itemBuilder: (BuildContext context, int i) =>
                  _buildRow(snapshot.data!.root.children[i], i),
              separatorBuilder: (BuildContext context, int index) =>
              const Divider(),
            ),
          );
        } else if (snapshot.hasError) {
          return Text("${snapshot.error}");
        }
        // By default, show a progress indicator
        return Container(
            height: MediaQuery.of(context).size.height,
            color: Colors.white,
            child: Center(
              child: CircularProgressIndicator(),
            ));
      },
    );

  }

  Widget _buildRow(Door door, int index) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Expanded(
          child: Text(
            'Door ${door.id}',
            style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
          ),
        ),
        door.state == 'locked'
            ? ElevatedButton.icon(
          icon: const Icon(Icons.lock),
          label: const Text("Unlock"),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.blue, // Azul para desbloquear
          ),
          onPressed: () async {
            await unlockDoor(door);
            setState(() => door.state = 'unlocked'); // Actualizar visual
          },
        )
            : ElevatedButton.icon(
          icon: const Icon(Icons.lock_open),
          label: const Text("Lock"),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.orange, // Naranja para bloquear
          ),
          onPressed: () async {
            await lockDoor(door);
            setState(() => door.state = 'locked'); // Actualizar visual
          },
        ),
        const SizedBox(width: 8), // Espacio entre botones
        door.closed
            ? ElevatedButton.icon(
          icon: const Icon(Icons.door_front_door),
          label: const Text("Open"),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.green, // Verde para abrir
          ),
          onPressed: () async {
            await openDoor(door);
            setState(() => door.closed = false); // Actualizar visual
          },
        )
            : ElevatedButton.icon(
          icon: const Icon(Icons.door_back_door),
          label: const Text("Close"),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.red, // Rojo para cerrar
          ),
          onPressed: () async {
            await closeDoor(door);
            setState(() => door.closed = true); // Actualizar visual
          },
        ),
      ],
    );
  }

}
