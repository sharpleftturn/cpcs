library greenspace_form.dart;
import 'dart:html';
import 'package:polymer/polymer.dart';
import 'package:paper_elements/paper_input.dart';

@CustomTag('greenspace-form')
class GreenspaceComponent extends FormElement with Polymer, Observable {
  
  HttpRequest request;
  
  GreenspaceComponent.created() : super.created() { 
    polymerCreated();
  }
  
  Map formData = {
    'postalCode': ''
  };
  
  void onPostalCodeChange() {
    formData['postalCode'] = ($['postalcode-input'] as PaperInput).value;
  }
}