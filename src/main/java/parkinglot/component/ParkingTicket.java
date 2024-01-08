package parkinglot.component;

import parkinglot.util.LocalDateTimeUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class ParkingTicket extends Ticket {
    private String entryDateTime;
    private int spotId;

    public ParkingTicket(LocalDateTimeUtil localDateTimeUtil, AtomicInteger atomicInteger) {
        super(localDateTimeUtil, atomicInteger);
    }

    public ParkingTicket generateEntryTicket(int spotId) {
        this.entryDateTime = localDateTimeUtil.getLocalDateTime();
        this.spotId = spotId;

        System.out.println("Parking Ticket:\n" +
                "Ticket Number: " + ticketNumber + "\n" +
                "Spot Number: " + spotId + "\n" +
                "Entry Date-time: " + entryDateTime);

        return this;
    }

    public String getEntryDateTime() {
        return entryDateTime;
    }

    public int getSpotId() {
        return spotId;
    }
}
