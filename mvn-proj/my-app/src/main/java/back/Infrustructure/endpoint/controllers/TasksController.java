package back.Infrustructure.endpoint.controllers;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;

import back.Infrustructure.Task;
import back.app.IApp;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import back.Infrustructure.Builder;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;

@Path("/tasks")
public class TasksController {
    @Context
    private HttpHeaders headers;

    @GET
    public Response getTasks() {
        try {
            String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");

            Builder builder = new Builder();
            IApp app = builder.buildApp();

            if (!app.validateToken(token)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Ошибка авторизации").build();
            }
            String login = app.getUserInfo(token).get("username");
            String resp = app.getTasks(login);
            if (resp != null) {
                return Response.ok(resp).build();
            } else {
                throw new Exception("error while getting tasks");
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createTask(String bodyJSON) {
        Jsonb jsonb = JsonbBuilder.create();
        try {
            Task task = jsonb.fromJson(bodyJSON, Task.class);
            String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");

            Builder builder = new Builder();
            IApp app = builder.buildApp();

            if (!app.validateToken(token)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Ошибка авторизации").build();
            }

            int result;
            result = app.createTask(task);
            if (result != -1) {
                String resultJSON = String.valueOf(result);
                return Response.ok(resultJSON).build();
            } else {
                throw new Exception("error while creating task");
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    public Response deleteTask(@QueryParam("id") String id) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        Jsonb jsonb = JsonbBuilder.create();
        try {
            Builder builder = new Builder();
            IApp app = builder.buildApp();

            if (!app.validateToken(token)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Ошибка авторизации").build();
            }

            int result = app.deleteTask(Integer.parseInt(id)) ? 1 : -1;
            if (result != -1) {
                String resultJSON = jsonb.toJson(result);
                return Response.ok(resultJSON).build();
            } else {
                throw new Exception("error while deleting task");
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
