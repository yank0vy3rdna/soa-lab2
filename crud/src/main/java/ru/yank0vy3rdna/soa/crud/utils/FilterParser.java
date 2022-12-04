package ru.yank0vy3rdna.soa.crud.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.criteria.Path;
import ru.yank0vy3rdna.soa.crud.models.Worker;

import javax.inject.Singleton;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class FilterParser {
    public List<Predicate> parse(CriteriaBuilder cb, Root<Worker> workerRoot, List<String> input) throws Exception {
        List<Predicate> predicates = new ArrayList<>();
        for (String item : input) {
            String[] s = item.split(" ");
            if (s.length != 3)
                throw new Exception("cannot parse filter");
            Predicate predicate;
            switch (s[1]) {
                case "eq":
                    Path<Object> path = workerRoot.get(s[0]);
                    Object instance;
                    instance = path.getJavaType().cast(
                            path.getJavaType().
                                    getDeclaredMethod("valueOf", String.class).
                                    invoke(null, s[2])
                    );
                    predicate = cb.equal(path, instance);
                    break;
                case "gt":
                    predicate = cb.gt(workerRoot.get(s[0]), Double.parseDouble(s[2]));
                    break;
                case "ge":
                    predicate = cb.ge(workerRoot.get(s[0]), Double.parseDouble(s[2]));
                    break;
                case "lt":
                    predicate = cb.lt(workerRoot.get(s[0]), Double.parseDouble(s[2]));
                    break;
                case "le":
                    predicate = cb.le(workerRoot.get(s[0]), Double.parseDouble(s[2]));
                    break;
                default:
                    continue;
            }
            predicates.add(predicate);
        }
        return predicates;
    }
}
