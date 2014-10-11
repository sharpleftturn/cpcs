/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mmdi.gmds.ws.pcs.resource;

import com.mmdi.gmds.ws.pcs.model.Location;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

/**
 *
 * @author jeremybeckman
 */
public class GeoLocationCacheResource extends CacheResource<Location> {
    
    @Override
    public void putData(Location[] data, String region) throws CacheException {
        JCS cache = JCS.getInstance(region);

        for (Location item : data) {
            cache.put(item.getLocationName(), item);
        }
    }
    
    @Override
    public void putData(String key, Location data, String region) throws CacheException {
        JCS cache = JCS.getInstance(region);
        cache.put(key, data);
    }
}
