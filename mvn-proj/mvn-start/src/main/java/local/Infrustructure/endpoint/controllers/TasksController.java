package Infrustructure.endpoint.controllers;

import Infrustructure.Message;
import Infrustructure.Task;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import Infrustructure.Builder;
import app.IApp;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
@Path("/tasks")
public class TasksController {
    @GET
    @Path("/")
    public Response getTasks(@QueryParam("login") String login) {
        try {
            Builder builder = new Builder();
            IApp app = builder.buildApp();
            String resp;
            String resultJSON;
            resp = app.getTasks(login);
            if (resp != null) {
                resultJSON = resp;
                return Response.ok(resultJSON).build();
            } else {
                throw new Exception("error while getting tasks");
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @POST
    @Path("/")
    public Response createTask(String bodyJSON) {
        Jsonb jsonb = JsonbBuilder.create();
        try {
            Task task = jsonb.fromJson(bodyJSON, Task.class);
            Builder builder = new Builder();
            IApp app = builder.buildApp();
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

    @POST
    @Path("/delete")
    public Response deleteTask(String bodyJSON) {
        Jsonb jsonb = JsonbBuilder.create();
        try {
            Message messg = jsonb.fromJson(bodyJSON, Message.class);
            Builder builder = new Builder();
            IApp app = builder.buildApp();
            int result = app.deleteTask(Integer.parseInt(messg.getText())) ? 1 : -1;
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
