package ru.yank0vy3rdna.soa.lab3.crud_logic.services;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.yank0vy3rdna.soa.lab3.crud_logic.dao.WorkerDAO;
import ru.yank0vy3rdna.soa.lab3.common.models.Status;
import ru.yank0vy3rdna.soa.lab3.common.models.Worker;
import ru.yank0vy3rdna.soa.lab3.common.models.WorkerInput;
import ru.yank0vy3rdna.soa.lab3.crud_logic.utils.Database;
import org.jboss.ejb3.annotation.Pool;

import java.util.List;

@Stateless(name = "WorkerService")
@Remote(WorkerService.class)
@Dependent
@Pool("worker-service")
public class WorkerServiceImpl implements WorkerService {

    @Inject
    private WorkerDAO workerDAO;

    @Inject
    private Database database;


    public Worker createWorker(WorkerInput body) {
        Session session = database.getSession();
        Transaction transaction = session.beginTransaction();
        Worker worker;
        try {
            worker = workerDAO.createWorker(session, body);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return worker;
    }

    public void deleteWorker(Long workerId) {
        Session session = database.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            workerDAO.deleteWorker(session, workerId);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    public void deleteWorkerByStatus(Status status) {
        Session session = database.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Worker> workers = workerDAO.getWorkersByStatus(session, status, 1, 1);
            if (workers.size() == 0) {
                transaction.commit();
                return;
            }
            workerDAO.deleteWorker(session, workers.get(0).getId());
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    public Worker getWorker(Long workerId) {
        Session session = database.getSession();
        Transaction transaction = session.beginTransaction();
        Worker worker;
        try {
            worker = workerDAO.getWorkerById(session, workerId);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return worker;
    }

    public Worker getWorkerWithMaxOrganization() {
        Session session = database.getSession();
        Transaction transaction = session.beginTransaction();
        Worker worker;
        try {
            worker = workerDAO.getWorkerWithMaxOrg(session);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return worker;
    }

    public List<Worker> listOfWorkers(
            List<String> sortBy,
            List<String> filterQuery,
            Integer page,
            Integer limit
    ) {
        Session session = database.getSession();
        Transaction transaction = session.beginTransaction();
        List<Worker> workersList;
        try {
            workersList = workerDAO.getWorkers(session, page, limit, filterQuery, sortBy);
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        transaction.commit();
        return workersList;
    }

    public List<Worker> listOfWorkersByStatus(
            Status status,
            Integer page,
            Integer limit
    ) {
        Session session = database.getSession();
        Transaction transaction = session.beginTransaction();
        List<Worker> workers;
        try {
            workers = workerDAO.getWorkersByStatus(session, status, page, limit);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return workers;
    }

    public Worker updateWorker(
            WorkerInput body,
            Long workerId
    ) {
        Session session = database.getSession();
        Transaction transaction = session.beginTransaction();
        Worker worker;
        try {
            worker = workerDAO.updateWorker(session, body, workerId);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return worker;
    }
}
