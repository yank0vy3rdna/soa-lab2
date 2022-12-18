package ru.yank0vy3rdna.soa.lab3.common.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "workers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Workers implements Serializable {
    private static final long serialVersionUID = -558553967080513790L;

    @XmlElement(name = "worker")
    private List<Worker> workers;

    public Workers(List<Worker> workersList) {
        this.workers = workersList;
    }

    public Workers() {
        this.workers = new ArrayList<>();
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
