/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmdi.gmds.ws.pcs.service;

import com.mmdi.gmds.ws.pcs.model.Location;
import com.mmdi.gmds.ws.pcs.resource.GeoDataResourceManager;
import com.mmdi.gmds.ws.pcs.resource.GeoLocationJsonResource;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.jcs.access.exception.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jeremybeckman
 */
@Path("/geo")
@Component
public class GeoServiceImpl implements GeoService {

    @Autowired
    public GeoDataResourceManager geoResourceManager;
    private GeoLocationJsonResource geoResource;
    private Logger logger = LoggerFactory.getLogger(GeoServiceImpl.class);

    public GeoServiceImpl() {
        super();
    }

    @GET
    @Path("/spaces")
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response getSpaces() {
        geoResource = geoResourceManager.getGeoResource();
        Response response = Response.status(Response.Status.BAD_REQUEST).build();

        List<Location> locationList = geoResource.getLocations();
        if (locationList != null) {
            GenericEntity entity = new GenericEntity<List<Location>>(locationList) {
            };
            response = Response.ok(entity).build();
        }
        return response;
    }

    @GET
    @Path("/lookup/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response lookup(@PathParam("name") String name) {
        GenericEntity entity;
        Response response = Response.status(Response.Status.BAD_REQUEST).build();

        Location location = lookupName(name);

        if (location != null) {
            entity = new GenericEntity<Location>(location) {
            };
            response = Response.ok(entity).build();
        }
        return response;
    }

    protected Location lookupName(String name) {
        geoResource = geoResourceManager.getGeoResource();
        Location location = null;
        try {
            location = geoResource.find(name);
        } catch (CacheException ce) {
            logger.error(ce.getMessage());
        } finally {
            return location;
        }
    }
}
