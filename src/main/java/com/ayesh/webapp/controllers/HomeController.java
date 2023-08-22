package com.ayesh.webapp.controllers;

import com.ayesh.webapp.entity.Employee;
import com.ayesh.webapp.util.HibernateUtil;
import com.ayesh.webapp.util.HibernateUtil;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.jersey.server.mvc.Viewable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Path("/")
public class HomeController {
    @GET
    public Viewable index() {
        return new Viewable("/login");
    }
}
