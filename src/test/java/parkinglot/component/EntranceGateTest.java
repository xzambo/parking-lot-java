package parkinglot.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parkinglot.manager.ParkingSpotManager;
import parkinglot.manager.TicketManager;
import parkinglot.model.ParkingSpot;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static parkinglot.model.Vehicle.MOTORCYCLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EntranceGateTest {
    private final ParkingSpotManager parkingSpotManager = mock(ParkingSpotManager.class);
    private final TicketManager ticketManager = mock(TicketManager.class);
    private final EntranceGate entranceGate = new EntranceGate(parkingSpotManager, ticketManager);
    private final ParkingTicket parkingTicket = mock(ParkingTicket.class);

    @BeforeEach
    void setUpMock() {
        when(parkingSpotManager.findAvailableParkingSpot(MOTORCYCLE))
                .thenReturn(Optional.of(new ParkingSpot(1, MOTORCYCLE)));
        when(ticketManager.getParkingTicket()).thenReturn(parkingTicket);
    }

    @Test
    void shouldCallParkingSpotManagerToFindAvailableParkingSpot() {
        entranceGate.park(MOTORCYCLE);

        verify(parkingSpotManager).findAvailableParkingSpot(MOTORCYCLE);
    }

    @Test
    void shouldCallTicketManagerToGenerateParkingTicket() {
        entranceGate.park(MOTORCYCLE);

        verify(ticketManager).getParkingTicket();
    }

    @Test
    void shouldReturnWarningMessageForUnsuccessfulParking() {
        when(parkingSpotManager.findAvailableParkingSpot(MOTORCYCLE)).thenReturn(Optional.empty());

        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class, () -> entranceGate.park(MOTORCYCLE));

        assertEquals("No space available", exception.getMessage());
    }
}