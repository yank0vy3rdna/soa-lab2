package ru.yank0vy3rdna.soa.crud;


import jakarta.ws.rs.*;
import ru.yank0vy3rdna.soa.lab3.common.models.Status;
import ru.yank0vy3rdna.soa.lab3.common.models.Worker;
import ru.yank0vy3rdna.soa.lab3.common.models.WorkerInput;
import ru.yank0vy3rdna.soa.lab3.common.models.Workers;
import ru.yank0vy3rdna.soa.lab3.crud_logic.services.WorkerService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Properties;

@Path("/workers")
public class WorkersApi {
    WorkerService workerService = GetWorkerService();

    public WorkersApi() throws NamingException {
    }


    private WorkerService GetWorkerService() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "user");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "qwerty123");

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        final Context context = new InitialContext(jndiProperties);

        return (WorkerService) context.lookup("ejb:/crud-logic-1.0-SNAPSHOT-jar-with-dependencies/WorkerServiceImpl!ru.yank0vy3rdna.soa.lab3.crud_logic.services.WorkerService");
    }


    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response createWorker(WorkerInput body) {
        Worker worker = workerService.createWorker(body);
        return Response.ok().entity(worker).build();
    }

    @DELETE
    public Response deleteWorker(@QueryParam("workerId") Long workerId) {
        workerService.deleteWorker(workerId);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete-by-status")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteWorkerByStatus(@QueryParam("status") Status status) {
        workerService.deleteWorkerByStatus(status);
        return Response.ok().build();
    }

    @GET
    @Path("/{workerId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getWorker(@PathParam("workerId") Long workerId) {
        Worker worker = workerService.getWorker(workerId);
        if (worker == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(worker).build();
    }

    @GET
    @Path("/max-organization")
    @Produces(MediaType.APPLICATION_XML)

    public Response getWorkerWithMaxOrganization() {
        Worker worker = workerService.getWorkerWithMaxOrganization();
        if (worker == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(worker).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)

    public Response listOfWorkers(@QueryParam("sort_by") List<String> sortBy, @QueryParam("filter") List<String> filterQuery, @QueryParam("page") @DefaultValue("1") Integer page, @QueryParam("limit") @DefaultValue("30") Integer limit) {
        List<Worker> workersList = workerService.listOfWorkers(sortBy, filterQuery, page, limit);
        return Response.ok().entity(new Workers(workersList)).build();
    }

    @GET
    @Path("/list-by-status/{status}")
    @Produces(MediaType.APPLICATION_XML)
    public Response listOfWorkersByStatus(@PathParam("status") Status status, @QueryParam("page") @DefaultValue("1") Integer page, @QueryParam("limit") @DefaultValue("30") Integer limit) {
        List<Worker> workers = workerService.listOfWorkersByStatus(status, page, limit);
        return Response.ok().entity(new Workers(workers)).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateWorker(WorkerInput body, @QueryParam("workerId") Long workerId) {
        Worker worker = workerService.updateWorker(body, workerId);
        if (worker == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(worker).build();
    }
}
