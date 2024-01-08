package parkinglot.factory;

import parkinglot.model.Vehicle;
import parkinglot.strategy.FourWheelerMallFeeStrategy;
import parkinglot.strategy.ParkingFeeStrategy;
import parkinglot.strategy.SixWheelerMallFeeStrategy;
import parkinglot.strategy.TwoWheelerMallFeeStrategy;

public class MallFeeStrategyFactory implements FeeModelStrategyFactory {
    public ParkingFeeStrategy getParkingFeeStrategy(Vehicle vehicle) {
        switch (vehicle) {
            case MOTORCYCLE:
            case SCOOTER:
                return new TwoWheelerMallFeeStrategy();
            case CAR:
            case SUV:
                return new FourWheelerMallFeeStrategy();
            case BUS:
            case TRUCK:
                return new SixWheelerMallFeeStrategy();
            default:
                throw new IllegalArgumentException("Vehicle " + vehicle + " not allowed in Mall parking");
        }
    }
}
