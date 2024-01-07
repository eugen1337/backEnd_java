package Infrustructure.endpoint.controllers;

import Infrustructure.Builder;
import app.IApp;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/calc")
public class CalcController {
    @GET
    @Path("/")
    @Produces("text/plain")
    public Response calc(@QueryParam("a") int a, @QueryParam("b") int b) {
        try {
            IApp app = Builder.buildApp();
            String resultJSON;
            String resp = app.calc(a, b);
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
