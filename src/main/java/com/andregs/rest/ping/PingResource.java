package com.andregs.rest.ping;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * 
 */
@Path("ping")
public class PingResource {

    @GET
    public String ping() {
        return "Enjoy Java EE 8! " + System.currentTimeMillis();
    }

    /**
     * You can play with this endpoint to test the exception mappers.
     * 
     * If you try to post credentials without username or password, then the resulting exception
     * will be caught by ValidationExceptionMapper.
     * 
     * If you try to post invalid JSON, then the resulting exception will be caught by
     * JsonProcessingExceptionMapper.
     */
    @POST
    @Path("boom")
    @Consumes(MediaType.APPLICATION_JSON)
    public String boom(@Valid Credentials credentials) {
//        throw new ForbiddenException("this will be caught by WebApplicationExceptionMapper");
        throw new RuntimeException("this will be caught by UnexpectedExceptionMapper");
//        return "Try to post invalid credentials! " + System.currentTimeMillis();
    }
    
}
