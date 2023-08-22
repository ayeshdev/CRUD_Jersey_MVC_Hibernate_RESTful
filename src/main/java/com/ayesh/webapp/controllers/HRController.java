package com.ayesh.webapp.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.glassfish.jersey.server.mvc.Viewable;

@Path("/hr-panel")
public class HRController {
    @GET
    public Viewable hrPanel(){
        return new Viewable("/hr-panel");
    }
}
