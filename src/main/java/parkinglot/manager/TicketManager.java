package parkinglot.manager;

import parkinglot.component.ExitTicket;
import parkinglot.component.ParkingTicket;
import parkinglot.factory.FeeModelFactory;
import parkinglot.model.FeeModel;
import parkinglot.util.LocalDateTimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketManager {
    private final List<ParkingTicket> parkingTickets = new ArrayList<>();
    private final LocalDateTimeUtil localDateTimeUtil;
    private final AtomicInteger atomicInteger;
    private final FeeModel feeModel;
    private final FeeModelFactory feeModelFactory;

    public TicketManager(LocalDateTimeUtil localDateTimeUtil,
                         AtomicInteger atomicInteger,
                         FeeModel feeModel,
                         FeeModelFactory feeModelFactory) {
        this.localDateTimeUtil = localDateTimeUtil;
        this.atomicInteger = atomicInteger;
        this.feeModel = feeModel;
        this.feeModelFactory = feeModelFactory;
    }

    public ParkingTicket getParkingTicket() {
        ParkingTicket parkingTicket = new ParkingTicket(localDateTimeUtil, atomicInteger);
        parkingTickets.add(parkingTicket);
        return parkingTicket;
    }

    public ExitTicket getExitTicket() {
        return new ExitTicket(localDateTimeUtil, feeModelFactory, atomicInteger, feeModel);
    }

    public Optional<ParkingTicket> findParkingTicketBy(int ticketNumber) {
        return parkingTickets
                .stream()
                .filter(parkingTicket -> parkingTicket.getTicketNumber() == ticketNumber)
                .findFirst();
    }
}
