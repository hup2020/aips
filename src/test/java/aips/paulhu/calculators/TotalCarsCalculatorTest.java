package aips.paulhu.calculators;

import aips.paulhu.domain.CarsPerHalfHour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static aips.paulhu.calculators.CalculatorTestSupport.dataRow;
import static org.junit.jupiter.api.Assertions.*;

class TotalCarsCalculatorTest {

    private TotalCarsCalculator unitUnderTest;

    @Test
    void failWhenInputsIsNull() {
        assertThrows(NullPointerException.class, () -> unitUnderTest.totalCarsIn(null));
    }

    @Test
    void calculateTotal() {
        List<CarsPerHalfHour> inputs = List.of(
                dataRow("2016-12-01T05:00:00", 4),
                dataRow("2016-12-01T05:30:00", 5),
                dataRow("2016-12-01T06:00:00", 6));

        assertEquals(15, unitUnderTest.totalCarsIn(inputs));
    }

    @BeforeEach
    void init() {
        unitUnderTest = new TotalCarsCalculator();
    }
}