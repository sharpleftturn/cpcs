/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mmdi.gmds.ws.pcs.resource;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jeremybeckman
 */
public class GeoDataResourceManager {
    
    private JsonResource postalCodeJsonResource;
    private JsonResource geoLocationJsonResource;
    private Logger logger = LoggerFactory.getLogger(GeoDataResourceManager.class);
    
    public void loadPostalCodeResources(String uri, String cacheRegion) {
        try {
            postalCodeJsonResource = new PostalCodeJsonResource(uri, cacheRegion);
            postalCodeJsonResource.load();
        } catch(IOException ioe) {
            logger.error(ioe.getMessage());
        }
        
    }
    
    public void loadGeoLocationResources(String url, String cacheRegion, String postalCodeCacheRegion) {
        try {
            geoLocationJsonResource = new GeoLocationJsonResource(url, cacheRegion, postalCodeCacheRegion);
            geoLocationJsonResource.load();
        } catch(IOException ioe) {
            logger.error(ioe.getMessage());
        }
    }
    
    public GeoLocationJsonResource getGeoResource() {
        return (GeoLocationJsonResource)geoLocationJsonResource;
    }
    
    public PostalCodeJsonResource getPostalCodeResource() {
        return (PostalCodeJsonResource)postalCodeJsonResource;
    }
    
}
