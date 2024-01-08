package parkinglot.factory;

import org.junit.jupiter.api.Test;
import parkinglot.strategy.FourWheelerStadiumFeeStrategy;
import parkinglot.strategy.ParkingFeeStrategy;
import parkinglot.strategy.TwoWheelerStadiumFeeStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static parkinglot.model.Vehicle.*;

class StadiumFeeStrategyFactoryTest {
    @Test
    void ShouldReturnStrategyForTwoWheelerWhenVehicleIsMotorcycle() {
        StadiumFeeStrategyFactory stadiumFeeStrategyFactory = new StadiumFeeStrategyFactory();

        ParkingFeeStrategy parkingFeeStrategy = stadiumFeeStrategyFactory.getParkingFeeStrategy(MOTORCYCLE);

        assertEquals(TwoWheelerStadiumFeeStrategy.class, parkingFeeStrategy.getClass());
    }

    @Test
    void ShouldReturnStrategyForFourWheelerWhenVehicleIsCar() {
        StadiumFeeStrategyFactory stadiumFeeStrategyFactory = new StadiumFeeStrategyFactory();

        ParkingFeeStrategy parkingFeeStrategy = stadiumFeeStrategyFactory.getParkingFeeStrategy(CAR);

        assertEquals(FourWheelerStadiumFeeStrategy.class, parkingFeeStrategy.getClass());
    }

    @Test
    void ShouldReturnStrategyForFourWheelerWhenVehicleIsSuv() {
        StadiumFeeStrategyFactory stadiumFeeStrategyFactory = new StadiumFeeStrategyFactory();

        ParkingFeeStrategy parkingFeeStrategy = stadiumFeeStrategyFactory.getParkingFeeStrategy(SUV);

        assertEquals(FourWheelerStadiumFeeStrategy.class, parkingFeeStrategy.getClass());
    }

    @Test
    void shouldThrowExceptionForUnsupportedVehicle() {
        StadiumFeeStrategyFactory stadiumFeeStrategyFactory = new StadiumFeeStrategyFactory();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> stadiumFeeStrategyFactory.getParkingFeeStrategy(BUS));

        assertEquals("Vehicle BUS not allowed in Stadium parking", exception.getMessage());
    }
}