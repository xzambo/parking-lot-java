package parkinglot.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parkinglot.factory.FeeModelFactory;
import parkinglot.factory.MallFeeStrategyFactory;
import parkinglot.strategy.TwoWheelerMallFeeStrategy;
import parkinglot.util.LocalDateTimeUtil;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static parkinglot.model.FeeModel.MALL;
import static parkinglot.model.Vehicle.MOTORCYCLE;

class ExitTicketTest {
    private final LocalDateTimeUtil localDateTimeUtil = mock(LocalDateTimeUtil.class);
    private final FeeModelFactory feeModelFactory = mock(FeeModelFactory.class);
    private final ParkingTicket parkingTicket = mock(ParkingTicket.class);
    private final AtomicInteger atomicInteger = mock(AtomicInteger.class);
    private final TwoWheelerMallFeeStrategy twoWheelerMallFeeStrategy = mock(TwoWheelerMallFeeStrategy.class);
    private final MallFeeStrategyFactory mallFeeStrategyFactory = mock(MallFeeStrategyFactory.class);
    private final ExitTicket exitTicket = new ExitTicket(localDateTimeUtil, feeModelFactory, atomicInteger, MALL);

    @BeforeEach
    void setUp() {
        when(localDateTimeUtil.getLocalDateTime()).thenReturn("06-Feb-2023 10:00:00");
        when(feeModelFactory.getFeeModelStrategyFactory(MALL)).thenReturn(mallFeeStrategyFactory);
        when(mallFeeStrategyFactory.getParkingFeeStrategy(MOTORCYCLE)).thenReturn(twoWheelerMallFeeStrategy);
        when(twoWheelerMallFeeStrategy.getFee(any(), any())).thenReturn(80.0);
        when(parkingTicket.getEntryDateTime()).thenReturn("06-Feb-2023 09:00:00");
    }

    @Test
    void shouldCallLocalDateTimeUtilToGenerateExitDatetime() {
        exitTicket.generateExitTicket(MOTORCYCLE, parkingTicket);

        verify(localDateTimeUtil).getLocalDateTime();
    }

    @Test
    void shouldCallFeeModelFactoryToGetFeeModelObject() {
        exitTicket.generateExitTicket(MOTORCYCLE, parkingTicket);

        verify(feeModelFactory).getFeeModelStrategyFactory(MALL);
    }

    @Test
    void shouldCallFeeModelStrategyFactoryToGetParkingFeeStrategy() {
        exitTicket.generateExitTicket(MOTORCYCLE, parkingTicket);

        verify(mallFeeStrategyFactory).getParkingFeeStrategy(MOTORCYCLE);
    }

    @Test
    void shouldCallParkingFeeStrategyFactoryToGetGetTotalFee() {
        exitTicket.generateExitTicket(MOTORCYCLE, parkingTicket);

        verify(twoWheelerMallFeeStrategy).getFee(parkingTicket, "06-Feb-2023 10:00:00");
    }

    @Test
    void shouldSetExitDateTime() {
        ExitTicket actualExitTicket = exitTicket.generateExitTicket(MOTORCYCLE, parkingTicket);

        assertEquals("06-Feb-2023 10:00:00", actualExitTicket.getExitDateTime());
    }
}