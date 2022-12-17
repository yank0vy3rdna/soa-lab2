package ru.yank0vy3rdna.soa.crud.models;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import ru.yank0vy3rdna.soa.lab3.crud_logic.services.HelloWorld;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

class EJBClientTest {
    private HelloWorld GetWorkerService() throws NamingException {
        Properties jndiProperties = new Properties();

//        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");

//        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, WildFlyInitialContextFactory.class.getName());

//        jndiProperties.put(Context.PROVIDER_URL, "remote+http://127.0.0.1:8080");
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "user");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "qwerty123");

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
//        WildFlyInitialContextFactory factory = new InitialContext();
        final Context context = new InitialContext(jndiProperties);
//        final Context context = new InitialContext(jndiProperties);

        return (HelloWorld) context.lookup("ejb:/crud-logic-1.0-SNAPSHOT-jar-with-dependencies/HelloWorldImpl!ru.yank0vy3rdna.soa.lab3.crud_logic.services.HelloWorld");
    }

    @SneakyThrows
    @Test
    void serialize() {
        System.out.println(GetWorkerService().hi());
    }


}
