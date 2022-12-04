package ru.yank0vy3rdna.soa.crud.utils.xmladapters;

import org.junit.jupiter.api.Test;
import ru.yank0vy3rdna.soa.crud.models.Worker;
import ru.yank0vy3rdna.soa.crud.utils.dbconverters.ZonedDateTimePersistenceConverter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.time.ZonedDateTime;

class ZonedDateTimeXmlAdapterTest {

    @Test
    void unmarshal() {
        ZonedDateTimeXmlAdapter zonedDateTimeXmlAdapter = new ZonedDateTimeXmlAdapter();
        java.sql.Timestamp r = (new ZonedDateTimePersistenceConverter()).convertToDatabaseColumn(zonedDateTimeXmlAdapter.unmarshal("2021-12-03T10:15:30+01:00"));
        System.out.println(r);
    }

    @Test
    void marshal() {
        ZonedDateTimeXmlAdapter zonedDateTimeXmlAdapter = new ZonedDateTimeXmlAdapter();
        String r = zonedDateTimeXmlAdapter.marshal(ZonedDateTime.now());
//        java.sql.Timestamp r = (new ZonedDateTimePersistenceConverter()).convertToDatabaseColumn(zonedDateTimeXmlAdapter.unmarshal("2021-12-03T10:15:30+01:00"));
        System.out.println(r);
    }

    @Test
    void m() throws JAXBException {
        Worker workerInput = new Worker();
        workerInput.setName("Lol");
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Worker.class);
        Marshaller m = context.createMarshaller();
        m.marshal(workerInput, writer);
//        Response.ok().entity(workerInput).build();
    }
}