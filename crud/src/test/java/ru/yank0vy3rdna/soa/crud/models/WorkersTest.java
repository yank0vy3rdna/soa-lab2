package ru.yank0vy3rdna.soa.crud.models;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;


class WorkersTest {
    @SneakyThrows
    @Test
    void serialize() {
        JAXBContext jaxbContext = JAXBContext.newInstance(Workers.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Workers workers = new Workers(new ArrayList<>());
        workers.getWorkers().add(new Worker());
        jaxbMarshaller.marshal(workers, System.out);
    }
}