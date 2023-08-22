package com.ayesh.webapp.config;

import com.ayesh.webapp.service.UserService;
import com.ayesh.webapp.util.JwtTokenUtil;
import jakarta.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class DependencyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        System.out.println("DependencyBinder....");
        bind(JwtTokenUtil.class).to(JwtTokenUtil.class).in(Singleton.class);
        bind(UserService.class).to(UserService.class).in(Singleton.class);
    }
}
