package parkinglot.component;

import parkinglot.manager.ParkingSpotManager;
import parkinglot.manager.TicketManager;
import parkinglot.model.ParkingSpot;
import parkinglot.model.Vehicle;

import java.util.Optional;

public class EntranceGate extends Gate {
    private static final String NO_SPACE_AVAILABLE = "No space available";

    public EntranceGate(ParkingSpotManager parkingSpotManager, TicketManager ticketManager) {
        super(parkingSpotManager, ticketManager);
    }

    public ParkingTicket park(Vehicle vehicle) {
        Optional<ParkingSpot> parkingSpot = parkingSpotManager.findAvailableParkingSpot(vehicle);
        if (parkingSpot.isPresent())
            return bookSpot(parkingSpot.get());
        else {
            throw new IllegalArgumentException(NO_SPACE_AVAILABLE);
        }
    }

    private ParkingTicket bookSpot(ParkingSpot parkingSpot) {
        parkingSpot.park();
        return ticketManager
                .getParkingTicket()
                .generateEntryTicket(parkingSpot.getId());
    }
}
