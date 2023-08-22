package com.ayesh.webapp.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import org.glassfish.jersey.server.mvc.Viewable;

@Path("/test")
public class TestController {
    @GET
    public String adminPanel(@Context HttpHeaders httpHeaders){
        String token = httpHeaders.getHeaderString("Authorization");
        return token;
//        return new Viewable("/admin-panel");
    }
}
