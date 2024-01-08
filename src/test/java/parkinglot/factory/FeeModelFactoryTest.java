package parkinglot.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static parkinglot.model.FeeModel.*;

class FeeModelFactoryTest {
    @Test
    void shouldReturnMallFeeModelObject() {
        FeeModelFactory feeModelFactory = new FeeModelFactory();

        FeeModelStrategyFactory feeModelStrategyFactory = feeModelFactory.getFeeModelStrategyFactory(MALL);

        assertEquals(MallFeeStrategyFactory.class, feeModelStrategyFactory.getClass());
    }

    @Test
    void shouldReturnStadiumFeeModelObject() {
        FeeModelFactory feeModelFactory = new FeeModelFactory();

        FeeModelStrategyFactory feeModelStrategyFactory = feeModelFactory.getFeeModelStrategyFactory(STADIUM);

        assertEquals(StadiumFeeStrategyFactory.class, feeModelStrategyFactory.getClass());
    }

    @Test
    void shouldReturnAirportFeeModelObject() {
        FeeModelFactory feeModelFactory = new FeeModelFactory();

        FeeModelStrategyFactory feeModelStrategyFactory = feeModelFactory.getFeeModelStrategyFactory(AIRPORT);

        assertEquals(AirportFeeStrategyFactory.class, feeModelStrategyFactory.getClass());
    }

    @Test
    void shouldThrowExceptionIfVehicleNotAllowed() {
        FeeModelFactory feeModelFactory = new FeeModelFactory();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> feeModelFactory.getFeeModelStrategyFactory(APARTMENT));

        assertEquals("Given Fee Model APARTMENT not supported", exception.getMessage());
    }
}