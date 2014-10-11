/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mmdi.gmds.ws.pcs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeremybeckman
 */
public class Location implements Serializable {
    static final long serialVersionUID = 0L;
            
    private Integer locationID;
    private String locationName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private Double lat;
    private Double lng;
    private List<Facility> facilities = new ArrayList<Facility>();
    private List<String> intersections = new ArrayList<String>();
    
    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }
    
    public Integer getLocationID() {
        return locationID;
    }
    
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    public String getLocationName() {
        return locationName;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }
    
    public List<Facility> getFacilities() {
        return facilities;
    }
    
    public void setIntersections(List<String> intersections) {
        this.intersections = intersections;
    }
    
    public List<String> getIntersections() {
        return intersections;
    }
    
    public void setLat(Double lat) {
        this.lat = lat;
    }
    
    public Double getLat() {
        return lat;
    }
    
    public void setLng(Double lng) {
        this.lng = lng;
    }
    
    public Double getLng() {
        return lng;
    }
}
