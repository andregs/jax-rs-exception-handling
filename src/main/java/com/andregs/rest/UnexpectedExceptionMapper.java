package com.andregs.rest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This is our most generic exception mapper. It catches all unexpected exceptions and builds an
 * HTTP 500 - Internal Server Error response.
 */
@Provider
public class UnexpectedExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(UnexpectedExceptionMapper.class.getName());
    
    @Override
    public Response toResponse(Exception exception) {
        LOG.log(Level.SEVERE, exception.getMessage(), exception);
        return Response.serverError().entity("").build();
    }
    
}
