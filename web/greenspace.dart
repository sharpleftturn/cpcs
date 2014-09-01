import 'dart:html';
import 'dart:convert';
import 'package:polymer/polymer.dart';
import 'package:google_maps/google_maps.dart';
import 'greenspace_controls.dart';

List spacesData;
GMap map;
GreenspaceControls controls;
String apiKey = 'AIzaSyDqxj_5rssrYL9B0bqwnFGjM74Hi4bJCMQ';

main() {
  initPolymer().run(() {
    loadMapView();
  });
  
  controls = querySelector('#controls');
  var request = HttpRequest.getString('http://localhost:8081/pcs/geo/spaces').then(loadSpaces);
}

void addMarker(map, lat, lng) {
  var marker = new Marker()
               ..position = new LatLng(lat, lng)
               ..map = map
               ..title = 'Greenspace';
}

void loadMapView() {
  getUserGeoLocation();
}

void loadMap(lat, lng) {
  var mapOptions = new MapOptions()
                             ..center = new LatLng(lat, lng)
                             ..zoom = 12
                             ..mapTypeId = MapTypeId.ROADMAP;
                      
    map = new GMap(querySelector("#map-canvas"), mapOptions);
    controls.map = map;
}

void getUserGeoLocation() {
  var geocodeUrl = setGoogleGeocodeUrl();
  var request = HttpRequest.getString(geocodeUrl).then(loadGeoData);
}

String setGoogleGeocodeUrl() {
  var url = 'https://maps.googleapis.com/maps/api/geocode/json';
  var stringBuffer = new StringBuffer(url);
  stringBuffer.write('?address=Toronto');
  stringBuffer.write('&key=');
  stringBuffer.write(apiKey);
  return stringBuffer.toString();
}

void loadGeoData(String responseData) {
  Map geoData = JSON.decode(responseData);
  List jsonList = geoData['results'];
  Map geometry = jsonList[0]['geometry'];
  Map geolocation = geometry['location'];
  loadMap(geolocation['lat'], geolocation['lng']);
}

void loadSpaces(String responseData) {
    spacesData = JSON.decode(responseData);
    controls.data = spacesData;
}

