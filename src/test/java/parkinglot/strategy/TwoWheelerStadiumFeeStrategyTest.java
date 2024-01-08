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

class TwoWheelerStadiumFeeStrategyTest {
    private final TwoWheelerStadiumFeeStrategy twoWheelerStadiumFeeStrategy = new TwoWheelerStadiumFeeStrategy();
    private final LocalDateTimeUtil localDateTimeUtil = mock(LocalDateTimeUtil.class);
    private final ParkingTicket parkingTicket = new ParkingTicket(localDateTimeUtil, mock(AtomicInteger.class));

    @Test
    void shouldReturnCorrectTotalFeeForParkedHoursBetween0To4() {
        when(localDateTimeUtil.parseToLocalDateTIme(any()))
                .thenReturn(LocalDateTime.of(2023, 2, 6, 1, 0))
                .thenReturn(LocalDateTime.of(2023, 2, 6, 4, 59));

        double actualFee = twoWheelerStadiumFeeStrategy.getFee(parkingTicket, "mocked date");

        assertEquals(30.0, actualFee);
    }

    @Test
    void shouldReturnCorrectTotalFeeForParkedHoursBetween4To12() {
        when(localDateTimeUtil.parseToLocalDateTIme(any()))
                .thenReturn(LocalDateTime.of(2023, 2, 6, 1, 0))
                .thenReturn(LocalDateTime.of(2023, 2, 6, 6, 59));

        double actualFee = twoWheelerStadiumFeeStrategy.getFee(parkingTicket, "mocked date");

        assertEquals(60.0, actualFee);
    }

    @Test
    void shouldReturnCorrectTotalFeeForParkedHoursMoreThan12() {
        when(localDateTimeUtil.parseToLocalDateTIme(any()))
                .thenReturn(LocalDateTime.of(2023, 2, 6, 1, 0))
                .thenReturn(LocalDateTime.of(2023, 2, 6, 15, 59));

        double actualFee = twoWheelerStadiumFeeStrategy.getFee(parkingTicket, "mocked date");

        assertEquals(390.0, actualFee);
    }
}