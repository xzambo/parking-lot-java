package parkinglot.strategy;

import parkinglot.component.ParkingTicket;

public class FourWheelerAirportFeeStrategy implements ParkingFeeStrategy {
    private static final int FLAT_RATE_BETWEEN_0_TO_12_HOURS = 60;
    private static final int FLAT_RATE_BETWEEN_12_TO_24_HOURS = 80;
    private static final int FLAT_RATE_FOR_EACH_DAY = 100;
    private static final double NUMBER_OF_HOURS_PER_DAY = 24.0;

    @Override
    public double getFee(ParkingTicket parkingTicket, String exitDateTime) {
        double initialFee = 0.0;
        long hoursParked = calculateHours(parkingTicket, exitDateTime);

        return calculateFee(initialFee, hoursParked);
    }

    private static double calculateFee(double totalFee, long hoursParked) {
        if (hoursParked >= 0 && hoursParked <= 12) {
            totalFee = FLAT_RATE_BETWEEN_0_TO_12_HOURS;
        } else if (hoursParked > 12 && hoursParked <= 24) {
            totalFee = FLAT_RATE_BETWEEN_12_TO_24_HOURS;
        } else if (hoursParked > 24) {
            totalFee = FLAT_RATE_FOR_EACH_DAY * Math.ceil(hoursParked / NUMBER_OF_HOURS_PER_DAY);
        }
        return totalFee;
    }
}
