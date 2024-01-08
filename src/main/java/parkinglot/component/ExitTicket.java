package parkinglot.component;

import parkinglot.factory.FeeModelFactory;
import parkinglot.strategy.ParkingFeeStrategy;
import parkinglot.model.FeeModel;
import parkinglot.factory.FeeModelStrategyFactory;
import parkinglot.model.Vehicle;
import parkinglot.util.LocalDateTimeUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class ExitTicket extends Ticket {
    private String exitDateTime;
    private final FeeModelFactory feeModelFactory;
    private final FeeModel feeModel;

    public ExitTicket(LocalDateTimeUtil localDateTimeUtil,
                      FeeModelFactory feeModelFactory,
                      AtomicInteger atomicInteger,
                      FeeModel feeModel) {
        super(localDateTimeUtil, atomicInteger);
        this.feeModelFactory = feeModelFactory;
        this.feeModel = feeModel;
    }

    public String getExitDateTime() {
        return exitDateTime;
    }

    public ExitTicket generateExitTicket(Vehicle vehicle, ParkingTicket parkingTicket) {
        exitDateTime = localDateTimeUtil.getLocalDateTime();

        System.out.println("Parking Receipt:\n" +
                "Receipt Number: R-" + ticketNumber + "\n" +
                "Entry Date-time: " + parkingTicket.getEntryDateTime() + "\n" +
                "Exit Date-time: " + exitDateTime + "\n" +
                "Fees: " + calculateFee(vehicle, parkingTicket, exitDateTime));

        return this;
    }

    private double calculateFee(Vehicle vehicle, ParkingTicket parkingTicket, String exitDateTime) {
        FeeModelStrategyFactory feeModelStrategyFactory = feeModelFactory.getFeeModelStrategyFactory(feeModel);
        ParkingFeeStrategy parkingFeeStrategy = feeModelStrategyFactory.getParkingFeeStrategy(vehicle);

        return parkingFeeStrategy.getFee(parkingTicket, exitDateTime);
    }
}
