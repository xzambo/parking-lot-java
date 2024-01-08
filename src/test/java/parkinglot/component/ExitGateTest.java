package parkinglot.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parkinglot.manager.ParkingSpotManager;
import parkinglot.manager.TicketManager;
import parkinglot.model.ParkingSpot;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static parkinglot.model.Vehicle.MOTORCYCLE;

class ExitGateTest {
    private final ParkingSpotManager parkingSpotManager = mock(ParkingSpotManager.class);
    private final TicketManager ticketManager = mock(TicketManager.class);
    private final ExitGate exitGate = new ExitGate(parkingSpotManager, ticketManager);
    private final ParkingTicket parkingTicket = mock(ParkingTicket.class);
    private final ExitTicket exitTicket = mock(ExitTicket.class);

    @BeforeEach
    void setUpMock() {
        when(ticketManager.findParkingTicketBy(1)).thenReturn(Optional.of(parkingTicket));
        when(ticketManager.getExitTicket()).thenReturn(exitTicket);
        when(parkingSpotManager.findParkingSpot(MOTORCYCLE, 1))
                .thenReturn(Optional.of(new ParkingSpot(1, MOTORCYCLE)));
        when(parkingTicket.getSpotId()).thenReturn(1);
        when(exitTicket.generateExitTicket(MOTORCYCLE, parkingTicket)).thenReturn(mock(ExitTicket.class));
    }

    @Test
    void shouldCallTicketManagerToFindParkingTicket() {
        exitGate.exit(MOTORCYCLE, 1);

        verify(ticketManager).findParkingTicketBy(1);
        verify(ticketManager).getExitTicket();
    }

    @Test
    void shouldCallParkingSpotManagerToFindParkingSpot() {
        exitGate.exit(MOTORCYCLE, 1);

        verify(parkingSpotManager).findParkingSpot(MOTORCYCLE, 1);
    }

    @Test
    void shouldCallExitTicketToOnSuccessfulVacate() {
        exitGate.exit(MOTORCYCLE, 1);

        verify(exitTicket).generateExitTicket(MOTORCYCLE, parkingTicket);
    }

    @Test
    void shouldThrowExceptionWhenParkingSpotIsNotFound() {
        when(parkingSpotManager.findParkingSpot(MOTORCYCLE, 1)).thenReturn(Optional.empty());
        when(ticketManager.findParkingTicketBy(1)).thenReturn(Optional.of(parkingTicket));
        when(parkingTicket.getSpotId()).thenReturn(1);

        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class, () -> exitGate.exit(MOTORCYCLE, 1));

        assertEquals("Parking spot not found for the given ticket number", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidTicketNumber() {
        when(ticketManager.findParkingTicketBy(1)).thenReturn(Optional.empty());
        when(parkingTicket.getSpotId()).thenReturn(1);

        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class, () -> exitGate.exit(MOTORCYCLE, 1));

        assertEquals("Invalid parking ticket number", exception.getMessage());
    }
}