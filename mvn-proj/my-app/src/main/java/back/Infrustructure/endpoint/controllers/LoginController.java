package back.Infrustructure.endpoint.controllers;

import back.Infrustructure.Builder;
import back.Infrustructure.Built;
import back.Infrustructure.User;
import back.app.IApp;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    @Inject
    @Built
    private IApp app;

    @GET
    public Response login(@QueryParam("login") String login, @QueryParam("password") String password) {
        try {
            System.out.println("Start login");
            // Builder builder = new Builder();
            // System.out.println("get builder");
            // IApp app = builder.buildApp();
            // System.out.println("Built app");
            String resp;
            String resultJSON;
            resp = app.login(login, password);
            if (resp != null) {
                resultJSON = resp;
                System.out.println(resultJSON);
                return Response.ok(resultJSON).build();
            } else {
                throw new Exception("invalid credentials or error in creating token");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Error login").build();
        }
    }

    @Path("/register")
    @POST
    @Consumes("application/json")
    public Response register(String strJSON) {
        Jsonb jsonb = JsonbBuilder.create();
        try {
            // Builder builder = new Builder();
            // IApp app = builder.buildApp();
            String resp;
            String resultJSON;
            User user = jsonb.fromJson(strJSON, User.class);
            resp = app.register(user);
            if (resp != null) {
                resultJSON = resp;
                return Response.ok(resultJSON).build();
            } else {
                throw new Exception("invalid credentials or error in creating token");
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error login").build();
        }
    }

}
