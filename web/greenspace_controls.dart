library greenspace_controls.dart;

import 'dart:html';
import 'package:polymer/polymer.dart';
import 'control_panel.dart'; 
import 'package:google_maps/google_maps.dart' as gmaps;

@CustomTag('greenspace-controls')
class GreenspaceControls extends PolymerElement {
  
  String filter;
  GreenspaceControlPanel panel;
  List data;
  gmaps.GMap map;
  var markers = [];
  var scaledX;
  var scaledY;
  var title;
  bool minimized = false;
  
  GreenspaceControls.created() : super.created();
  
  void updateFilter(Event e, var detail, Node target) {
    if(!minimized) {
      panel = this.shadowRoot.querySelector('#panel');
      filter = panel.filter;
      List filteredData = filterDataByKey(filter, data);
      updateMarkers(filteredData);
    }
    toggleControls();
  }
  
  List filterDataByKey(String key, List dataList) {
      List filteredSpaces = new List();
      for(Map space in dataList) {
        List facilities = space['facilities'];
        for(Map facility in facilities) {
          if(facility['facilityType'].toLowerCase().contains(key.toLowerCase())) {
            filteredSpaces.add(space);
          } 
        }
      }
     return filteredSpaces;
  }
  
  void updateMarkers(List data) {
    for(gmaps.Marker marker in markers) {
      removeMarker(marker);
    }
    
    for(Map record in data) {
      addMarker(record['locationName'], record['lat'], record['lng']);
    }
  }
  
  void addMarker(title, lat, lng) {
    gmaps.Marker marker = new gmaps.Marker()
                 ..position = new gmaps.LatLng(lat, lng)
                 ..map = map
                 ..title = title;
    marker.onClick.listen((gmaps.MouseEvent event) {
      showDialog();
    });
    markers.add(marker);
  }
  
  void removeMarker(marker) {
    marker.map = null;
    markers.remove(marker);
  }
  
  void showDialog() {
    
  }
  
  void toggleControls() {
    if(!minimized) {
      minimize();
    } else {
      maximize();
    }
  }
  
  void minimize() {
    double pct = 0.15;
    var maxWidth = this.client.width;
    var minWidth = this.client.width * pct;
    scaledX = (maxWidth - minWidth) / 2;
    var maxHeight = this.client.height;
    var minHeight = this.client.height * pct;
    scaledY = (maxHeight - minHeight) / 2;
    this.style.transform = 'translate(${scaledX}px,${scaledY}px) scale(${pct}, ${pct})';
    title = panel.$['title'];
    title.remove();
    minimized = true;
  }
  
  void maximize() {
    this.style.transform = 'translate(0,0)';
    minimized = false;
  }
}