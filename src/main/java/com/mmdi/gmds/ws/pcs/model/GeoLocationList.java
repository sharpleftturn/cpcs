/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mmdi.gmds.ws.pcs.model;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author jeremybeckman
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GeoLocationList {
    
    @JsonProperty("results")
    private List<GeoLocation> geoLocations;
    
    public void setGeoLocations(List<GeoLocation> geoLocations) {
        this.geoLocations = geoLocations;
    }
    
    public List<GeoLocation> getGeoLocations() {
        return geoLocations;
    }
}
