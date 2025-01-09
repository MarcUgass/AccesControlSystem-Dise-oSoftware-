import 'package:http/http.dart' as http;
import 'dart:convert' as convert;
import 'package:intl/intl.dart';

import 'tree.dart';
import 'package:flutter/material.dart';


// Base URL for the requests
const String BASE_URL = "http://localhost:8080";
// Date format used in the requests
final DateFormat DATEFORMATTER = DateFormat('yyyy-MM-ddThh:mm');

// Function to send an HTTP GET request and handle the response
Future<String> sendRequest(Uri uri) async {
  final response = await http.get(uri);
  // Check if the response was successful
  if (response.statusCode == 200) {
    print("statusCode=$response.statusCode");
    print(response.body);
    return response.body;
  } else {
    // Throw an exception if the request fails
    print("statusCode=$response.statusCode");
    throw Exception('failed to get answer to request $uri');
  }
}

// Function to get a tree based on the area ID
Future<Tree> getTree(String areaId) async {
  Uri uri = Uri.parse("${BASE_URL}/get_children?$areaId");
  final String responseBody = await sendRequest(uri);
  Map<String, dynamic> decoded = convert.jsonDecode(responseBody);
  return Tree(decoded);
}

// Functions to lock and unlock doors
Future<void> lockDoor(Door door) async {
  await lockUnlockDoor(door, 'lock');
}

Future<void> unlockDoor(Door door) async {
  await lockUnlockDoor(door, 'unlock');
}

// Functions to open and close doors
Future<void> openDoor(Door door) async {
  await openCloseDoor(door, 'open');
}

Future<void> closeDoor(Door door) async {
  await openCloseDoor(door, 'close');
}

// Function to open or close a door using an HTTP request to the server
Future<void> openCloseDoor(Door door, String action) async {
  // Ensure that the action is valid
  assert ((action=='open') | (action=='close'));
  String strNow = DATEFORMATTER.format(DateTime.now());
  print(strNow);
  Uri uri = Uri.parse("${BASE_URL}/reader?credential=11343&action=$action"
      "&datetime=$strNow&doorId=${door.id}");
  print('close ${door.id}, uri $uri');
  final String responseBody = await sendRequest(uri);
  print('requests.dart : door ${door.id} is ${door.state}');
}

// Function to lock or unlock a door
Future<void> lockUnlockDoor(Door door, String action) async {
  // Ensure that the action is valid
  assert ((action=='lock') | (action=='unlock'));
  String strNow = DATEFORMATTER.format(DateTime.now());
  print(strNow);
  Uri uri = Uri.parse("${BASE_URL}/reader?credential=11343&action=$action"
      "&datetime=$strNow&doorId=${door.id}");
  print('lock ${door.id}, uri $uri');
  final String responseBody = await sendRequest(uri);
  print('requests.dart : door ${door.id} is ${door.state}');
}

// Functions to lock and unlock areas
Future<void> lockArea(Area area) async {
  lockUnlockArea(area, 'lock');
}

Future<void> unlockArea(Area area) async {
  lockUnlockArea(area, 'unlock');
}

// Function to lock or unlock an area
Future<void> lockUnlockArea(Area area, String action) async {
  // Ensure that the action is valid
  assert ((action=='lock') | (action=='unlock'));
  String strNow = DATEFORMATTER.format(DateTime.now());
  print(strNow);
  Uri uri = Uri.parse("${BASE_URL}/area?credential=11343&action=$action"
      "&datetime=$strNow&areaId=${area.id}");
  print('lock ${area.id}, uri $uri');
  final String responseBody = await sendRequest(uri);
  print('requests.dart : area ${area.id} is ${action}');
}
