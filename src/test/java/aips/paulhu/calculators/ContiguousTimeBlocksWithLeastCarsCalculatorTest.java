package aips.paulhu.calculators;

import aips.paulhu.domain.CarsPerHalfHour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static aips.paulhu.calculators.CalculatorTestSupport.dataRow;
import static org.junit.jupiter.api.Assertions.*;

class ContiguousTimeBlocksWithLeastCarsCalculatorTest {

    private static final int TIME_BLOCK_COUNT = 3;

    private ContiguousTimeBlocksWithLeastCarsCalculator unitUnderTest;

    @Test
    void failWhenInputsIsNull() {
        assertThrows(NullPointerException.class, () -> unitUnderTest.contiguousTimeBlocksWithLeastCarsIn(null));
    }

    @Test
    void findContiguousHalfHoursWithLeastCars() {
        List<CarsPerHalfHour> inputs = List.of(
                dataRow("2016-12-01T05:00:00", 2),
                dataRow("2016-12-01T05:30:00", 1),
                dataRow("2016-12-01T06:00:00", 1),
                dataRow("2016-12-01T06:30:00", 1),
                dataRow("2016-12-01T07:00:00", 2));

        List<CarsPerHalfHour> results = unitUnderTest.contiguousTimeBlocksWithLeastCarsIn(inputs);

        assertEquals(3, results.size());

        assertEquals(dataRow("2016-12-01T05:30:00", 1), results.get(0));
        assertEquals(dataRow("2016-12-01T06:00:00", 1), results.get(1));
        assertEquals(dataRow("2016-12-01T06:30:00", 1), results.get(2));
    }

    @Test
    void returnEmptyListIfInputIsLessThanRequiredTimeInterval() {
        List<CarsPerHalfHour> inputs = List.of(
                dataRow("2016-12-01T05:00:00", 2),
                dataRow("2016-12-01T05:30:00", 1));

        List<CarsPerHalfHour> results = unitUnderTest.contiguousTimeBlocksWithLeastCarsIn(inputs);

        assertTrue(results.isEmpty());
    }

    @Test
    void returnEmptyListIfNoContiguousRecordsWithRequiredDuration() {
        List<CarsPerHalfHour> inputs = List.of(
                dataRow("2016-12-01T05:00:00", 1),
                dataRow("2016-12-01T05:30:00", 1),
                dataRow("2016-12-02T06:00:00", 1),
                dataRow("2016-12-02T06:30:00", 1));

        List<CarsPerHalfHour> results = unitUnderTest.contiguousTimeBlocksWithLeastCarsIn(inputs);

        assertTrue(results.isEmpty());
    }

    @Test
    void ignoreRecordBlocksWithTimeGap() {
        List<CarsPerHalfHour> inputs = List.of(
                dataRow("2016-12-01T05:00:00", 10),
                dataRow("2016-12-01T05:30:00", 3),  // start of the block
                dataRow("2016-12-01T06:00:00", 3),
                dataRow("2016-12-01T06:30:00", 3),  // end of the block
                dataRow("2016-12-01T07:00:00", 4),
                dataRow("2016-12-02T05:00:00", 1)); // not qualified as there's a time gap

        List<CarsPerHalfHour> results = unitUnderTest.contiguousTimeBlocksWithLeastCarsIn(inputs);

        assertEquals(3, results.size());

        assertEquals(dataRow("2016-12-01T05:30:00", 3), results.get(0));
        assertEquals(dataRow("2016-12-01T06:00:00", 3), results.get(1));
        assertEquals(dataRow("2016-12-01T06:30:00", 3), results.get(2));
    }

    @BeforeEach
    void init() {
        unitUnderTest = new ContiguousTimeBlocksWithLeastCarsCalculator(TIME_BLOCK_COUNT);
    }
}