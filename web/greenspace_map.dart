import 'dart:html';
import 'package:google_maps/google_maps.dart';

main() {
  var mapOptions = new MapOptions()
                       ..center = new LatLng(43.66232, -79.31483639999999)
                       ..zoom = 15
                       ..mapTypeId = MapTypeId.ROADMAP;
                
  final map = new GMap(querySelector("#map-canvas"), mapOptions);
  
  addMarker(map);
}

void addMarker(map) {
  var marker = new Marker()
               ..position = new LatLng(43.66232, -79.31483639999999)
               ..map = map
               ..title = 'Greenspace';
}
