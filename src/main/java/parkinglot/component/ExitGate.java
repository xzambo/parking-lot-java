package parkinglot.component;

import parkinglot.manager.ParkingSpotManager;
import parkinglot.manager.TicketManager;
import parkinglot.model.ParkingSpot;
import parkinglot.model.Vehicle;

import java.util.Optional;

public class ExitGate extends Gate{

    public ExitGate(ParkingSpotManager parkingSpotManager, TicketManager ticketManager) {
        super(parkingSpotManager, ticketManager);
    }

    public ExitTicket exit(Vehicle vehicle, int ticketNumber) {
        Optional<ParkingTicket> parkingTicket = ticketManager.findParkingTicketBy(ticketNumber);
        if (parkingTicket.isPresent()) {
            ParkingTicket parkingTicketValue = parkingTicket.get();
            Optional<ParkingSpot> parkingSpot = parkingSpotManager.findParkingSpot(vehicle, parkingTicketValue.getSpotId());
            if (parkingSpot.isPresent()) {
                return vacateSpot(vehicle, parkingSpot.get(), parkingTicketValue);
            } else {
                throw new IllegalArgumentException("Parking spot not found for the given ticket number");
            }
        } else {
            throw new IllegalArgumentException("Invalid parking ticket number");
        }
    }

    private ExitTicket vacateSpot(Vehicle vehicle, ParkingSpot parkingSpot, ParkingTicket parkingTicket) {
        parkingSpot.vacate();
        return ticketManager.getExitTicket().generateExitTicket(vehicle, parkingTicket);
    }
}

