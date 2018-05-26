package com.andregs.rest;

import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.ext.ExceptionMapper;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;


/**
 * Maps validation failures to HTTP 400 - Bad Request.
 * 
 * We need to provide this mapper in order to override the ValidationExceptionMapper in the
 * provided jersey-bean-validation jar.
 * 
 * @see org.glassfish.jersey.server.validation.internal.ValidationExceptionMapper
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger LOG = Logger.getLogger(ValidationExceptionMapper.class.getName());
    
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        LOG.info(exception.getMessage());
        return Response.status(BAD_REQUEST).entity("").type("text/plain").build();
    }
    
}
