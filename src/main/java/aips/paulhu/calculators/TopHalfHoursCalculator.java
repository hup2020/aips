package aips.paulhu.calculators;

import aips.paulhu.domain.CarsPerHalfHour;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.Validate.notNull;

public class TopHalfHoursCalculator {

    private int topHalfHours;

    public TopHalfHoursCalculator(int topHalfHours) {
        this.topHalfHours = topHalfHours;
    }

    public List<CarsPerHalfHour> topHalfHoursWithMostCarsIn(List<CarsPerHalfHour> inputs) {
        notNull(inputs, "inputs cannot be null");

        return inputs
                .stream()
                .sorted(Comparator.comparingInt(CarsPerHalfHour::getCount).reversed())
                .limit(this.topHalfHours)
                .collect(Collectors.toList());
    }
}
