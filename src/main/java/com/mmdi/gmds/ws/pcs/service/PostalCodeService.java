package com.mmdi.gmds.ws.pcs.service;

import javax.ws.rs.core.Response;

/**
 * Interface for the postal code web service implementation.
 * 
 * @author Jeremy Beckman
 * version 1.0
 */
public interface PostalCodeService {
    Response lookup(String code);
    Response refresh();
}
