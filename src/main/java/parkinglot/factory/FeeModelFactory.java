package parkinglot.factory;

import parkinglot.model.FeeModel;

public class FeeModelFactory {
    public FeeModelStrategyFactory getFeeModelStrategyFactory(FeeModel feeModel) {
        switch (feeModel) {
            case MALL:
                return new MallFeeStrategyFactory();
            case STADIUM:
                return new StadiumFeeStrategyFactory();
            case AIRPORT:
                return new AirportFeeStrategyFactory();
            default:
                throw new IllegalArgumentException("Given Fee Model " + feeModel + " not supported");
        }
    }
}
