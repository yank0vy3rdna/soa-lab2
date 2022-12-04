package ru.yank0vy3rdna.hr.services;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yank0vy3rdna.hr.models.Worker;
import ru.yank0vy3rdna.hr.models.WorkerInput;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class CrudService {
    private final RestTemplate restTemplate;

    @Value("${crud.base_url}")
    private String crudBaseUrl;

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
            return this.restTemplate.patchForObject(this.crudBaseUrl + "/workers?workerId={workerId}", request, Worker.class, urlParams);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public Worker getById(Long workerId) {
        Map<String, Long> urlParams = new HashMap<>();
        urlParams.put("workerId", workerId);
        return this.restTemplate.getForObject(crudBaseUrl + "/workers/{workerId}", Worker.class, urlParams);
    }
}
