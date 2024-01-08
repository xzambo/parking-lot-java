package parkinglot;

import parkinglot.component.EntranceGate;
import parkinglot.component.ExitGate;
import parkinglot.factory.FeeModelFactory;
import parkinglot.manager.ParkingSpotManager;
import parkinglot.manager.TicketManager;
import parkinglot.model.FeeModel;
import parkinglot.model.ParkingSpot;
import parkinglot.model.Vehicle;
import parkinglot.util.LocalDateTimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static parkinglot.model.FeeModel.*;
import static parkinglot.model.Vehicle.*;

public class ParkingLotApplication {
    private final int twoWheelerParkingSpotCount;
    private final int fourWheelerParkingSpotCount;
    private final int sixWheelerParkingSpotCount;

    private final EntranceGate entranceGate;
    private final ExitGate exitGate;

    public ParkingLotApplication(FeeModel feeModel,
                                 int twoWheelerParkingSpotCount,
                                 int fourWheelerParkingSpotCount,
                                 int sixWheelerParkingSpotCount) {
        this.twoWheelerParkingSpotCount = twoWheelerParkingSpotCount;
        this.fourWheelerParkingSpotCount = fourWheelerParkingSpotCount;
        this.sixWheelerParkingSpotCount = sixWheelerParkingSpotCount;

        LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
        TicketManager ticketManager
                = new TicketManager(localDateTimeUtil, new AtomicInteger(), feeModel, new FeeModelFactory());
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager(getParkingSpots());
        entranceGate = new EntranceGate(parkingSpotManager, ticketManager);
        exitGate = new ExitGate(parkingSpotManager, ticketManager);
    }

    public static void main(String[] args) {
        run1();
        run2();
        run3();
    }

    private static void run1() {
        ParkingLotApplication parkingLotApplication
                = new ParkingLotApplication(MALL, 2, 0, 0);

        parkingLotApplication.park(MOTORCYCLE);
        parkingLotApplication.park(SCOOTER);
        parkingLotApplication.park(SCOOTER);

        parkingLotApplication.unpark(SCOOTER, 2);

        parkingLotApplication.park(MOTORCYCLE);
        parkingLotApplication.unpark(MOTORCYCLE, 1);
    }

    private static void run2() {
        ParkingLotApplication parkingLotApplication
                = new ParkingLotApplication(STADIUM, 1000, 1500, 0);

        parkingLotApplication.park(MOTORCYCLE);
        parkingLotApplication.park(SCOOTER);
        parkingLotApplication.park(SUV);
        parkingLotApplication.park(CAR);

        parkingLotApplication.unpark(CAR, 4);
        parkingLotApplication.unpark(SUV, 3);
        parkingLotApplication.unpark(SCOOTER, 2);
        parkingLotApplication.unpark(MOTORCYCLE, 1);
    }

    private static void run3() {
        ParkingLotApplication parkingLotApplication
                = new ParkingLotApplication(AIRPORT, 1000, 1500, 0);

        parkingLotApplication.park(MOTORCYCLE);
        parkingLotApplication.park(SCOOTER);
        parkingLotApplication.park(SUV);
        parkingLotApplication.park(CAR);
        parkingLotApplication.park(BUS);
        parkingLotApplication.park(TRUCK);

        parkingLotApplication.unpark(CAR, 4);
        parkingLotApplication.unpark(SUV, 3);
        parkingLotApplication.unpark(SCOOTER, 2);
        parkingLotApplication.unpark(MOTORCYCLE, 1);
    }

    public void park(Vehicle vehicle) {
        try {
            entranceGate.park(vehicle);
            newLine();
            Thread.sleep(1000);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            newLine();
        }
    }

    public void unpark(Vehicle vehicle, int ticketNumber) {
        try {
            exitGate.exit(vehicle, ticketNumber);
            newLine();
            Thread.sleep(1000);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            newLine();
        }
    }

    private List<ParkingSpot> getParkingSpots() {
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        for (int spotId = 1; spotId <= twoWheelerParkingSpotCount; spotId++) {
            parkingSpots.add(new ParkingSpot(spotId, MOTORCYCLE));
        }
        for (int spotId = 1; spotId <= fourWheelerParkingSpotCount; spotId++) {
            parkingSpots.add(new ParkingSpot(spotId, CAR));
        }
        for (int spotId = 1; spotId <= sixWheelerParkingSpotCount; spotId++) {
            parkingSpots.add(new ParkingSpot(spotId, BUS));
        }
        return parkingSpots;
    }

    private void newLine() {
        System.out.println(System.lineSeparator());
    }
}
