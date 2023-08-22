package com.ayesh.webapp.controllers;


import com.ayesh.webapp.dto.AuthResponseDTO;
import com.ayesh.webapp.dto.LoginDTO;
import com.ayesh.webapp.entity.User;
import com.ayesh.webapp.entity.UserType;
import com.ayesh.webapp.model.UserDetails;
import com.ayesh.webapp.service.UserService;
import com.ayesh.webapp.util.HibernateUtil;
import com.ayesh.webapp.util.JwtTokenUtil;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.*;
import org.apache.catalina.WebResource;
import org.apache.tomcat.util.http.parser.Authorization;
import org.glassfish.jersey.server.mvc.Viewable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.net.URI;
import java.util.Date;

import static jakarta.ws.rs.client.Client.*;

@Path("/login")
public class LoginController {

    @Inject
    private JwtTokenUtil jwtTokenUtil;

    @Inject
    private UserService userService;

    @Context
    HttpServletResponse httpServletResponse;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) {

        String mobile = loginDTO.getMobile();
        String password = loginDTO.getPassword();

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query<User> query = session.createQuery("select u from User u where u.mobile=:mobile and u.password=:password", User.class);
        query.setParameter("mobile",mobile);
        query.setParameter("password",password);

        try {
            User user = query.getSingleResult();

            if(user!=null){
//||||||||||||| GENERATE TOKEN ||||||||||||||

                UserDetails userDetails = userService.getUserByMobile(user.getMobile());
                String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
                String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
                Date expireDate = jwtTokenUtil.getExpireDateFromToken(accessToken);

                AuthResponseDTO authResponseDTO = new AuthResponseDTO();
                authResponseDTO.setAccessToken(accessToken);
                authResponseDTO.setRefreshToken(refreshToken);
                authResponseDTO.setExpireIn(expireDate.toString());

                Query<UserType> query1 = session.createQuery("select user_type from UserType user_type where user_type.id=:user_type_id", UserType.class);
                UserType userType = query1.setParameter("user_type_id", user.getUserType().getId()).uniqueResult();

                Cookie cookie = new Cookie("token",accessToken);
                httpServletResponse.addCookie(cookie);


                if (userType.getName().equals("Admin")){
                    return Response.status(Response.Status.CREATED).entity("Admin").header("Authorization","Bearer "+accessToken).build();
//                    return Response.ok().entity("admin-panel").build();
                } else if (userType.getName().equals("HR Manager")) {
                    return Response.status(Response.Status.CREATED).entity("HR-Manager").header("Authorization","Bearer "+accessToken).build();
                }else{
                    return Response.status(Response.Status.UNAUTHORIZED).entity("UnAuthorized User").build();
                }

//                return Response.ok().entity(authResponseDTO.getAccessToken()).build();

            }else{
                return Response.status(Response.Status.UNAUTHORIZED).entity("Check you Login Details Again!").build();
            }
        }catch (NoResultException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


    @Path("refresh-token")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response refreshToken(@FormParam("refreshToken") String refreshToken) {
        UserDetails userDetails = userService.getUserByMobile(jwtTokenUtil.getUsernameFromToken(refreshToken));
        if (!jwtTokenUtil.validateToken(refreshToken, userDetails)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid refresh token").build();
        } else {
            String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
            Date expireDate = jwtTokenUtil.getExpireDateFromToken(accessToken);

            AuthResponseDTO dto = new AuthResponseDTO();
            dto.setAccessToken(accessToken);
            dto.setRefreshToken(refreshToken);
            dto.setExpireIn(expireDate.toString());

            return Response.ok().entity(dto).build();
        }
    }
}
