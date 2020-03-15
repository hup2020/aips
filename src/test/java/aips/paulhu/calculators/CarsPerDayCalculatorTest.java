package aips.paulhu.calculators;

import aips.paulhu.domain.CarsPerDay;
import aips.paulhu.domain.CarsPerHalfHour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static aips.paulhu.calculators.CalculatorTestSupport.dataRow;
import static org.junit.jupiter.api.Assertions.*;

class CarsPerDayCalculatorTest {

    private CarsPerDayCalculator unitUnderTest;

    @Test
    void failWhenInputsIsNull() {
        assertThrows(NullPointerException.class, () -> unitUnderTest.carsPerDayFrom(null));
    }

    @Test
    void calculateCarsPerDay() {
        List<CarsPerHalfHour> inputs = List.of(
                dataRow("2016-12-01T05:00:00", 4),
                dataRow("2016-12-01T05:30:00", 5),
                dataRow("2016-12-02T06:00:00", 6));

        List<CarsPerDay> results = unitUnderTest.carsPerDayFrom(inputs);

        assertEquals(2, results.size());

        assertEquals(LocalDate.parse("2016-12-01"), results.get(0).getDate());
        assertEquals(9, results.get(0).getCount());

        assertEquals(LocalDate.parse("2016-12-02"), results.get(1).getDate());
        assertEquals(6, results.get(1).getCount());
    }

    @BeforeEach
    void init() {
        unitUnderTest = new CarsPerDayCalculator();
    }
}