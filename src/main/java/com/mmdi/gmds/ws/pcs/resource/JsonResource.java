/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mmdi.gmds.ws.pcs.resource;

import java.io.IOException;
import org.apache.jcs.access.exception.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jeremybeckman
 */
public abstract class JsonResource<T> {
    protected String path;
    protected String cacheRegion;
    private Logger logger = LoggerFactory.getLogger(JsonResource.class);
    
    public JsonResource(String path, String cacheRegion) {
        this.path = path;
        this.cacheRegion = cacheRegion;
    }
    
    public void load() throws IOException {
        try {
            T[] t = build();
            persist(t);
        } catch(CacheException ce) {
            logger.error(ce.getMessage());
        }
    }
    
    public void refresh() throws CacheException {
        clear();
        try {
            load();
        } catch(IOException ioe) {
            logger.error(ioe.getMessage());
        }
    }
    
    public abstract T find(String value) throws CacheException;
    protected abstract T[] build() throws IOException;
    protected abstract void clear() throws CacheException;
    protected abstract void persist(T[] data) throws CacheException;
}
