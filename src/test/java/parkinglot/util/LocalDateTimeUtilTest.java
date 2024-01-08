package parkinglot.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalDateTimeUtilTest {
    @Test
    void shouldReturnFormattedLocalDateTime() {
        LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
        String actualPattern = "^(0[1-9]|[12][0-9]|3[01])-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-\\d{4} (0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";

        String localDateTime = localDateTimeUtil.getLocalDateTime();
        
        assertTrue(localDateTime.matches(actualPattern));
    }

    @Test
    void shouldReturnLocalDateTimeFromFormattedDateTime() {
        LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();

        LocalDateTime parsedDateTime = localDateTimeUtil.parseToLocalDateTIme("06-Feb-2023 10:00:00");

        assertEquals(LocalDateTime.class, parsedDateTime.getClass());
    }
}