package parkinglot.strategy;

import org.junit.jupiter.api.Test;
import parkinglot.component.ParkingTicket;
import parkinglot.util.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SixWheelerMallFeeStrategyTest {
    @Test
    void shouldReturnTotalFee() {
        SixWheelerMallFeeStrategy sixWheelerMallFeeModel = new SixWheelerMallFeeStrategy();
        LocalDateTimeUtil localDateTimeUtil = mock(LocalDateTimeUtil.class);
        ParkingTicket parkingTicket = new ParkingTicket(localDateTimeUtil, mock(AtomicInteger.class));
        when(localDateTimeUtil.parseToLocalDateTIme(any()))
                .thenReturn(LocalDateTime.of(2023, 2, 6, 9, 0))
                .thenReturn(LocalDateTime.of(2023, 2, 6, 10, 0));

        double actualFee = sixWheelerMallFeeModel.getFee(parkingTicket, "mocked date");

        assertEquals(50.0, actualFee);
    }
}