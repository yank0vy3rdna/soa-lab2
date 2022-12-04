package ru.yank0vy3rdna.hr.utils.xmladapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ZonedDateTimeXmlAdapter extends XmlAdapter<String, ZonedDateTime> {
    @Override
    public String marshal(ZonedDateTime v) {
        if (v == null) {
            return "";
        }
        return v.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    @Override
    public ZonedDateTime unmarshal(String v) {
        if (Objects.equals(v, "")) {
            return null;
        }
        return ZonedDateTime.parse(v,  DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
}