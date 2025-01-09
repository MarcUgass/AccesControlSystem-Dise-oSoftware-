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
  late Future<Tree> futureTree;

  @override
  void initState() {
    super.initState();
    futureTree = getTree(widget.id); // Se obtiene el árbol de la estructura de habitaciones
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<Tree>(
      future: futureTree, // Espera los datos del árbol
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          // Obtenemos el conteo de puertas
          Map<String, int> counts = snapshot.data!.countRoomsAndDoors();
          return Scaffold(
            appBar: AppBar(
              backgroundColor: Theme.of(context).colorScheme.primary,
              foregroundColor: Theme.of(context).colorScheme.onPrimary,
              title: Text("${snapshot.data!.root.id}"), // Muestra el ID de la raíz
              actions: <Widget>[
                IconButton(
                  icon: const Icon(Icons.home),
                  onPressed: () {
                    Navigator.of(context).pushAndRemoveUntil(
                      MaterialPageRoute(builder: (context) => const ScreenPartition(id: "building")),
                          (Route<dynamic> route) => false, // Redirige al inicio
                    );
                  },
                ),
                // Muestra el recuento de puertas
                Padding(
                  padding: const EdgeInsets.only(right: 16),
                  child: Center(
                    child: Text(
                      'Doors: ${counts['doors']}',
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
                  _buildRow(snapshot.data!.root.children[i], i), // Construye las filas de las puertas
              separatorBuilder: (BuildContext context, int index) => const Divider(),
            ),
          );
        } else if (snapshot.hasError) {
          return Text("${snapshot.error}"); // Muestra errores si los hay
        }
        return Container(
            height: MediaQuery.of(context).size.height,
            color: Colors.white,
            child: Center(
              child: CircularProgressIndicator(), // Muestra cargando mientras se obtiene la data
            ));
      },
    );
  }

  // Construye la fila con el estado de la puerta y los botones de control
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
            await unlockDoor(door); // Desbloquea la puerta
            setState(() => door.state = 'unlocked'); // Actualiza el estado visual
          },
        )
            : ElevatedButton.icon(
          icon: const Icon(Icons.lock_open),
          label: const Text("Lock"),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.orange, // Naranja para bloquear
          ),
          onPressed: () async {
            await lockDoor(door); // Bloquea la puerta
            setState(() => door.state = 'locked'); // Actualiza el estado visual
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
            await openDoor(door); // Abre la puerta
            setState(() => door.closed = false); // Actualiza el estado visual
          },
        )
            : ElevatedButton.icon(
          icon: const Icon(Icons.door_back_door),
          label: const Text("Close"),
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.red, // Rojo para cerrar
          ),
          onPressed: () async {
            await closeDoor(door); // Cierra la puerta
            setState(() => door.closed = true); // Actualiza el estado visual
          },
        ),
      ],
    );
  }
}
