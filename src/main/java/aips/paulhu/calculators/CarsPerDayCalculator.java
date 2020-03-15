package aips.paulhu.calculators;

import aips.paulhu.domain.CarsPerDay;
import aips.paulhu.domain.CarsPerHalfHour;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.Validate.notNull;

public class CarsPerDayCalculator {

    public List<CarsPerDay> carsPerDayFrom(List<CarsPerHalfHour> inputs) {
        notNull(inputs, "inputs cannot be null");

        return carCountsGroupedByDateFrom(inputs).entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(carsPerDay -> new CarsPerDay(carsPerDay.getKey(), carsPerDay.getValue()))
                .collect(Collectors.toList());
    }

    private Map<LocalDate, Integer> carCountsGroupedByDateFrom(List<CarsPerHalfHour> carsPerHalfHour) {
        return carsPerHalfHour
                .stream()
                .collect(Collectors.groupingBy(
                        CarsPerHalfHour::getDate,
                        Collectors.summingInt(CarsPerHalfHour::getCount)
                ));
    }
}
