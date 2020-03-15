package aips.paulhu.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CarsPerDayTest {

    private static final LocalDate LOCAL_DATE = LocalDate.of(2016, 12, 1);

    @Test
    void testEqualsAndHashcode() {
        CarsPerDay obj1 = new CarsPerDay(LOCAL_DATE, 3);
        CarsPerDay obj2 = new CarsPerDay(LOCAL_DATE, 3);

        assertEquals(obj1, obj2);
        assertEquals(obj1.hashCode(), obj2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("2016-12-01 3", new CarsPerDay(LOCAL_DATE, 3).toString());
    }
}