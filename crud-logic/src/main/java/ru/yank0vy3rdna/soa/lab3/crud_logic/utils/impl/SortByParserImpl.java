package ru.yank0vy3rdna.soa.lab3.crud_logic.utils.impl;

import jakarta.enterprise.context.Dependent;
import ru.yank0vy3rdna.soa.lab3.common.models.Worker;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import ru.yank0vy3rdna.soa.lab3.crud_logic.utils.SortByParser;

import java.util.ArrayList;
import java.util.List;

@Dependent
public class SortByParserImpl implements SortByParser {
    public SortByParserImpl() {
    }

    public List<Order> parse(CriteriaBuilder cb, Root<Worker> workerRoot, List<String> input) {
        List<Order> orders = new ArrayList<>();
        for (String item : input) {
            String[] s = item.split(" ");
            if (s.length == 0)
                continue;
            if (s.length == 1)
                orders.add(cb.asc(workerRoot.get(s[0])));
            if (s.length > 1) {
                if ("desc".equals(s[1])) {
                    orders.add(cb.desc(workerRoot.get(s[0])));
                } else {
                    orders.add(cb.asc(workerRoot.get(s[0])));
                }
            }
        }
        return orders;
    }
}
