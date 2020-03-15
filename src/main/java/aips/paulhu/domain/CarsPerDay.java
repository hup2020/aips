package aips.paulhu.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CarsPerDay {

    private static final String DATE_COUNT_SEPARATOR = " ";
    private static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LocalDate date;
    private int count;

    public CarsPerDay(LocalDate date, int count) {
        this.date = date;
        this.count = count;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return date.format(DATE_OUTPUT_FORMAT) + DATE_COUNT_SEPARATOR + count;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
