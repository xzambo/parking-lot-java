package parkinglot.strategy;

import parkinglot.component.ParkingTicket;

public class TwoWheelerStadiumFeeStrategy implements ParkingFeeStrategy {
    private static final int FLAT_RATE_BETWEEN_0_TO_4_HOURS = 30;
    private static final int FLAT_RATE_BETWEEN_4_TO_12_HOURS = 30 + FLAT_RATE_BETWEEN_0_TO_4_HOURS;
    private static final int FLAT_RATE_PER_HOUR = 100;
    private static final double TWELVE_HOURS = 12;

    @Override
    public double getFee(ParkingTicket parkingTicket, String exitDateTime) {
        double initialFee = 0.0;
        long hoursParked = calculateHours(parkingTicket, exitDateTime);

        return calculateFee(initialFee, hoursParked);
    }

    private static double calculateFee(double totalFee, long hoursParked) {
        if (hoursParked >= 0 && hoursParked <= 4) {
            totalFee = FLAT_RATE_BETWEEN_0_TO_4_HOURS;
        } else if (hoursParked > 4 && hoursParked <= 12) {
            totalFee = FLAT_RATE_BETWEEN_4_TO_12_HOURS;
        } else if (hoursParked > 12) {
            totalFee = FLAT_RATE_PER_HOUR * (hoursParked - TWELVE_HOURS)
                    + FLAT_RATE_BETWEEN_0_TO_4_HOURS
                    + FLAT_RATE_BETWEEN_4_TO_12_HOURS;
        }
        return totalFee;
    }
}
