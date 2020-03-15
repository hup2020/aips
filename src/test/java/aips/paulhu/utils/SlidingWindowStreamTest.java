package aips.paulhu.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowStreamTest {

    @Test
    void failWhenListIsNull() {
        assertThrows(NullPointerException.class, () -> SlidingWindowStream.stream(null, 3));
    }

    @Test
    void failWhenWindowSizeLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> SlidingWindowStream.stream(List.of(1, 2), 0));
    }

    @Test
    void windowSizeIsOne() {
        List<List<Integer>> results = SlidingWindowStream.stream(List.of(1, 2, 3), 1).collect(toList());

        assertEquals(3, results.size());
        assertTrue(results.contains(List.of(1)));
        assertTrue(results.contains(List.of(2)));
        assertTrue(results.contains(List.of(3)));
    }

    @Test
    void listSizeEqualsToWindowSize() {
        List<List<Integer>> results = SlidingWindowStream.stream(List.of(1, 2, 3), 3).collect(toList());

        assertEquals(1, results.size());
        assertTrue(results.contains(List.of(1, 2, 3)));
    }

    @Test
    void listSizeLessThanWindowSize() {
        List<List<Integer>> results = SlidingWindowStream.stream(List.of(1, 2), 3).collect(toList());

        assertEquals(1, results.size());
        assertTrue(results.contains(List.of(1, 2)));
    }

    @Test
    void slidingWindow() {
        List<List<Integer>> results = SlidingWindowStream.stream(List.of(1, 2, 3, 4, 5), 3).collect(toList());

        assertEquals(3, results.size());
        assertTrue(results.contains(List.of(1, 2, 3)));
        assertTrue(results.contains(List.of(2, 3, 4)));
        assertTrue(results.contains(List.of(3, 4, 5)));
    }
}