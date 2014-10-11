/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmdi.gmds.ws.pcs.resource;

import com.mmdi.gmds.ws.pcs.model.PostalCode;
import org.apache.jcs.access.exception.CacheException;
import org.junit.*;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jeremy.beckman
 */
public class PostalCodeCacheResourceTest {

    private static final Logger logger = LoggerFactory.getLogger(PostalCodeCacheResourceTest.class);
    private PostalCode[] postalCodes;
    private CacheResource cache;
    private String code = "M4C4B1";
    private String region = "default";
    
    public PostalCodeCacheResourceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        postalCodes = new PostalCode[1];
        PostalCode postalCode = new PostalCode();
        postalCode.setCity("Toronto");
        postalCode.setCode(code);
        postalCode.setLat(43.6840615);
        postalCode.setLong(-79.31347);
        postalCode.setRegion("ON");
        postalCode.setZone(null);
        postalCodes[0] = postalCode;

        cache = new PostalCodeCacheResource();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void putDataFromPostalCodeArray() {
        try {
            cache.putData(postalCodes, region);
            assertEquals(postalCodes[0], (PostalCode)cache.getData(code, region));
        } catch (CacheException ce) {
            logger.error(ce.getMessage());
        }
    }
}
