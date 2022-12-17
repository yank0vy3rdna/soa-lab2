//package ru.yank0vy3rdna.soa.lab3.crud_logic.dao.utils.dbconverters;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.time.ZonedDateTime;
//
//@Converter(autoApply = true)
//public class ZonedDateTimePersistenceConverter implements AttributeConverter<ZonedDateTime, Timestamp> {
//
//    @Override
//    public java.sql.Timestamp convertToDatabaseColumn(ZonedDateTime entityValue) {
//        if (entityValue != null) {
//            return java.sql.Timestamp.from(entityValue.toInstant());
//        }
//        return null;
//    }
//
//    @Override
//    public ZonedDateTime convertToEntityAttribute(java.sql.Timestamp databaseValue) {
//        if (databaseValue != null) {
//            return ZonedDateTime.from(databaseValue.toLocalDateTime());
//        }
//        return null;
//    }
//}