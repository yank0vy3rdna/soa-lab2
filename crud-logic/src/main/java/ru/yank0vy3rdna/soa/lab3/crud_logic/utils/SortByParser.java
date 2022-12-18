package ru.yank0vy3rdna.soa.lab3.crud_logic.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import ru.yank0vy3rdna.soa.lab3.common.models.Worker;

import java.util.List;

public interface SortByParser {
    List<Order> parse(CriteriaBuilder cb, Root<Worker> workerRoot, List<String> input);
}
