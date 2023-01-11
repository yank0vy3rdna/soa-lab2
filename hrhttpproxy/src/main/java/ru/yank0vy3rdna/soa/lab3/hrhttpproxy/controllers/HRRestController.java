package ru.yank0vy3rdna.soa.lab3.hrhttpproxy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.yank0vy3rdna.soa.lab3.common.models.Status;
import ru.yank0vy3rdna.soa.lab3.common.models.Worker;
import ru.yank0vy3rdna.soa.lab3.common.models.WorkerInput;
import ru.yank0vy3rdna.soa.lab3.hrhttpproxy.exceptions.NotFoundException;
import ru.yank0vy3rdna.soa.lab3.hrhttpproxy.services.WsClient;

@RestController
@RequestMapping("/")
//@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PATCH}, allowCredentials = "true")
public class HRRestController {
    private final WsClient wsClient;

    public HRRestController(WsClient wsClient) {
        this.wsClient = wsClient;
    }

    @PostMapping(path = "index/{workerId}/{coeff}", produces = "application/xml")
    public ResponseEntity<?> index(@PathVariable Double coeff, @PathVariable Long workerId) {
        this.wsClient.index(coeff, workerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "fire/{workerId}", produces = "application/xml")
    public ResponseEntity<?> fire(@PathVariable Long workerId) {
        this.wsClient.fire(workerId);
        return ResponseEntity.ok().build();
    }
}