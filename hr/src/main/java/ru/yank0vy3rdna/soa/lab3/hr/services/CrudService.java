package ru.yank0vy3rdna.soa.lab3.hr.services;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yank0vy3rdna.soa.lab3.common.models.Worker;
import ru.yank0vy3rdna.soa.lab3.common.models.WorkerInput;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.yank0vy3rdna.soa.lab3.hr.ribbon.RibbonClientConfig;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
@RibbonClient(name = "crud", configuration = RibbonClientConfig.class)
public class CrudService {
    private final RestTemplate restTemplate;

    public CrudService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Worker update(Long workerId, WorkerInput input) {
        Map<String, Long> urlParams = new HashMap<>();
        urlParams.put("workerId", workerId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(WorkerInput.class);
            Marshaller m = context.createMarshaller();
            m.marshal(input, writer);
            headers.setContentLength(writer.toString().length());

            HttpEntity<String> request = new HttpEntity<>(writer.toString(), headers);
            return this.restTemplate.patchForObject("http://crud:8080/workers?workerId={workerId}", request, Worker.class, urlParams);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public Worker getById(Long workerId) {
        Map<String, Long> urlParams = new HashMap<>();
        urlParams.put("workerId", workerId);
        return this.restTemplate.getForObject("http://crud:8080/workers/{workerId}", Worker.class, urlParams);
    }
}

