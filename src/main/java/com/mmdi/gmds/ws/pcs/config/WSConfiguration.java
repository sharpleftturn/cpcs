package com.mmdi.gmds.ws.pcs.config;

/**
 *
 * @author jeremy.beckman
 */
import com.mmdi.gmds.ws.pcs.service.GeoService;
import com.mmdi.gmds.ws.pcs.service.GeoServiceImpl;
import com.mmdi.gmds.ws.pcs.service.PostalCodeService;
import com.mmdi.gmds.ws.pcs.service.PostalCodeServiceImpl;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Spring configuration class for accessing application properties
 * and initializing the PostalCodeService implementation.
 * 
 * @author Jeremy Beckman
 * @version 1.0
 */
@Configuration
public class WSConfiguration {
    
    /**
     * Returns an instance of PropertyPlaceholderConfigurer for accessing
     * a properties file. Method is called by the Spring framework.
     * 
     * @return PropertyPlaceholderConfigurer
     * @see PropertyPlaceholderConfigurer
     */
    @Bean
    public PropertyPlaceholderConfigurer appPlaceholderConfig() {
        PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
        cfg.setLocation(new ClassPathResource("/config.properties"));
        return cfg;
    }
    
    /**
     * Returns an instance of the GeoService JAX-RS web service. 
     * Method is called by the Spring framework.
     * 
     * @return GeoService
     * @see GeoService
     */
    @Bean
    public GeoService geoService() {
        return (new GeoServiceImpl());
    }
    
    /**
     * Returns an instance of the PostalCodeService JAX-RS web service. 
     * Method is called by the Spring framework.
     * 
     * @return PostalCodeService
     * @see PostalCodeService
     */
    @Bean
    public PostalCodeService postalCodeService() {
        return (new PostalCodeServiceImpl());
    }
}
