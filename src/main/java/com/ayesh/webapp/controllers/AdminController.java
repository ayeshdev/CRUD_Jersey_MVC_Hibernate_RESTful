package com.ayesh.webapp.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.glassfish.jersey.server.mvc.Viewable;

@Path("/admin-panel")
public class AdminController {
    @GET
    public Viewable adminPanel(){
        return new Viewable("/admin-panel");
    }
}
