package parkinglot.manager;

import parkinglot.model.Vehicle;
import org.junit.jupiter.api.Test;
import parkinglot.model.ParkingSpot;

import java.util.List;
import java.util.Optional;

import static parkinglot.model.Vehicle.*;
import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotManagerTest {
    @Test
    void shouldReturnTrueForAvailableParkingSpotForGivenVehicle() {
        Vehicle motorcycle = MOTORCYCLE;
        ParkingSpot parkingSpot = new ParkingSpot(1, motorcycle);
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager(List.of(parkingSpot));

        Optional<ParkingSpot> availableParkingSpot = parkingSpotManager.findAvailableParkingSpot(motorcycle);

        assertTrue(availableParkingSpot.isPresent());
    }

    @Test
    void shouldReturnTrueForSimilarVehicleType() {
        ParkingSpot parkingSpot = new ParkingSpot(1, MOTORCYCLE);
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager(List.of(parkingSpot));

        Optional<ParkingSpot> availableParkingSpot = parkingSpotManager.findAvailableParkingSpot(SCOOTER);

        assertTrue(availableParkingSpot.isPresent());
    }

    @Test
    void shouldReturnFalseForUnavailableParkingSpotForGivenVehicle() {
        ParkingSpot parkingSpot = new ParkingSpot(1, MOTORCYCLE);
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager(List.of(parkingSpot));

        Optional<ParkingSpot> availableParkingSpot = parkingSpotManager.findAvailableParkingSpot(CAR);

        assertFalse(availableParkingSpot.isPresent());
    }

    @Test
    void shouldReturnCorrectParkingSpotForGivenVehicle() {
        ParkingSpot parkingSpot1 = new ParkingSpot(1, MOTORCYCLE);
        ParkingSpot parkingSpot2 = new ParkingSpot(1, CAR);
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager(List.of(parkingSpot1, parkingSpot2));

        Optional<ParkingSpot> availableParkingSpot = parkingSpotManager.findAvailableParkingSpot(CAR);

        assertTrue(availableParkingSpot.isPresent());
        assertEquals(CAR, availableParkingSpot.get().getVehicleType());
    }

    @Test
    void shouldReturnCorrectParkingSpotForGivenVehicleAndSpotId() {
        ParkingSpot parkingSpot1 = new ParkingSpot(101, MOTORCYCLE);
        ParkingSpot parkingSpot2 = new ParkingSpot(202, CAR);
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager(List.of(parkingSpot1, parkingSpot2));

        Optional<ParkingSpot> availableParkingSpot = parkingSpotManager.findParkingSpot(CAR, 202);

        assertTrue(availableParkingSpot.isPresent());
        assertEquals(CAR, availableParkingSpot.get().getVehicleType());
    }
}