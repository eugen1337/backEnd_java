package back.Infrustructure.endpoint.controllers;

import back.Infrustructure.Message;
import back.Infrustructure.Task;
import back.Infrustructure.Builder;
import back.app.IApp;
import jakarta.ws.rs.core.Response;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;

@Path("/tasks")
public class TasksController {

    @GET
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

    @Path("/create")
    @POST
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
    @Produces("application/json")
    @Consumes("application/json")    
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
