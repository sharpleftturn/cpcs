library control_panel.dart;

import 'dart:html';
import 'dart:async';
import 'package:polymer/polymer.dart';

@CustomTag('control-panel')
class GreenspaceControlPanel extends PolymerElement {

  @observable String filter;
  Element target;
  Element filterElement;
  int touchStartX;
  int index;
  
  @observable Map<String, String> labels = toObservable({
    'Playground': 'playground.svg',
    'Pool': 'pool.svg',
    'Sport Field': 'sport.svg',
    'Leash': 'leash.svg'
  });
  
  GreenspaceControlPanel.created() : super.created() {
    filter = labels.keys.first;
    index = 0;
  }

  @override
  void attached() {
    super.attached();

    target = $['selection'];
    target.onClick.listen((MouseEvent event) {
      filterItem();
    });
    target.onTouchStart.listen((TouchEvent event) {
      event.preventDefault();
      print("swipe started");
    });
    target.onTouchMove.listen((TouchEvent event) {
      event.preventDefault();
      print("swipe moved");
      
    });
    target.onTouchEnd.listen((TouchEvent event) {
      event.preventDefault();
      print("swipe ended");
    });
    
    document.onKeyDown.listen((KeyboardEvent event) {
        switch (event.keyCode) {
          case KeyCode.LEFT:
            startTransition(target, -1);
            break;
          case KeyCode.RIGHT:
            startTransition(target, 1);
            break;
        }
      });
    
    document.onKeyUp.listen((event) => stopTransition());
    
    

  }
  
  void startTransition(Element figure, int direction) {
      executeTransition(figure, direction);
  }
  
  void executeTransition(Element figure, int direction) {
    index += direction;
    
    if(index < 0) {
      index = 0;
    }
    
    if(index >= labels.length) {
      index = labels.length - 1;
    }
    
    filter = labels.keys.elementAt(index);
    figure.attributes['src'] = labels[filter];
  }
  
  void stopTransition() {
  
  }

  void filterItem() {
    dispatchEvent(new CustomEvent('filter-change'));
  }
}
