package aips.paulhu.calculators;

import aips.paulhu.domain.CarsPerHalfHour;

import java.time.LocalDateTime;

final class CalculatorTestSupport {

    static CarsPerHalfHour dataRow(String timestamp, int count) {
        return new CarsPerHalfHour(LocalDateTime.parse(timestamp), count);
    }

    private CalculatorTestSupport() {}
}
