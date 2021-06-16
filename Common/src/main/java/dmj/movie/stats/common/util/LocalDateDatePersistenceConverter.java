package dmj.movie.stats.common.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter(
        autoApply = true
)
public class LocalDateDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {
    public LocalDateDatePersistenceConverter() {
    }

    public Date convertToDatabaseColumn(LocalDate entityValue) {
        return entityValue != null ? Date.valueOf(entityValue) : null;
    }

    public LocalDate convertToEntityAttribute(Date databaseValue) {
        return databaseValue != null ? databaseValue.toLocalDate() : null;
    }
}
