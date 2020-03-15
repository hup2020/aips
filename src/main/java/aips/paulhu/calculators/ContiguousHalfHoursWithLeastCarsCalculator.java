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

        return totalCountsByContiguousTimeBlocks(recordsInTimeSequence).entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .findFirst()
                .map(mapEntry -> mapEntry.getKey())
                .orElse(emptyList());
    }

    /**
     * Build lookup map of total car counts in contiguous half hours, which is indexed by contiguous half hours. The algorithm
     * break down the inputs into blocks of half hours using 'sliding window', then filter out non-contiguous time blocks.
     */
    private Map<List<CarsPerHalfHour>, Integer> totalCountsByContiguousTimeBlocks(List<CarsPerHalfHour> recordsInTimeSequence) {
        return SlidingWindowStream
                .stream(recordsInTimeSequence, this.contiguousHalfHours)
                .filter(recordsInSlidingWindow -> isContiguousRecords(recordsInSlidingWindow))
                .collect(Collectors.toMap(Function.identity(), recordsInSlidingWindow -> sumCounts(recordsInSlidingWindow)));
    }

    private int sumCounts(List<CarsPerHalfHour> recordsInSlidingWindow) {
        return totalCarsCalculator.totalCarsIn(recordsInSlidingWindow);
    }

    /**
     * Break the records in sliding window into block of 2 and make sure each pair is contiguous in time.
     * It seems the sample data file has time gaps, which is assumed to be caused by traffic counter temporarily offline.
     * If there's guarantee the input file always have contiguous time records, then this check is not necessary.
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
