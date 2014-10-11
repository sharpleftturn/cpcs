package com.mmdi.gmds.ws.pcs.service;

import javax.ws.rs.core.Response;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author Jeremy Beckman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.mmdi.gmds.ws.pcs.config.WSConfiguration.class, com.mmdi.gmds.ws.pcs.config.ResourceConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public final class PostalCodeServiceTest {

    @Autowired
    public PostalCodeService postalCodeService;
    private static final Logger logger = LoggerFactory.getLogger(PostalCodeServiceTest.class);
    private String code = "M4C4B1";

    @Test
    public void lookupIsNotNull() {
        assertNotNull(postalCodeService.lookup(code));
    }
    
    @Test
    public void refreshReturnsOk() {
        assertEquals(Response.ok().build().getStatus(), postalCodeService.refresh().getStatus());
    }
}
