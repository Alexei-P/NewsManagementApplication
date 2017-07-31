package by.epam.newsmanagement.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter
    implements AttributeConverter<LocalDate, java.sql.Date> {

  @Override
  public java.sql.Date convertToDatabaseColumn(LocalDate localDate) {
    return (localDate == null ? null : Date.valueOf(localDate));
  }

  @Override
  public LocalDate convertToEntityAttribute(java.sql.Date date) {
    return (date == null ? null : date.toLocalDate());
  }

}
