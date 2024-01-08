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

class TwoWheelerAirportFeeStrategyTest {
    private final TwoWheelerAirportFeeStrategy twoWheelerAirportFeeStrategy = new TwoWheelerAirportFeeStrategy();
    private final LocalDateTimeUtil localDateTimeUtil = mock(LocalDateTimeUtil.class);
    private final ParkingTicket parkingTicket  = new ParkingTicket(localDateTimeUtil, mock(AtomicInteger.class));

    @Test
    void shouldReturnCorrectTotalFeeForParkedHoursBetween0To1() {
        when(localDateTimeUtil.parseToLocalDateTIme(any()))
                .thenReturn(LocalDateTime.of(2023,2,6,1,0))
                .thenReturn(LocalDateTime.of(2023,2,6,1,59));

        double actualFee = twoWheelerAirportFeeStrategy.getFee(parkingTicket, "mocked date");

        assertEquals(0.0, actualFee);
    }

    @Test
    void shouldReturnCorrectTotalFeeForParkedHoursBetween1To8() {
        when(localDateTimeUtil.parseToLocalDateTIme(any()))
                .thenReturn(LocalDateTime.of(2023,2,6,1,0))
                .thenReturn(LocalDateTime.of(2023,2,6,8,59));

        double actualFee = twoWheelerAirportFeeStrategy.getFee(parkingTicket, "mocked date");

        assertEquals(40.0, actualFee);
    }

    @Test
    void shouldReturnCorrectTotalFeeForParkedHoursBetween8To24() {
        when(localDateTimeUtil.parseToLocalDateTIme(any()))
                .thenReturn(LocalDateTime.of(2023,2,6,1,0))
                .thenReturn(LocalDateTime.of(2023,2,6,23,59));

        double actualFee = twoWheelerAirportFeeStrategy.getFee(parkingTicket, "mocked date");

        assertEquals(60.0, actualFee);
    }

    @Test
    void shouldReturnCorrectTotalFeeForParkedHoursMoreThan1Day() {
        when(localDateTimeUtil.parseToLocalDateTIme(any()))
                .thenReturn(LocalDateTime.of(2023,2,6,1,0))
                .thenReturn(LocalDateTime.of(2023,2,7,1,1));

        double actualFee = twoWheelerAirportFeeStrategy.getFee(parkingTicket, "mocked date");

        assertEquals(160.0, actualFee);
    }
}