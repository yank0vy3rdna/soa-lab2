package ru.yank0vy3rdna.hr.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.yank0vy3rdna.hr.exceptions.NotFoundException;
import ru.yank0vy3rdna.hr.models.Status;
import ru.yank0vy3rdna.hr.models.Worker;
import ru.yank0vy3rdna.hr.models.WorkerInput;
import ru.yank0vy3rdna.hr.services.CrudService;

@RestController
@RequestMapping("/")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PATCH}, allowCredentials = "true")
public class HRRestController {
    private final CrudService crudService;

    public HRRestController(CrudService crudService) {
        this.crudService = crudService;
    }

    @PostMapping(path = "index/{workerId}/{coeff}", produces = "application/xml")
    public ResponseEntity<?> index(@PathVariable Double coeff, @PathVariable Long workerId) {
        try {
            Worker workerFromCrud = crudService.getById(workerId);
            WorkerInput worker = new WorkerInput();
            worker.setSalary(Math.toIntExact(Math.round(workerFromCrud.getSalary() * coeff)));
            this.crudService.update(workerId, worker);
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException();
        } catch (Exception e) {
            Worker worker = new Worker();
            worker.setName(e.getMessage());
            return ResponseEntity.ok(worker);
        }
    }

    @PostMapping(path = "fire/{workerId}", produces = "application/xml")
    public ResponseEntity<?> fire(@PathVariable Long workerId) {
        try {
            WorkerInput worker = new WorkerInput();
            worker.setStatus(Status.FIRED);
            this.crudService.update(workerId, worker);
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException();
        }
    }
}
