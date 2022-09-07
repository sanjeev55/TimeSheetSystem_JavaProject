package com.javaee.javaee2022teamnine.controller;

import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


// this is just for testing
@Path("/register")
public class RegisterController {
    DBConnectionService dbService = new DBConnectionService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        dbService.registerUser(user);
        return Response.status(Response.Status.OK)
                .entity("User Registered Successfully!")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
