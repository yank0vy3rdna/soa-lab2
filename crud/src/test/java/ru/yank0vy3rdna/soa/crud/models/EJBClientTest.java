//package ru.yank0vy3rdna.soa.crud.models;
//
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Test;
//import ru.yank0vy3rdna.soa.lab3.common.models.Coordinates;
//import ru.yank0vy3rdna.soa.lab3.common.models.Worker;
//import ru.yank0vy3rdna.soa.lab3.common.models.WorkerInput;
//import ru.yank0vy3rdna.soa.lab3.crud_logic.services.WorkerService;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//class EJBClientTest {
//    private WorkerService GetWorkerService() throws NamingException {
//        Properties jndiProperties = new Properties();
//        jndiProperties.put(Context.SECURITY_PRINCIPAL, "user");
//        jndiProperties.put(Context.SECURITY_CREDENTIALS, "qwerty123");
//
//        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
//        jndiProperties.put("jboss.naming.client.ejb.context", true);
//        final Context context = new InitialContext(jndiProperties);
//
//        return (WorkerService) context.lookup("ejb:/crud-logic-1.0-SNAPSHOT-jar-with-dependencies/WorkerServiceImpl!ru.yank0vy3rdna.soa.lab3.crud_logic.services.WorkerService");
//    }
//
//    @SneakyThrows
//    @Test
//    void serialize() {
//        List<Worker> workers = GetWorkerService().listOfWorkers(new ArrayList<>(), new ArrayList<>(), 1, 30);
//        System.out.println();
//    }
//    @SneakyThrows
//    @Test
//    void createWorker() {
//        WorkerInput workerInput = new WorkerInput();
//        workerInput.setName("gloa");
//        workerInput.setCoordinates(new Coordinates());
////        Worker worker = GetWorkerService().createWorker(workerInput);
////        System.out.println("Worker created: " + worker.getId());
//    }
//
//
//}
