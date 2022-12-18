package ru.yank0vy3rdna.soa.lab3.crud_logic.services;

import ru.yank0vy3rdna.soa.lab3.common.models.Status;
import ru.yank0vy3rdna.soa.lab3.common.models.Worker;
import ru.yank0vy3rdna.soa.lab3.common.models.WorkerInput;

import java.util.List;

public interface WorkerService {
    Worker createWorker(WorkerInput body);

    void deleteWorker(Long workerId);

    void deleteWorkerByStatus(Status status);

    Worker getWorker(Long workerId);

    Worker getWorkerWithMaxOrganization();

    List<Worker> listOfWorkers(
            List<String> sortBy,
            List<String> filterQuery,
            Integer page,
            Integer limit
    );

    List<Worker> listOfWorkersByStatus(
            Status status,
            Integer page,
            Integer limit
    );

    Worker updateWorker(
            WorkerInput body,
            Long workerId
    );
}
