package aips.paulhu.utils;

import java.time.format.DateTimeFormatter;

public final class Constants {

    // ISO-8601 is the default format for LocalDateTime
    public static final DateTimeFormatter ISO_8601 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public static final String TIMESTAMP_COUNT_SEPARATOR = " ";

    private Constants() {}
}
