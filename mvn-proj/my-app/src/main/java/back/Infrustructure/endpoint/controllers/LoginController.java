package back.Infrustructure.endpoint.controllers;

import back.Infrustructure.Builder;
import back.Infrustructure.User;
import back.app.IApp;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;


import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    @GET
    @Produces("text/plain")

    public Response login(@QueryParam("login") String login, @QueryParam("password") String password) {
        try {
            Builder builder = new Builder();
            IApp app = builder.buildApp();
            String token = app.login(login, password);
            if (token != null) {
                return Response.ok(token).build();
            } else {
                throw new Exception("invalid credentials");
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public Response register(String strJSON) {
        Jsonb jsonb = JsonbBuilder.create();
        try {
            Builder builder = new Builder();
            IApp app = builder.buildApp();
            User user = jsonb.fromJson(strJSON, User.class);
            String token = app.register(user);
            if (token != null) {
                return Response.ok(token).build();
            } else {
                throw new Exception("invalid credentials");
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
