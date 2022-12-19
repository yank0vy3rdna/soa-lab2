package ru.yank0vy3rdna.soa.crud;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Startup;
import jakarta.ejb.Singleton;
import lombok.SneakyThrows;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Collections;

@Singleton
@Startup
public class ConsulConfig {
    String serviceId = "crud";
    Consul client;
    AgentClient agentClient;

    @SneakyThrows
    @PostConstruct
    void init() {
        client = Consul.builder().withHostAndPort(HostAndPort.fromParts("consul", 8500)).build();
        agentClient = client.agentClient();

        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            Registration service = ImmutableRegistration.builder()
                    .id(serviceId)
                    .name(serviceId)
                    .port(8080)
                    .address(socket.getLocalAddress().getHostAddress())
                    .check(Registration.RegCheck.http("http://"+socket.getLocalAddress().getHostAddress()+":8080/healthcheck", 10, 1))
                    .tags(Collections.singletonList("crud-service"))
                    .meta(Collections.singletonMap("version", "1.0"))
                    .build();
            agentClient.register(service);
        }
    }

    @PreDestroy
    private void deregisterService() {
        agentClient.deregister(serviceId);
    }

}