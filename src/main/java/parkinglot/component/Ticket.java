package parkinglot.component;

import parkinglot.util.LocalDateTimeUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
    protected int ticketNumber;
    protected LocalDateTimeUtil localDateTimeUtil;

    public Ticket(LocalDateTimeUtil localDateTimeUtil, AtomicInteger atomicInteger) {
        this.localDateTimeUtil = localDateTimeUtil;
        this.ticketNumber = atomicInteger.incrementAndGet();
    }

    public LocalDateTimeUtil getLocalDateTimeUtil() {
        return localDateTimeUtil;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }
}
