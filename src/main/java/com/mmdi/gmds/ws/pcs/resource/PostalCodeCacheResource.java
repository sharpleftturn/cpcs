/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mmdi.gmds.ws.pcs.resource;

import com.mmdi.gmds.ws.pcs.model.PostalCode;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

/**
 *
 * @author jeremybeckman
 */
public class PostalCodeCacheResource extends CacheResource<PostalCode> {
    
    public void putData(PostalCode[] data, String region) throws CacheException {
        JCS cache = JCS.getInstance(region);

        for (PostalCode item : data) {
            cache.put(item.getCode(), item);
        }
    }
    
    public void putData(String key, PostalCode data, String region) throws CacheException {
        JCS cache = JCS.getInstance(region);
        cache.put(key, data);
    }
}
