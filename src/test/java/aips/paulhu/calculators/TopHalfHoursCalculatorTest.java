package aips.paulhu.calculators;

import aips.paulhu.domain.CarsPerHalfHour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static aips.paulhu.calculators.CalculatorTestSupport.dataRow;
import static org.junit.jupiter.api.Assertions.*;

class TopHalfHoursCalculatorTest {

    private TopHalfHoursCalculator unitUnderTest;

    @Test
    void failWhenInputsIsNull() {
        assertThrows(NullPointerException.class, () -> unitUnderTest.topHalfHoursWithMostCarsIn(null));
    }

    @Test
    void getTopHalfHoursWithMostCars() {
        List<CarsPerHalfHour> inputs = List.of(
                dataRow("2016-12-01T05:00:00", 5),
                dataRow("2016-12-01T05:30:00", 16),
                dataRow("2016-12-01T06:00:00", 20),
                dataRow("2016-12-01T06:30:00", 6));

        List<CarsPerHalfHour> results = unitUnderTest.topHalfHoursWithMostCarsIn(inputs);

        assertEquals(2, results.size());

        assertEquals(dataRow("2016-12-01T06:00:00", 20), results.get(0));
        assertEquals(dataRow("2016-12-01T05:30:00", 16), results.get(1));
    }

    @Test
    void whenCountsAreEqualThenEarliestRecordWins() {
        List<CarsPerHalfHour> inputs = List.of(
                dataRow("2016-12-01T05:00:00", 16),
                dataRow("2016-12-01T05:30:00", 16),
                dataRow("2016-12-01T06:00:00", 20),
                dataRow("2016-12-01T06:30:00", 6));

        List<CarsPerHalfHour> results = unitUnderTest.topHalfHoursWithMostCarsIn(inputs);

        assertEquals(2, results.size());

        assertEquals(dataRow("2016-12-01T06:00:00", 20), results.get(0));
        assertEquals(dataRow("2016-12-01T05:00:00", 16), results.get(1));
    }

    @BeforeEach
    void init() {
        unitUnderTest = new TopHalfHoursCalculator(2);
    }
}