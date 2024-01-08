package parkinglot.factory;

import parkinglot.model.Vehicle;
import parkinglot.strategy.ParkingFeeStrategy;

public interface FeeModelStrategyFactory {
    ParkingFeeStrategy getParkingFeeStrategy(Vehicle vehicle);
}
