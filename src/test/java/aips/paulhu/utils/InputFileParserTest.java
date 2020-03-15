package aips.paulhu.utils;

import aips.paulhu.domain.CarsPerHalfHour;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputFileParserTest {

    @Test
    void testFileParser() throws IOException {
        List<CarsPerHalfHour> results = new InputFileParser().parse(new File("src/test/resources", "test_sample_input"));

        assertEquals(2, results.size());
        assertEquals("2016-12-01T05:00:00 5", results.get(0).toString());
        assertEquals("2016-12-01T05:30:00 12", results.get(1).toString());
    }
}