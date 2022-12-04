package ru.yank0vy3rdna.soa.crud;


import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.yank0vy3rdna.soa.crud.models.Status;
import ru.yank0vy3rdna.soa.crud.models.Worker;
import ru.yank0vy3rdna.soa.crud.models.WorkerInput;
import ru.yank0vy3rdna.soa.crud.models.Workers;
import ru.yank0vy3rdna.soa.crud.services.WorkerDAO;
import ru.yank0vy3rdna.soa.crud.utils.Database;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/workers")
public class WorkersApi {
    @Inject
    private WorkerDAO workerDAO;

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response createWorker(WorkerInput body) {
        Session session = Database.getSession();
        Transaction transaction = session.beginTransaction();
        Worker worker = workerDAO.createWorker(session, body);
        transaction.commit();
        return Response.ok().entity(worker).build();
    }

    @DELETE
    public Response deleteWorker(@QueryParam("workerId") Long workerId) {
        Session session = Database.getSession();
        Transaction transaction = session.beginTransaction();
        workerDAO.deleteWorker(session, workerId);
        transaction.commit();
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete-by-status")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteWorkerByStatus(@QueryParam("status") Status status) {
        Session session = Database.getSession();
        Transaction transaction = session.beginTransaction();
        List<Worker> workers = workerDAO.getWorkersByStatus(session, status, 1, 1);
        if (workers.size() == 0) {
            transaction.commit();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        workerDAO.deleteWorker(session, workers.get(0).getId());
        transaction.commit();
        return Response.ok().build();
    }

    @GET
    @Path("/{workerId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getWorker(@PathParam("workerId") Long workerId
    ) {
        Session session = Database.getSession();
        Transaction transaction = session.beginTransaction();
        Worker worker = workerDAO.getWorkerById(session, workerId);
        transaction.commit();
        if (worker == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(worker).build();
    }

    @GET
    @Path("/max-organization")
    @Produces(MediaType.APPLICATION_XML)

    public Response getWorkerWithMaxOrganization() {
        Session session = Database.getSession();
        Transaction transaction = session.beginTransaction();
        Worker worker = workerDAO.getWorkerWithMaxOrg(session);
        transaction.commit();
        if (worker == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(worker).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)

    public Response listOfWorkers(
            @QueryParam("sort_by") List<String> sortBy,
            @QueryParam("filter") List<String> filterQuery,
            @QueryParam("page") @DefaultValue("1") Integer page,
            @QueryParam("limit") @DefaultValue("30") Integer limit
    ) {
        Session session = Database.getSession();
        Transaction transaction = session.beginTransaction();
        List<Worker> workersList;
        try {
            workersList = workerDAO.getWorkers(session, page, limit, filterQuery, sortBy);
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        transaction.commit();
        return Response.ok().entity(new Workers(workersList)).build();
    }

    @GET
    @Path("/list-by-status/{status}")
    @Produces(MediaType.APPLICATION_XML)
    public Response listOfWorkersByStatus(
            @PathParam("status") Status status,
            @QueryParam("page") @DefaultValue("1") Integer page,
            @QueryParam("limit") @DefaultValue("30") Integer limit
    ) {

        Session session = Database.getSession();
        Transaction transaction = session.beginTransaction();
        List<Worker> workers = workerDAO.getWorkersByStatus(session, status, page, limit);
        transaction.commit();
        return Response.ok().entity(new Workers(workers)).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateWorker(
            WorkerInput body,
            @QueryParam("workerId") Long workerId
    ) {
        Session session = Database.getSession();
        Transaction transaction = session.beginTransaction();
        Worker worker;
        try {
            worker = workerDAO.updateWorker(session, body, workerId);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        if (worker == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(worker).build();
    }
}
