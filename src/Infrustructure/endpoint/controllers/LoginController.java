package Infrustructure.endpoint.controllers;

import Infrustructure.Builder;
import Infrustructure.User;
import app.IApp;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;


import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    @GET
    @Path("/")
    @Produces("text/plain")

    public Response login(@QueryParam("login") String login, @QueryParam("password") String password) {
        try {
            IApp app = Builder.buildApp();
            String resp;
            String resultJSON;
            resp = app.login(login, password);
            if (resp != null) {
                resultJSON = resp;
                System.out.println(resultJSON);
                return Response.ok(resultJSON).build();
            } else {
                throw new Exception("invalid credentials");
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error login").build();
        }
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response register(String strJSON) {
        Jsonb jsonb = JsonbBuilder.create();
        try {
            IApp app = Builder.buildApp();
            String resp;
            String resultJSON;
            User user = jsonb.fromJson(strJSON, User.class);
            resp = app.register(user);
            if (resp != null) {
                resultJSON = resp;
                return Response.ok(resultJSON).build();
            } else {
                throw new Exception("invalid credentials");
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error login").build();
        }
    }

}
