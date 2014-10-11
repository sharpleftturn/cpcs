import 'dart:html';
import 'dart:convert';

class GreenspaceService {
  String url;
  List spacesData;
  
  GreenspaceService(this.url);
  
  getSpaces() {
    var request = HttpRequest.getString(url).then(loadResults);  
  }
  
  void loadResults(String responseData) {
    spacesData = JSON.decode(responseData);
    
  }
  
  List filterSpacesByFacility(String facilityType) {
    List filteredSpaces = new List();
    for(Map space in spacesData) {
      List facilities = space['facilities'];
      for(Map facility in facilities) {
        if(facilityType == facility['facilityType']) {
          filteredSpaces.add(space);
        } 
      }
    }
    return filteredSpaces;
  }
}