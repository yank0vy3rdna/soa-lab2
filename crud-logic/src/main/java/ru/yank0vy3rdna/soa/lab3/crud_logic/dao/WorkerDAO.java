package ru.yank0vy3rdna.soa.lab3.crud_logic.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.SneakyThrows;
import org.hibernate.Session;
import ru.yank0vy3rdna.soa.lab3.common.models.Status;
import ru.yank0vy3rdna.soa.lab3.common.models.Worker;
import ru.yank0vy3rdna.soa.lab3.common.models.WorkerInput;
import ru.yank0vy3rdna.soa.lab3.crud_logic.utils.FilterParser;
import ru.yank0vy3rdna.soa.lab3.crud_logic.utils.SortByParser;

import java.util.List;

@ApplicationScoped
public class WorkerDAO {
    @Inject
    private SortByParser sortByParser;
    @Inject
    private FilterParser filterParser;

    public Worker createWorker(Session session, WorkerInput workerInput) {
        Worker obj = workerInput.convert();
        session.persist(obj);
        return obj;
    }

    public Worker updateWorker(Session session, WorkerInput workerInput, Long workerId) {
        Worker worker = this.getWorkerById(session, workerId);
        if (worker == null)
            return null;
        worker = workerInput.update(worker);
        session.persist(worker);
        return worker;
    }

    public void deleteWorker(Session session, Long workerId) {
        session.createQuery("delete from worker where id=:id").setParameter("id", workerId).executeUpdate();
    }

    public Worker getWorkerById(Session session, Long workerId) {
        return (Worker) session.createQuery("from worker where id=:id").setParameter("id", workerId).getSingleResult();
    }

    public Worker getWorkerWithMaxOrg(Session session) {
        return session.createNativeQuery("select * from workers order by organization->>'fullName' desc limit 1", Worker.class).getSingleResult();
    }

    @SneakyThrows
    public List<Worker> getWorkers(Session session, int page, int limit, List<String> filters, List<String> sortBy) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Worker> query = cb.createQuery(Worker.class);
        Root<Worker> root = query.from(Worker.class);
        query = query.select(root).orderBy(this.sortByParser.parse(cb, root, sortBy));
        List<Predicate> predicates = this.filterParser.parse(cb, root, filters);
        for (Predicate predicate : predicates) {
            query = query.where(predicate);
        }
        return session.createQuery(query).setFirstResult((page - 1) * limit).setMaxResults(limit).getResultList();
    }

    public List<Worker> getWorkersByStatus(Session session, Status status, Integer page, Integer limit) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Worker> query = cb.createQuery(Worker.class);
        Root<Worker> root = query.from(Worker.class);
        query.select(root).where(cb.equal(root.get("status"), status));
        return session.createQuery(query).setMaxResults(limit).setFirstResult(limit * (page - 1)).getResultList();
    }
}
