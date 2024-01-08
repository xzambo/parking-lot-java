package parkinglot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {
    private static final String DATE_PATTERN = "dd-MMM-yyyy HH:mm:ss";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getLocalDateTime() {
        return LocalDateTime.now().format(formatter);
    }

    public LocalDateTime parseToLocalDateTIme(String formattedDateTime) {
        return LocalDateTime.parse(formattedDateTime, formatter);
    }
}
