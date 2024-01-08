package parkinglot.factory;

import parkinglot.model.Vehicle;
import parkinglot.strategy.FourWheelerStadiumFeeStrategy;
import parkinglot.strategy.ParkingFeeStrategy;
import parkinglot.strategy.TwoWheelerStadiumFeeStrategy;

public class StadiumFeeStrategyFactory implements FeeModelStrategyFactory {
    public ParkingFeeStrategy getParkingFeeStrategy(Vehicle vehicle) {
        switch (vehicle) {
            case MOTORCYCLE:
            case SCOOTER:
                return new TwoWheelerStadiumFeeStrategy();
            case CAR:
            case SUV:
                return new FourWheelerStadiumFeeStrategy();
            default:
                throw new IllegalArgumentException("Vehicle " + vehicle + " not allowed in Stadium parking");
        }
    }
}
