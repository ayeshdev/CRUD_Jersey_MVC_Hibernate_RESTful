package com.ayesh.webapp.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

public class AppConfig extends ResourceConfig {
    public AppConfig(){
        packages("com.ayesh.webapp.controllers");
        packages("com.ayesh.webapp.middleware");

        register(DependencyBinder.class);
        register(JspMvcFeature.class);

        property(JspMvcFeature.TEMPLATE_BASE_PATH,"/WEB-INF/views");
    }
}
