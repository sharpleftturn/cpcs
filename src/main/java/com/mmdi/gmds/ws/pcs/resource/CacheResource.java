/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mmdi.gmds.ws.pcs.resource;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

/**
 *
 * @author jeremybeckman
 */
public abstract class CacheResource<T> {
    
    abstract void putData(T[] data, String region) throws CacheException;
    abstract void putData(String key, T data, String region) throws CacheException;
    
    public T getData(String key, String region) throws CacheException {
        JCS cache = JCS.getInstance(region);
        return (T)cache.get(key);
    }
    
    public void clear(String region) throws CacheException {
        JCS cache = JCS.getInstance(region);
        cache.clear();
    }
}
