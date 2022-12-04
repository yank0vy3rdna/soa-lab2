package ru.yank0vy3rdna.soa.crud.utils.xmladapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public String marshal(LocalDate v) {
        if (v == null) {
            return "";
        }
        return v.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public LocalDate unmarshal(String v) throws ParseException {
        if (v.equals("")){
            return null;
        }
        return LocalDate.parse(v, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}