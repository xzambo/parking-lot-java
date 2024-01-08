package parkinglot.strategy;

import parkinglot.component.ParkingTicket;

public class FourWheelerMallFeeStrategy implements ParkingFeeStrategy {
    private static final int PER_HOUR_FLAT_FEES = 20;

    @Override
    public double getFee(ParkingTicket parkingTicket, String exitDateTime) {
        return PER_HOUR_FLAT_FEES * calculateHours(parkingTicket, exitDateTime);
    }
}
