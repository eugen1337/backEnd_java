package Infrustructure.endpoint.controllers;

import Infrustructure.Builder;
import Infrustructure.endpoint.Request;
import app.IApp;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/calc")
public class CalcController {
    @GET
    @Path("/")
    @Produces("application/json")
    public Response calc(@QueryParam("a") int a, @QueryParam("b") int b) {
        Jsonb jsonb = JsonbBuilder.create();
        try {
            IApp app = Builder.buildApp();
            String resultJSON;
            String resp = app.calc(a, b);
            if (resp != null) {
                resultJSON = jsonb.toJson(resp);
                return Response.ok(resultJSON).build();
            } else {
                throw new Exception("invalid credentials");
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonb.toJson("Error login")).build();
        }
    }
}
