package com.mmdi.gmds.ws.pcs.resource;

import com.mmdi.gmds.ws.pcs.model.PostalCode;
import org.apache.jcs.access.exception.CacheException;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author jeremy.beckman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.mmdi.gmds.ws.pcs.config.WSConfiguration.class, com.mmdi.gmds.ws.pcs.config.ResourceConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class PostalCodeJsonResourceTest {

    @Autowired
    public PostalCodeJsonResource jsonResource;
    private static final Logger logger = LoggerFactory.getLogger(PostalCodeJsonResourceTest.class);
    private PostalCode postalCode;
    private String code = "M4C4B1";

    public PostalCodeJsonResourceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        postalCode = new PostalCode();
        postalCode.setCity("Toronto");
        postalCode.setCode(code);
        postalCode.setLat(43.6840615);
        postalCode.setLong(-79.31347);
        postalCode.setRegion("ON");
        postalCode.setZone(null);
    }

    @Test
    public void findPostalCode() {
        try {
            PostalCode testPostalCode = jsonResource.find(code);
            assertEquals(postalCode.getCity(), testPostalCode.getCity());
            assertEquals(postalCode.getCode(), testPostalCode.getCode());
            assertEquals(postalCode.getLat(), testPostalCode.getLat());
            assertEquals(postalCode.getLong(), testPostalCode.getLong());
            assertEquals(postalCode.getRegion(), testPostalCode.getRegion());
            assertEquals(postalCode.getZone(), testPostalCode.getZone());
           
        } catch (CacheException ce) {
            logger.error(ce.getMessage());
        }
    }

    @After
    public void tearDown() {
        postalCode = null;
    }
   
}
