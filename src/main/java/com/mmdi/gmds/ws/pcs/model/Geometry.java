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
public class Geometry {
    
    @JsonProperty("bounds")
    private Bounds bounds;
    @JsonProperty("location")
    private GeometricLocation geometricLocation;
    
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }
    
    public Bounds getBounds() {
        return bounds;
    }
    
    public void setGeometricLocation(GeometricLocation geometricLocation) {
        this.geometricLocation = geometricLocation;
    }
    
    public GeometricLocation getGeometricLocation() {
        return geometricLocation;
    }
}
