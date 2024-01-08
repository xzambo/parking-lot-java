package parkinglot.strategy;

import parkinglot.component.ParkingTicket;
import parkinglot.util.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public interface ParkingFeeStrategy {
    default long calculateHours(ParkingTicket parkingTicket, String exitDateTime) {
        LocalDateTimeUtil localDateTimeUtil = parkingTicket.getLocalDateTimeUtil();
        LocalDateTime entryTime = localDateTimeUtil.parseToLocalDateTIme(parkingTicket.getEntryDateTime());
        LocalDateTime exitTime = localDateTimeUtil.parseToLocalDateTIme(exitDateTime);

        long parkedDurationInSeconds = ChronoUnit.SECONDS.between(entryTime, exitTime);
        double totalSecondPerHour = 60.0 * 60.0;

        return (long) Math.ceil(parkedDurationInSeconds / totalSecondPerHour);
    }
    double getFee(ParkingTicket parkingTicket, String exitDateTime);
}
