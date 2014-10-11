/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mmdi.gmds.ws.pcs.sax;

import com.mmdi.gmds.ws.pcs.model.Facility;
import com.mmdi.gmds.ws.pcs.model.Location;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author jeremybeckman
 */
public class XMLParser extends DefaultHandler {
    private Logger logger = LoggerFactory.getLogger(XMLParser.class);
    private InputStream inputStream;
    private Location location;
    private Facility facility;
    private List<Location> locations;
    private String value;
    
    public XMLParser(InputStream inputStream) {
        this.inputStream = inputStream;
        locations = new ArrayList<Location>();
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attribs) throws SAXException {
        if(qName.equalsIgnoreCase("location")) {
            location = new Location();
        }
        if(qName.equalsIgnoreCase("facility")) {
            facility = new Facility();
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equalsIgnoreCase("location")) {
            locations.add(location);
        }
        if(qName.equalsIgnoreCase("locationid")) {
            location.setLocationID(Integer.parseInt(value));
        }
        if(qName.equalsIgnoreCase("locationname")) {
            location.setLocationName(value);
        }
        if(qName.equalsIgnoreCase("address")) {
            location.setAddress(value);
        }
        if(qName.equalsIgnoreCase("postalcode")) {
            location.setPostalCode(value);
        }
        if(qName.equalsIgnoreCase("phonenumber")) {
            location.setPhoneNumber(value);
        }
        if(qName.equalsIgnoreCase("facility")) {
            location.getFacilities().add(facility);
        }
        if(qName.equalsIgnoreCase("facilityid")) {
            facility.setFacilityID(value);
        }
        if(qName.equalsIgnoreCase("facilitytype")) {
            facility.setFacilityType(value);
        }
        if(qName.equalsIgnoreCase("facilityname")) {
            facility.setFacilityName(value);
        }
        if(qName.equalsIgnoreCase("facilitydisplayname")) {
            facility.setFacilityDisplayName(value);
        }
        if(qName.equalsIgnoreCase("intersection")) {
            location.getIntersections().add(value);
        }
    }
    
    @Override
    public void characters(char[] ch, int i, int j) throws SAXException {
        value = new String(ch, i ,j);
    }
    
    public void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(inputStream, this);
        } catch(ParserConfigurationException pce) {
            logger.error(pce.getMessage());
        } catch(SAXException se) {
            logger.error(se.getMessage());
        } catch(IOException ioe) {
            logger.error(ioe.getMessage());
        }
    }
    
    public List<Location> getData() {
        return locations;
    }
}
