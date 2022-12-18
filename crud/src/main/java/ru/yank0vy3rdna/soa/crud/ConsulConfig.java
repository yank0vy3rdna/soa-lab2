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

import java.util.Collections;

@Singleton
@Startup
public class ConsulConfig {
    String serviceId = "soa-3-1.0-SNAPSHOT";
    Consul client;
    AgentClient agentClient;

    @PostConstruct
    void init() {
        client = Consul.builder().withHostAndPort(HostAndPort.fromParts("consul", 8500)).build();
        agentClient = client.agentClient();
        Registration service = ImmutableRegistration.builder()
                .id(serviceId)
                .name(serviceId)
                .port(8500)
                .address("consul")
                .check(Registration.RegCheck.http("http://wildfly:8080/healthcheck", 10, 1))
                .tags(Collections.singletonList("tag1"))
                .meta(Collections.singletonMap("version", "1.0"))
                .build();

        agentClient.register(service);
    }

    @PreDestroy
    private void deregisterService(){
        agentClient.deregister(serviceId);
    }

}