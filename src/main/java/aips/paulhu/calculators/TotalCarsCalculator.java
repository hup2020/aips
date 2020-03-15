package aips.paulhu.calculators;

import aips.paulhu.domain.CarsPerHalfHour;

import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

public class TotalCarsCalculator {

    public int totalCarsIn(List<CarsPerHalfHour> inputs) {
        notNull(inputs, "inputs cannot be null");

        return inputs
                .stream()
                .mapToInt(CarsPerHalfHour::getCount)
                .sum();
    }
}
