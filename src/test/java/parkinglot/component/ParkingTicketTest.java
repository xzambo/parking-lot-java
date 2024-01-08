package parkinglot.component;

import org.junit.jupiter.api.Test;
import parkinglot.util.LocalDateTimeUtil;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParkingTicketTest {
    @Test
    void shouldGenerateParkingTicket() {
        LocalDateTimeUtil localDateTimeUtil = mock(LocalDateTimeUtil.class);
        ParkingTicket parkingTicket = new ParkingTicket(localDateTimeUtil, mock(AtomicInteger.class));
        when(localDateTimeUtil.getLocalDateTime()).thenReturn("06-Feb-2023 10:00:00");

        ParkingTicket actualParkingTicket = parkingTicket.generateEntryTicket(1);

        assertEquals(1, actualParkingTicket.getSpotId());
        assertEquals("06-Feb-2023 10:00:00", actualParkingTicket.getEntryDateTime());
    }
}