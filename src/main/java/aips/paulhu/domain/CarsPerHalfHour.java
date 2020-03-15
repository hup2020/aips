package aips.paulhu.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CarsPerHalfHour {

    private static final String TIMESTAMP_COUNT_SEPARATOR = " ";
    private static final DateTimeFormatter ISO_8601 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private LocalDateTime timestamp;
    private int count;

    public CarsPerHalfHour(LocalDateTime timestamp, int count) {
        this.timestamp = timestamp;
        this.count = count;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getCount() {
        return count;
    }

    public LocalDate getDate() {
        return timestamp.toLocalDate();
    }

    @Override
    public String toString() {
        return timestamp.format(ISO_8601) + TIMESTAMP_COUNT_SEPARATOR + count;
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
