package com.mmdi.gmds.ws.pcs.service;

import com.mmdi.gmds.ws.pcs.model.PostalCode;
import com.mmdi.gmds.ws.pcs.resource.GeoDataResourceManager;
import com.mmdi.gmds.ws.pcs.resource.PostalCodeJsonResource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JAX-RS annotated implementation of the PostalCodeService interface. A RESTful
 * web service that exposes methods to the user for searching and refreshing
 * postal code data in memory.
 *
 * @author Jeremy Beckman
 * @version 1.0
 */
@Path("/cpc")
@Component
public class PostalCodeServiceImpl implements PostalCodeService {

    @Autowired
    public GeoDataResourceManager geoResourceManager;
    private PostalCodeJsonResource jsonResource;
    @Value("${pattern_string}")
    public String regex;
    private Logger logger = LoggerFactory.getLogger(PostalCodeServiceImpl.class);

    public PostalCodeServiceImpl() {
        super();
    }

    protected Boolean validate(String code) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    protected PostalCode lookupCode(String code) {
        jsonResource = geoResourceManager.getPostalCodeResource();
        PostalCode postalCode = null;
        try {
            postalCode = jsonResource.find(code);
        } catch (CacheException ce) {
            logger.error(ce.getMessage());
        } finally {
            return postalCode;
        }
    }

    protected void refreshAll() {
        jsonResource = geoResourceManager.getPostalCodeResource();
        try {
            jsonResource.refresh();
        } catch (CacheException ce) {
            logger.error(ce.getMessage());
        }
    }

    /**
     * Returns the JSON record for the postal code passed in the REST call. URL
     * pattern: //http://domain.to/cpc/lookup/{postalcode}
     *
     * @param code postal code string taken from the end of REST URL.
     * @return JSON formatted response
     */
    @GET
    @Path("/lookup/{code}")
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response lookup(@PathParam("code") String code) {
        GenericEntity entity;
        Response response = Response.status(Response.Status.BAD_REQUEST).build();

        if (validate(code)) {
            PostalCode postalCode = lookupCode(code);

            if (postalCode != null) {
                entity = new GenericEntity<PostalCode>(postalCode) {
                };
                response = Response.ok(entity).build();
            }
        }
        return response;
    }

    /**
     * Refresh the data in the JCS cache. Current data is cleared and new
     * records are loaded into the same cache region. Capacity of the cache is
     * not checked before loading data. Returns an HTTP status code 200 if
     * successful to indicate refresh is complete.
     *
     * @return response with HTTP status code ONLY.
     */
    @GET
    @Path("/refresh")
    @Override
    public Response refresh() {
        refreshAll();
        return Response.ok().build();
    }
}
