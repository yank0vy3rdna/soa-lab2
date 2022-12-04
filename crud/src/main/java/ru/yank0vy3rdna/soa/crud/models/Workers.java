package ru.yank0vy3rdna.soa.crud.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "workers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Workers {
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
