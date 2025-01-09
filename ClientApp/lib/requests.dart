import 'package:http/http.dart' as http;
import 'dart:convert' as convert;
import 'package:intl/intl.dart';

import 'tree.dart';
import 'package:flutter/material.dart';

// Define the base URL for API requests
const String BASE_URL = "http://localhost:8080";
// Define the date format used in the requests
final DateFormat DATEFORMATTER = DateFormat('yyyy-MM-ddThh:mm');

// Function to send HTTP GET request and return response body as a string
Future<String> sendRequest(Uri uri) async {
  final response = await http.get(uri);
  if (response.statusCode == 200) {
    // Print the status and body if the request is successful
    print("statusCode=$response.statusCode");
    print(response.body);
    return response.body;
  } else {
    // Print the status code and throw an exception for failed requests
    print("statusCode=$response.statusCode");
    throw Exception('failed to get answer to request $uri');
  }
}

// Function to get a Tree object based on the area ID
Future<Tree> getTree(String areaId) async {
  Uri uri = Uri.parse("${BASE_URL}/get_children?$areaId");
  final String responseBody = await sendRequest(uri);
  Map<String, dynamic> decoded = convert.jsonDecode(responseBody);
  return Tree(decoded);
}

// Function to lock a door
Future<void> lockDoor(Door door) async {
  await lockUnlockDoor(door, 'lock');
}

// Function to unlock a door
Future<void> unlockDoor(Door door) async {
  await lockUnlockDoor(door, 'unlock');
}

// Function to open a door
Future<void> openDoor(Door door) async {
  await openCloseDoor(door, 'open');
}

// Function to close a door
Future<void> closeDoor(Door door) async {
  await openCloseDoor(door, 'close');
}

// Function to open or close a door with a specified action
Future<void> openCloseDoor(Door door, String action) async {
  assert ((action=='open') | (action=='close')); // Ensure the action is either 'open' or 'close'
  String strNow = DATEFORMATTER.format(DateTime.now()); // Format current date and time
  print(strNow);
  Uri uri = Uri.parse("${BASE_URL}/reader?credential=11343&action=$action"
      "&datetime=$strNow&doorId=${door.id}"); // Build the URI with necessary parameters
  print('close ${door.id}, uri $uri');
  final String responseBody = await sendRequest(uri); // Send request to API
  print('requests.dart : door ${door.id} is ${door.state}'); // Print the door state after request
}

// Function to lock or unlock a door based on action ('lock' or 'unlock')
Future<void> lockUnlockDoor(Door door, String action) async {
  assert ((action=='lock') | (action=='unlock')); // Ensure the action is either 'lock' or 'unlock'
  String strNow = DATEFORMATTER.format(DateTime.now()); // Format current date and time
  print(strNow);
  Uri uri = Uri.parse("${BASE_URL}/reader?credential=11343&action=$action"
      "&datetime=$strNow&doorId=${door.id}"); // Build the URI with necessary parameters
  print('lock ${door.id}, uri $uri');
  final String responseBody = await sendRequest(uri); // Send request to API
  print('requests.dart : door ${door.id} is ${door.state}'); // Print the door state after request
}

// Function to lock an area
Future<void> lockArea(Area area) async {
  lockUnlockArea(area, 'lock');
}

// Function to unlock an area
Future<void> unlockArea(Area area) async {
  lockUnlockArea(area, 'unlock');
}

// Function to lock or unlock an area based on action ('lock' or 'unlock')
Future<void> lockUnlockArea(Area area, String action) async {
  assert ((action=='lock') | (action=='unlock')); // Ensure the action is either 'lock' or 'unlock'
  String strNow = DATEFORMATTER.format(DateTime.now()); // Format current date and time
  print(strNow);
  Uri uri = Uri.parse("${BASE_URL}/area?credential=11343&action=$action"
      "&datetime=$strNow&areaId=${area.id}"); // Build the URI with necessary parameters
  print('lock ${area.id}, uri $uri');
  final String responseBody = await sendRequest(uri); // Send request to API
  print('requests.dart : area ${area.id} is ${action}'); // Print the area state after request
}
