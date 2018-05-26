package com.andregs.rest;

import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Handles WebApplicationException because they have a special meaning in JAX-RS apps, since
 * they already wrap the appropriated HTTP response.
 * 
 * @see https://dennis-xlc.gitbooks.io/restful-java-with-jax-rs-2-0-en/cn/part1/chapter7/exception_handling.html
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    private static final Logger LOG = Logger.getLogger(WebApplicationExceptionMapper.class.getName());
    
    @Override
    public Response toResponse(WebApplicationException exception) {
        LOG.info(exception.getMessage());
        Response original = exception.getResponse();
        return Response.fromResponse(original).entity("").build();
    }
    
}
