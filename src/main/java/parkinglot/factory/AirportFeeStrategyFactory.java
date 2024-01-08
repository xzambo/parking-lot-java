package parkinglot.factory;

import parkinglot.model.Vehicle;
import parkinglot.strategy.FourWheelerAirportFeeStrategy;
import parkinglot.strategy.ParkingFeeStrategy;
import parkinglot.strategy.TwoWheelerAirportFeeStrategy;

import static parkinglot.model.Vehicle.*;

public class AirportFeeStrategyFactory implements FeeModelStrategyFactory {
    public ParkingFeeStrategy getParkingFeeStrategy(Vehicle vehicle) {
        if (vehicle == MOTORCYCLE || vehicle == SCOOTER) {
            return new TwoWheelerAirportFeeStrategy();
        } else if (vehicle == CAR || vehicle == SUV) {
            return new FourWheelerAirportFeeStrategy();
        } else {
            throw new IllegalArgumentException("Vehicle " + vehicle + " not allowed in Airport parking");
        }
    }
}
