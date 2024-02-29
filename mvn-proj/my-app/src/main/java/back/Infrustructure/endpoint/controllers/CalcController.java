package back.Infrustructure.endpoint.controllers;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;

import back.Infrustructure.Builder;
import back.Infrustructure.Built;
import back.app.IApp;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/calc")
public class CalcController {
    @Inject
    @Built
    private IApp app;

    @Context
    private HttpHeaders headers;

    @GET
    @Produces("text/plain")
    public Response calc(@QueryParam("id") int id, @QueryParam("a") int a, @QueryParam("b") int b) {
        try {
            String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");

            if (!app.validateToken(token)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Ошибка авторизации").build();
            }

            String resultJSON;
            String resp = app.calc(id, a, b);
            if (resp != null) {
                resultJSON = resp;
                return Response.ok(resultJSON).build();
            } else {
                throw new Exception("invalid credentials");
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
