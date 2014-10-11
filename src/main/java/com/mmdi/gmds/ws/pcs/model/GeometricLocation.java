/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mmdi.gmds.ws.pcs.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author jeremybeckman
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GeometricLocation {
    
    @JsonProperty("lat")
    private Double lat;
    
    @JsonProperty("lng")
    private Double lng;
    
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
