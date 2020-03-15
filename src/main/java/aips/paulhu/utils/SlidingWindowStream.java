package aips.paulhu.utils;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Sliding window stream example
 *
 * [1, 2, 3, 4, 5] with sliding window size 3 will produce:
 *
 * [1, 2, 3]
 * [2, 3, 4]
 * [3, 4, 5]
 */
public final class SlidingWindowStream {

    public static <T> Stream<List<T>> stream(List<T> list, int windowSize) {
        notNull(list, "List cannot be null");
        isTrue(windowSize > 0, "Sliding window size must be greater than 0");

        if (list.size() <= windowSize) {
            return Stream.of(list);
        } else {
            return IntStream.range(0, list.size() - windowSize + 1)
                    .mapToObj(start -> list.subList(start, start + windowSize));
        }
    }

    private SlidingWindowStream() {}
}
