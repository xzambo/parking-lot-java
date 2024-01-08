package parkinglot.manager;

import parkinglot.model.Vehicle;
import parkinglot.model.ParkingSpot;

import java.util.List;
import java.util.Optional;

import static parkinglot.model.Vehicle.*;

public class ParkingSpotManager {
    private final List<ParkingSpot> parkingSpots;

    public ParkingSpotManager(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public Optional<ParkingSpot> findAvailableParkingSpot(Vehicle vehicle) {
        return parkingSpots
                .stream()
                .filter(parkingSpot -> areVehicleTypeSame(parkingSpot, vehicle) && !parkingSpot.isOccupied())
                .findFirst();
    }

    public Optional<ParkingSpot> findParkingSpot(Vehicle vehicle, int spotId) {
        return parkingSpots
                .stream()
                .filter(parkingSpot -> areVehicleTypeSame(parkingSpot, vehicle) && parkingSpot.getId() == spotId)
                .findFirst();
    }

    private boolean areVehicleTypeSame(ParkingSpot parkingSpot, Vehicle vehicle) {
        Vehicle parkingSpotVehicleType = parkingSpot.getVehicleType();
        if ((vehicle == MOTORCYCLE || vehicle == SCOOTER)
                && (parkingSpotVehicleType == MOTORCYCLE || parkingSpotVehicleType == SCOOTER)) {
            return true;
        } else if ((vehicle == CAR || vehicle == SUV)
                && (parkingSpotVehicleType == CAR || parkingSpotVehicleType == SUV)) {
            return true;
        } else return (vehicle == BUS || vehicle == TRUCK)
                && (parkingSpotVehicleType == BUS || parkingSpotVehicleType == TRUCK);
    }
}
