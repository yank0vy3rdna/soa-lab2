//package ru.yank0vy3rdna.soa.lab3.crud_logic.dao.utils.dbconverters;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.time.LocalDateTime;
//
//@Converter(autoApply = true)
//public class LocalDateTimePersistenceConverter implements AttributeConverter<LocalDateTime, java.sql.Timestamp> {
//
//    @Override
//    public java.sql.Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
//        if (entityValue != null) {
//            return java.sql.Timestamp.valueOf(entityValue);
//        }
//        return null;
//    }
//
//    @Override
//    public LocalDateTime convertToEntityAttribute(java.sql.Timestamp databaseValue) {
//        if (databaseValue != null) {
//            return databaseValue.toLocalDateTime();
//        }
//        return null;
//    }
//}