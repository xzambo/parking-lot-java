package parkinglot.factory;

import org.junit.jupiter.api.Test;
import parkinglot.strategy.FourWheelerAirportFeeStrategy;
import parkinglot.strategy.ParkingFeeStrategy;
import parkinglot.strategy.TwoWheelerAirportFeeStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static parkinglot.model.Vehicle.*;
import static parkinglot.model.Vehicle.BUS;

class AirportFeeStrategyFactoryTest {
    @Test
    void ShouldReturnStrategyForTwoWheelerWhenVehicleIsMotorcycle() {
        AirportFeeStrategyFactory airportFeeStrategyFactory = new AirportFeeStrategyFactory();

        ParkingFeeStrategy parkingFeeStrategy = airportFeeStrategyFactory.getParkingFeeStrategy(MOTORCYCLE);

        assertEquals(TwoWheelerAirportFeeStrategy.class, parkingFeeStrategy.getClass());
    }

    @Test
    void ShouldReturnStrategyForTwoWheelerWhenVehicleIsScooter() {
        AirportFeeStrategyFactory airportFeeStrategyFactory = new AirportFeeStrategyFactory();

        ParkingFeeStrategy parkingFeeStrategy = airportFeeStrategyFactory.getParkingFeeStrategy(SCOOTER);

        assertEquals(TwoWheelerAirportFeeStrategy.class, parkingFeeStrategy.getClass());
    }

    @Test
    void ShouldReturnStrategyForFourWheelerWhenVehicleIsCar() {
        AirportFeeStrategyFactory airportFeeStrategyFactory = new AirportFeeStrategyFactory();

        ParkingFeeStrategy parkingFeeStrategy = airportFeeStrategyFactory.getParkingFeeStrategy(CAR);

        assertEquals(FourWheelerAirportFeeStrategy.class, parkingFeeStrategy.getClass());
    }

    @Test
    void ShouldReturnStrategyForFourWheelerWhenVehicleIsSuv() {
        AirportFeeStrategyFactory airportFeeStrategyFactory = new AirportFeeStrategyFactory();

        ParkingFeeStrategy parkingFeeStrategy = airportFeeStrategyFactory.getParkingFeeStrategy(SUV);

        assertEquals(FourWheelerAirportFeeStrategy.class, parkingFeeStrategy.getClass());
    }

    @Test
    void shouldThrowExceptionForUnsupportedVehicle() {
        AirportFeeStrategyFactory airportFeeStrategyFactory = new AirportFeeStrategyFactory();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> airportFeeStrategyFactory.getParkingFeeStrategy(BUS));

        assertEquals("Vehicle BUS not allowed in Airport parking", exception.getMessage());
    }
}