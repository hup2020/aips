package aips.paulhu.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CarsPerHalfHourTest {

    private static final LocalDateTime TIMESTAMP = LocalDateTime.of(2016, 12, 1, 5, 0);

    @Test
    void getDate() {
        assertEquals(LocalDate.of(2016, 12, 1), new CarsPerHalfHour(TIMESTAMP, 3).getDate());
    }

    @Test
    void testEqualsAndHashcode() {
        CarsPerHalfHour obj1 = new CarsPerHalfHour(TIMESTAMP, 3);
        CarsPerHalfHour obj2 = new CarsPerHalfHour(TIMESTAMP, 3);

        assertEquals(obj1, obj2);
        assertEquals(obj1.hashCode(), obj2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("2016-12-01T05:00:00 3", new CarsPerHalfHour(TIMESTAMP, 3).toString());
    }
}