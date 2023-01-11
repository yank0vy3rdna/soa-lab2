package ru.yank0vy3rdna.soa.lab3.hr.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import ru.yank0vy3rdna.soa.lab3.common.models.Status;
import ru.yank0vy3rdna.soa.lab3.common.models.Worker;
import ru.yank0vy3rdna.soa.lab3.common.models.WorkerInput;
import ru.yank0vy3rdna.soa.lab3.hr.exceptions.NotFoundException;
import ru.yank0vy3rdna.soa.lab3.hr.services.CrudService;
import ru.yank0vy3rdna.soa.lab3.hr.xsd.FireRequest;
import ru.yank0vy3rdna.soa.lab3.hr.xsd.IndexRequest;

@Endpoint
public class HREndpoint {
    public static final String NAMESPACE_URI = "http://soa.yank0vy3rdna.ru/services/hr";
    private final CrudService crudService;

    public HREndpoint(CrudService crudService) {
        this.crudService = crudService;
    }

    @PayloadRoot(localPart = "indexRequest", namespace = NAMESPACE_URI)
    public void index(@RequestPayload IndexRequest indexRequest) {
        try {
            Worker workerFromCrud = crudService.getById(indexRequest.getWorkerId());
            WorkerInput worker = new WorkerInput();
            worker.setSalary(Math.toIntExact(Math.round(workerFromCrud.getSalary() * indexRequest.getCoeff())));
            this.crudService.update(indexRequest.getWorkerId(), worker);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404)
                throw new NotFoundException();
            throw e;
        }
    }

    @PayloadRoot(localPart = "fireRequest", namespace = NAMESPACE_URI)
    public void fire(@RequestPayload FireRequest fireRequest) {
        try {
            WorkerInput worker = new WorkerInput();
            worker.setStatus(Status.FIRED);
            this.crudService.update(fireRequest.getWorkerId(), worker);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404)
                throw new NotFoundException();
            throw e;
        }
    }
}
