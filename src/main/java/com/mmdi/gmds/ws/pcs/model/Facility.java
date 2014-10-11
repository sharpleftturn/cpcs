/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mmdi.gmds.ws.pcs.model;

import java.io.Serializable;

/**
 *
 * @author jeremybeckman
 */
public class Facility implements Serializable {
    static final long serialVersionUID = 0L;
    
    private String facilityID;
    private String facilityType;
    private String facilityName;
    private String facilityDisplayName;
    
    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }
    
    public String getFacilityID() {
        return facilityID;
    }
    
    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }
    
    public String getFacilityName() {
        return facilityName;
    }
    
    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }
    
    public String getFacilityType() {
        return facilityType;
    }
    
    public void setFacilityDisplayName(String facilityDisplayName) {
        this.facilityDisplayName = facilityDisplayName;
    }
    
    public String getFacilityDisplayName() {
        return facilityDisplayName;
    }
}
