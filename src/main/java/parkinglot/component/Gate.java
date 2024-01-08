package parkinglot.component;

import parkinglot.manager.ParkingSpotManager;
import parkinglot.manager.TicketManager;

public class Gate {
    protected final ParkingSpotManager parkingSpotManager;
    protected final TicketManager ticketManager;

    public Gate(ParkingSpotManager parkingSpotManager, TicketManager ticketManager) {
        this.parkingSpotManager = parkingSpotManager;
        this.ticketManager = ticketManager;
    }
}
