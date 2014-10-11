package com.mmdi.gmds.ws.pcs.config;

import com.mmdi.gmds.ws.pcs.resource.GeoDataResourceManager;
import com.mmdi.gmds.ws.pcs.resource.GeoLocationJsonResource;
import com.mmdi.gmds.ws.pcs.resource.PostalCodeJsonResource;
import java.io.IOException;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author jeremy.beckman
 */
@Configuration
@Import(WSConfiguration.class)
public class ResourceConfiguration {
    @Value("${postal_code_uri}")
    String postalCodeUri;
    @Value("${geo_url}")
    private String geoUrl;
    @Value("${cache_region}")
    String cacheRegion;
    @Value("${geo_cache_region}")
    private String geoCacheRegion;
    
    Logger logger = LoggerFactory.getLogger(ResourceConfiguration.class);
    
    /**
     * Returns the singleton instance of JacksonJsonProvider bean.
     * This method is used by JAX-RS for generating JSON objects
     * when the MeidaType.APPLICATION_JSON annotation is used.
     * This method should be called by the framework ONLY. 
     * 
     * @return singleton instance of JacksonJsonProvider
     * @see JacksonJsonProvider
     */
    @Bean
    @Scope(value="singleton")
    public JacksonJsonProvider jsonProvider() {
        return (new JacksonJsonProvider());
    }

    /**
     * Returns an instance of GeoDataResourceManager bean for loading 
     * JSON records into memory cache.
     * This method should be called by the framework ONLY. 
     * 
     * @return GeoDataResourceManager
     */
    @Bean
    public GeoDataResourceManager geoResourceManager() {
        GeoDataResourceManager geoResourceManager = new GeoDataResourceManager();
        geoResourceManager.loadPostalCodeResources(postalCodeUri, cacheRegion);
        geoResourceManager.loadGeoLocationResources(geoUrl, geoCacheRegion, cacheRegion);
        return geoResourceManager;
    }
    
    
}
