package parkinglot.manager;

import org.junit.jupiter.api.Test;
import parkinglot.component.ExitTicket;
import parkinglot.component.ParkingTicket;
import parkinglot.factory.FeeModelFactory;
import parkinglot.util.LocalDateTimeUtil;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static parkinglot.model.FeeModel.MALL;

class TicketManagerTest {
    private final LocalDateTimeUtil localDateTimeUtil = mock(LocalDateTimeUtil.class);
    private final AtomicInteger atomicInteger = mock(AtomicInteger.class);
    private final FeeModelFactory feeModelFactory = mock(FeeModelFactory.class);
    private final TicketManager ticketManager
            = new TicketManager(localDateTimeUtil, atomicInteger, MALL, feeModelFactory);

    @Test
    void shouldReturnParkingTicket() {
        ParkingTicket parkingTicket = ticketManager.getParkingTicket();

        assertEquals(ParkingTicket.class, parkingTicket.getClass());
    }

    @Test
    void shouldReturnExitTicket() {
        ExitTicket exitTicket = ticketManager.getExitTicket();

        assertEquals(ExitTicket.class, exitTicket.getClass());
    }

    @Test
    void shouldReturnParkingTicketForGivenTicketNumber() {
        ParkingTicket parkingTicket = ticketManager.getParkingTicket();

        Optional<ParkingTicket> actualParkingTicket = ticketManager
                .findParkingTicketBy(parkingTicket.getTicketNumber());

        assertTrue(actualParkingTicket.isPresent());
    }

    @Test
    void shouldReturnNotReturnParkingTicketForInvalidTicketNumber() {
        Optional<ParkingTicket> parkingTicketBy = ticketManager.findParkingTicketBy(1);

        assertFalse(parkingTicketBy.isPresent());
    }
}