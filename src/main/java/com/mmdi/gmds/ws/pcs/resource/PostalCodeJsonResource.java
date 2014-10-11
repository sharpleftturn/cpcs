package com.mmdi.gmds.ws.pcs.resource;

import com.mmdi.gmds.ws.pcs.model.PostalCode;
import java.io.File;
import java.io.IOException;
import org.apache.jcs.access.exception.CacheException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the internal logic for the exposed methods of the PostalCodeService
 * implementation.
 *
 * @author Jeremy Beckman
 * @version 1.0
 */
public class PostalCodeJsonResource extends JsonResource<PostalCode> {

    private Logger logger = LoggerFactory.getLogger(PostalCodeJsonResource.class);

    public PostalCodeJsonResource(String uri, String cacheRegion) {
        super(uri, cacheRegion);
    }

    /**
     * Returns a PostalCode object for the given postal code string.
     *
     * @param value postal code string
     * @return PostalCode object
     * @throws CacheException
     */
    public PostalCode find(String value) throws CacheException {
        CacheResource cache = new PostalCodeCacheResource();
        String key = value.toUpperCase();
        PostalCode postalCode = (PostalCode) cache.getData(key, cacheRegion);
        return postalCode;
    }

    /**
     * Refreshes the JCS cache with the data from a file. The file path (uri) is
     * configurable. This allows the web service to update its data without
     * requiring a restart or redeployment.
     *
     * @throws CacheException
     */
    protected void clear() throws CacheException {
        CacheResource cache = new PostalCodeCacheResource();
        cache.clear(cacheRegion);
    }

    protected PostalCode[] build() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PostalCode[] postalCodes = mapper.readValue(new File(path), PostalCode[].class);
        return postalCodes;
    }

    protected void persist(PostalCode[] data) throws CacheException {
        CacheResource cache = new PostalCodeCacheResource();
        cache.putData(data, cacheRegion);
    }
}
