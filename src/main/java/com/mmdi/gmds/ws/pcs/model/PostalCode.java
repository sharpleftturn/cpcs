package com.mmdi.gmds.ws.pcs.model;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author jeremy.beckman
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class PostalCode implements Serializable {
    static final long serialVersionUID = -3781408772476215699L;
    
    @JsonProperty("city")
    private String city;
    
    @JsonProperty("pcode")
    private String code;
   
    @JsonProperty("lat")
    private Double lat;
    
    @JsonProperty("long")
    private Double lon;
    
    @JsonProperty("region")
    private String region;
    
    private String zone;

    /**
     * Sets the postal code
     * 
     * @param value Postal code 
     */
    public void setCode(String value) {
        code = value;
    }

    /**
     * Returns the postal code
     * 
     * @return String The postal code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the province/state for the postal code.
     * 
     * @param value 2-character abbreviation of the province/state.
     */
    public void setRegion(String value) {
        region = value;
    }

    /**
     * Returns a 2-character abbreviation of the province/state
     * for the postal code.
     * 
     * @return String 2-char province code.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the city of the postal code.
     * 
     * @param value The city name associated with the postal code.
     */
    public void setCity(String value) {
        city = value;
    }

    /**
     * Returns the name of the city associated with the postal code.
     * 
     * @return the city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the latitude coordinate for the postal code.
     * 
     * @param value The numeric latitude coordinate.
     */
    public void setLat(Double value) {
        lat = value;
    }

    /**
     * Returns the latitude coordinate for the postal code.
     * 
     * @return numeric latitude coordinate.
     */
    public Double getLat() {
        return lat;
    }

    /**
     * Sets the longitude coordinate for the postal code.
     * 
     * @param value The numeric latitude coordinate.
     */
    public void setLong(Double value) {
        lon = value;
    }

    /**
     * Returns the longitude coordinate for the postal code.
     * 
     * @return numeric latitude coordinate.
     */
    public Double getLong() {
        return lon;
    }

    /**
     * Sets the GM marketing zone ID for the postal code.
     * 
     * @param value The GM marketing zone ID. 
     */
    public void setZone(String value) {
        zone = value;
    }

     /**
     * Returns the GM marketing zone ID for the postal code.
     * 
     * @return the GM marketing zone ID. 
     */
    public String getZone() {
        return zone;
    }
}
