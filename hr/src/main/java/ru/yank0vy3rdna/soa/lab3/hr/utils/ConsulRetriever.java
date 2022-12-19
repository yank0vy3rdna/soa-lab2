package ru.yank0vy3rdna.soa.lab3.hr.utils;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.health.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConsulRetriever {
    public List<String> getCrudAddresses() {
        Consul client = Consul.builder().withHostAndPort(HostAndPort.fromParts("localhost", 8500)).build();
        AgentClient agentClient = client.agentClient();
        Map<String, Service> services = agentClient.getServices();
        return services.values().stream().filter(x -> x.getTags().contains("crud-service")).map(service -> "http://" + service.getAddress() + ":" + service.getPort()).collect(Collectors.toList());
    }
}
