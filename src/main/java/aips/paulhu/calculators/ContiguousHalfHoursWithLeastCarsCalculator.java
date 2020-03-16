package aips.paulhu.calculators;

import aips.paulhu.domain.CarsPerHalfHour;
import aips.paulhu.utils.SlidingWindowStream;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.Validate.notNull;

public class ContiguousHalfHoursWithLeastCarsCalculator {

    private int contiguousHalfHours;
    private TotalCarsCalculator totalCarsCalculator;

    public ContiguousHalfHoursWithLeastCarsCalculator(int contiguousHalfHours) {
        this.contiguousHalfHours = contiguousHalfHours;
        this.totalCarsCalculator = new TotalCarsCalculator();
    }

    /**
     * Returns contiguous half hour blocks with least car counts. For example, if contiguousHalfHours is 3, then this
     * method will find 1.5 contiguous hours with least car counts.
     *
     * If there is no contiguous half hour blocks, it will return an empty list.
     * If there are multiple contiguous half hour blocks with same car counts, it will return earliest time block.
     *
     * @param recordsInTimeSequence a list of {@link CarsPerHalfHour} in time sequence
     * @return contiguous half hour block with least total car count
     */
    public List<CarsPerHalfHour> contiguousHalfHoursWithLeastCarsIn(List<CarsPerHalfHour> recordsInTimeSequence) {
        notNull(recordsInTimeSequence, "recordsInTimeSequence cannot be null");

        if (recordsInTimeSequence.size() < this.contiguousHalfHours)
            return emptyList();

        return totalCarCountsInContiguousTimeBlocks(recordsInTimeSequence).entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .findFirst()
                .map(mapEntry -> mapEntry.getKey())
                .orElse(emptyList());
    }

    /**
     * Assuming fixed time interval (1.5 hrs), this problem is best solved by sliding window algorithm. There's an added
     * complexity of possible missing record (as shown in the sample data), which can be solved by filter out any non-contiguous
     * sliding window data.
     */
    private Map<List<CarsPerHalfHour>, Integer> totalCarCountsInContiguousTimeBlocks(List<CarsPerHalfHour> recordsInTimeSequence) {
        return SlidingWindowStream
                .stream(recordsInTimeSequence, this.contiguousHalfHours)
                .filter(recordsInSlidingWindow -> isContiguousRecords(recordsInSlidingWindow))
                .collect(Collectors.toMap(
                        Function.identity(),
                        recordsInSlidingWindow -> totalCarsCalculator.totalCarsIn(recordsInSlidingWindow)));
    }

    /**
     * This can be either implemented as a 'looking ahead / back' for loop (if performance is a concern),
     * OR sliding window of size 2 (if readability is a priority, assuming familiar with concept of sliding window)
     */
    private boolean isContiguousRecords(List<CarsPerHalfHour> recordsInSlidingWindow) {
        return SlidingWindowStream
                .stream(recordsInSlidingWindow, 2)
                .allMatch(adjacentRecords -> isContiguousTwoHalfHours(adjacentRecords));
    }

    private boolean isContiguousTwoHalfHours(List<CarsPerHalfHour> adjacentRecords) {
        CarsPerHalfHour firstRecord = adjacentRecords.get(0);
        CarsPerHalfHour secondRecord = adjacentRecords.get(1);

        return firstRecord.getTimestamp().plusMinutes(30).equals(secondRecord.getTimestamp());
    }
}
