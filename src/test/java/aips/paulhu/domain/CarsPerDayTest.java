package aips.paulhu.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CarsPerDayTest {

    @Test
    void testEqualsAndHashcode() {
        CarsPerDay obj1 = new CarsPerDay(LocalDate.of(2016, 12, 1), 3);
        CarsPerDay obj2 = new CarsPerDay(LocalDate.of(2016, 12, 1), 3);

        assertEquals(obj1, obj2);
        assertEquals(obj1.hashCode(), obj2.hashCode());
    }
}