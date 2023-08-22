package com.ayesh.webapp.middleware;

import com.ayesh.webapp.model.UserDetails;
import com.ayesh.webapp.service.UserService;
import com.ayesh.webapp.util.JwtTokenUtil;
import io.fusionauth.jwt.JWTExpiredException;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

@Provider
@Priority(1)
public class JwtTokenFilter implements ContainerRequestFilter {

    @Inject
    private JwtTokenUtil jwtTokenUtil;

    @Inject
    private UserService userService;


    @Context
    HttpServletResponse httpServletResponse;

    @Context
    HttpServletRequest httpServletRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();


//        Enumeration<String> authorization = httpServletRequest.getHeaders("Authorization");
//        System.out.println(authorization);

        if (path.equals("") || path.equals("login")) {
            System.out.println("This is the token...................");
            return;
        }

        Cookie[] cookies = httpServletRequest.getCookies();

        Boolean isCookie = false;
        String tokenFromCookie = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    tokenFromCookie = cookie.getValue();
                    isCookie = true;
                }
            }
        }

        if (isCookie) {
            String token = tokenFromCookie;
            try {
                UserDetails userDetails = userService.getUserByMobile(jwtTokenUtil.getUsernameFromToken(token));

                if (!jwtTokenUtil.validateToken(token, userDetails)) {
                    System.out.println("error1........");
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            } catch (JWTExpiredException | NullPointerException ex) {
                System.out.println("error2........");

                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Your Token is Expired!").build());
            } catch (Exception ex) {
                System.out.println("error3........");
                System.out.println(ex);
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

//        if (httpServletResponse.getHeaders("Authorization") == null) {
//            System.out.println("error0........");
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//        } else {
//
//            //         |||||||||||||||| GET TOKEN ||||||||||||
////            System.out.println(httpServletResponse);
//            Collection<String> token = httpServletResponse.getHeaders("Authorization");
//            System.out.println(token.toString());
//
//            try {
//                UserDetails userDetails = userService.getUserByMobile(jwtTokenUtil.getUsernameFromToken(token.toString()));
//
//                if (!jwtTokenUtil.validateToken(token.toString(), userDetails)) {
//                    System.out.println("error1........");
//                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//                }
//            } catch (JWTExpiredException | NullPointerException ex) {
//                System.out.println("error2........");
//
//                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Your Token is Expired!").build());
//            } catch (Exception ex) {
//                System.out.println(ex);
//                System.out.println("error3........");
//
//                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//            }

    }
}

