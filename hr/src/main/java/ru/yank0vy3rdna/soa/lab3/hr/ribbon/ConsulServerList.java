package ru.yank0vy3rdna.soa.lab3.hr.ribbon;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import ru.yank0vy3rdna.soa.lab3.hr.utils.ConsulRetriever;

import java.util.List;
import java.util.stream.Collectors;

public class ConsulServerList implements ServerList<Server> {
    private final ConsulRetriever consulRetriever = new ConsulRetriever();
    public List<Server> prepareListOfServers (){
        return consulRetriever.getCrudAddresses().stream().map(Server::new).collect(Collectors.toList());
    }
    @Override
    public List<Server> getInitialListOfServers() {
        return prepareListOfServers();
    }

    @Override
    public List<Server> getUpdatedListOfServers() {
        return prepareListOfServers();
    }
}
