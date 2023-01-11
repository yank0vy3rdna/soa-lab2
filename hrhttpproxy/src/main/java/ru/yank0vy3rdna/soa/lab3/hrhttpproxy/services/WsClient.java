package ru.yank0vy3rdna.soa.lab3.hrhttpproxy.services;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import ru.yank0vy3rdna.soa.lab3.hrhttpproxy.xsd.FireRequest;
import ru.yank0vy3rdna.soa.lab3.hrhttpproxy.xsd.IndexRequest;
import ru.yank0vy3rdna.soa.lab3.hrhttpproxy.xsd.ObjectFactory;

@Component
public class WsClient {
    private final WebServiceTemplate webServiceTemplate;

    public WsClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public void index(Double coeff, Long workerId) {
        ObjectFactory objectFactory = new ObjectFactory();
        IndexRequest indexRequest = objectFactory.createIndexRequest();
        indexRequest.setCoeff(coeff.floatValue());
        indexRequest.setWorkerId(workerId);
        webServiceTemplate.marshalSendAndReceive(indexRequest);
    }

    public void fire(Long workerId) {
        ObjectFactory objectFactory = new ObjectFactory();
        FireRequest fireRequest = objectFactory.createFireRequest();
        fireRequest.setWorkerId(workerId);
        webServiceTemplate.marshalSendAndReceive(fireRequest);
    }
}
