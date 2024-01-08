package parkinglot.strategy;

import parkinglot.component.ParkingTicket;

public class TwoWheelerAirportFeeStrategy implements ParkingFeeStrategy {
    private static final int FLAT_RATE_BETWEEN_0_TO_1_HOURS = 0;
    private static final int FLAT_RATE_BETWEEN_1_TO_8_HOURS = 40;
    private static final int FLAT_RATE_BETWEEN_8_TO_24_HOURS = 60;
    private static final int FLAT_RATE_EACH_DAY = 80;
    private static final double TWENTY_FOUR_HOURS = 24.0;

    @Override
    public double getFee(ParkingTicket parkingTicket, String exitDateTime) {
        double initialFee = 0.0;
        long hoursParked = calculateHours(parkingTicket, exitDateTime);

        return calculateFee(initialFee, hoursParked);
    }

    private static double calculateFee(double totalFee, long hoursParked) {
        if (hoursParked >= 0 && hoursParked <= 1) {
            totalFee = FLAT_RATE_BETWEEN_0_TO_1_HOURS;
        } else if (hoursParked >= 1 && hoursParked <= 8) {
            totalFee = FLAT_RATE_BETWEEN_1_TO_8_HOURS;
        } else if (hoursParked > 8 && hoursParked <= 24) {
            totalFee = FLAT_RATE_BETWEEN_8_TO_24_HOURS;
        } else if (hoursParked > 24) {
            totalFee = FLAT_RATE_EACH_DAY * Math.ceil(hoursParked / TWENTY_FOUR_HOURS);
        }
        return totalFee;
    }
}
