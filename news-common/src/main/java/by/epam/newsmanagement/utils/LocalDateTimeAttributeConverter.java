package by.epam.newsmanagement.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDate, Timestamp> {

  @Override
    public Timestamp convertToDatabaseColumn(LocalDate localDate) {
    Date date = null;  
    if (localDate != null){
      date = Date.valueOf(localDate);
      }
      return (localDate == null ? null : new Timestamp(date.getTime()));
    }

  @Override
  public LocalDate convertToEntityAttribute(Timestamp timestamp) {
    
    return (timestamp == null ? null : timestamp.toLocalDateTime().toLocalDate());
  }

}
