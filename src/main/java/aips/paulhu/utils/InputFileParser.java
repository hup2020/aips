package aips.paulhu.utils;

import aips.paulhu.domain.CarsPerHalfHour;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class InputFileParser {

    private static final String TIMESTAMP_COUNT_SEPARATOR = " ";
    private static final DateTimeFormatter ISO_8601 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public List<CarsPerHalfHour> parse(File inputFile) throws IOException {
        return Files.readAllLines(inputFile.toPath())
                .stream()
                .map(line -> vehiclesPerHalfHourFrom(line))
                .collect(Collectors.toList());
    }

    private CarsPerHalfHour vehiclesPerHalfHourFrom(String recordLine) {
        String[] timestampAndCount = recordLine.split(TIMESTAMP_COUNT_SEPARATOR);

        return new CarsPerHalfHour(
                LocalDateTime.parse(timestampAndCount[0], ISO_8601),
                Integer.parseInt(timestampAndCount[1]));
    }
}
