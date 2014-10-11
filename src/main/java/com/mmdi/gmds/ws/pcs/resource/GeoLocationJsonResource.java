/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmdi.gmds.ws.pcs.resource;

import com.mmdi.gmds.ws.pcs.model.GeoLocation;
import com.mmdi.gmds.ws.pcs.model.GeoLocationList;
import com.mmdi.gmds.ws.pcs.model.GeometricLocation;
import com.mmdi.gmds.ws.pcs.model.Location;
import com.mmdi.gmds.ws.pcs.model.PostalCode;
import com.mmdi.gmds.ws.pcs.sax.XMLParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import org.apache.jcs.access.exception.CacheException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author jeremybeckman
 */
public class GeoLocationJsonResource extends JsonResource<Location> {

    private Logger logger = LoggerFactory.getLogger(GeoLocationJsonResource.class);
    private InputStream inputStream = null;
    private List<Location> list = new ArrayList<Location>();
    private String locateCacheRegion = null;

    public GeoLocationJsonResource(String url, String cacheRegion, String locateCacheRegion) {
        super(url, cacheRegion);
        this.locateCacheRegion = locateCacheRegion;
    }

    public List<Location> getLocations() {
        return list;
    }

    @Override
    public Location find(String value) throws CacheException {
        CacheResource cache = new GeoLocationCacheResource();
        String key = value.toUpperCase();
        Location location = (Location) cache.getData(key, cacheRegion);
        return location;
    }

    @Override
    protected void clear() throws CacheException {
        CacheResource cache = new PostalCodeCacheResource();
        cache.clear(cacheRegion);
    }

    @Override
    protected Location[] build() throws IOException {
        connect();
        XMLParser parser = new XMLParser(inputStream);
        parser.parseDocument();
        list.addAll(parser.getData());
        Location[] locations = list.toArray(new Location[list.size()]);
        disconnect();
        return locations;
    }

    @Override
    protected void persist(Location[] data) {
        try {
            for (Location location : data) {
                locateFromCache(location);
            }

            GeoLocationCacheResource cache = new GeoLocationCacheResource();
            cache.putData(data, cacheRegion);
        } catch (CacheException ce) {
            logger.error(ce.getMessage());
        }
    }

    private void connect() {
        try {
            inputStream = new FileInputStream(new File(path));
        } catch (IOException ioe) {
            logger.error(ioe.getMessage());
        }
    }

    private void disconnect() {
        try {
            inputStream.close();
        } catch (IOException ioe) {
            logger.error(ioe.getMessage());
        }
    }

    private void locateFromCache(Location location) {
        try {
            String pc = location.getPostalCode();
            if (pc != null) {
                StringBuilder builder = new StringBuilder(pc);
                Pattern pattern = Pattern.compile("\\s");
                Matcher matcher = pattern.matcher(pc);

                while (matcher.find()) {
                    builder.replace(matcher.start(), matcher.end(), "");
                }

                CacheResource cache = new PostalCodeCacheResource();
                PostalCode postalCode = (PostalCode) cache.getData(builder.toString(), locateCacheRegion);
                if (postalCode != null) {
                    location.setLat(postalCode.getLat());
                    location.setLng(postalCode.getLong());
                }
            }
        } catch (CacheException ce) {
            logger.error(ce.getMessage());
        }
    }
}
