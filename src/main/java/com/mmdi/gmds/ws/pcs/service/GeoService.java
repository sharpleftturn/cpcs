package com.mmdi.gmds.ws.pcs.service;

import javax.ws.rs.core.Response;

/**
 *
 * @author jeremybeckman
 */
public interface GeoService {
    public Response getSpaces();
    public Response lookup(String name);
}
