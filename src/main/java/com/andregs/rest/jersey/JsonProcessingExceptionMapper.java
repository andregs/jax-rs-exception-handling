package com.andregs.rest.jersey;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps JSON processing exceptions to HTTP 400 - Bad Request.
 * 
 * We need to provide this mapper in order to override the ones present in the provided 
 * jersey-media-json-jackson jar.
 * 
 * @see org.glassfish.jersey.jackson.internal.jackson.jaxrs.base.JsonMappingExceptionMapper
 * @see org.glassfish.jersey.jackson.internal.jackson.jaxrs.base.JsonParseExceptionMapper
 */
@Provider
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {

    private static final Logger LOG = Logger.getLogger(JsonProcessingExceptionMapper.class.getName());

    @Override
    public Response toResponse(JsonProcessingException exception) {
        LOG.info(exception.getMessage());
        return Response.status(BAD_REQUEST).entity("").type("text/plain").build();
    }
    
}
