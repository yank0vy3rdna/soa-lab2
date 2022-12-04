package ru.yank0vy3rdna.soa.crud;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import ru.yank0vy3rdna.soa.crud.models.Worker;
import ru.yank0vy3rdna.soa.crud.models.WorkerInput;
import ru.yank0vy3rdna.soa.crud.services.WorkerDAO;
import ru.yank0vy3rdna.soa.crud.utils.Database;

import java.util.List;

class WorkersApiTest {
    private final WorkerDAO workerDAO = new WorkerDAO();

    @Test
    void testWorkerDAO() {
//        Worker worker = createWorker();
//        listOfWorkers();

    }

    Worker createWorker() {

        WorkerInput workerInput = new WorkerInput();
        workerInput.setName("Lol");

        Session session = Database.getSession();
        Transaction transaction = session.beginTransaction();
        Worker worker = workerDAO.createWorker(session, workerInput);
        transaction.commit();
        return worker;
    }


}