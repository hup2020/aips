package aips.paulhu;

import aips.paulhu.calculators.CarsPerDayCalculator;
import aips.paulhu.calculators.ContiguousTimeBlocksWithLeastCarsCalculator;
import aips.paulhu.calculators.TopHalfHoursCalculator;
import aips.paulhu.calculators.TotalCarsCalculator;
import aips.paulhu.domain.CarsPerHalfHour;
import aips.paulhu.utils.InputFileParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TrafficReportApplication {

    private TotalCarsCalculator totalCarsCalculator;
    private CarsPerDayCalculator carsPerDayCalculator;
    private TopHalfHoursCalculator topThreeHalfHoursCalculator;
    private ContiguousTimeBlocksWithLeastCarsCalculator leastCarsInOneHourAndHalfCalculator;

    public TrafficReportApplication() {
        this.totalCarsCalculator = new TotalCarsCalculator();
        this.carsPerDayCalculator = new CarsPerDayCalculator();
        this.topThreeHalfHoursCalculator = new TopHalfHoursCalculator(3);
        this.leastCarsInOneHourAndHalfCalculator = new ContiguousTimeBlocksWithLeastCarsCalculator(3);
    }

    private void printReportFor(List<CarsPerHalfHour> inputs) {
        System.out.println("Total cars seen: " + totalCarsCalculator.totalCarsIn(inputs));

        System.out.println("\nCars seen per day: ");
        printResults(carsPerDayCalculator.carsPerDayFrom(inputs));

        System.out.println("\nTop 3 half hours with most cars:");
        printResults(topThreeHalfHoursCalculator.topHalfHoursWithMostCarsIn(inputs));

        System.out.println("\n1.5 hours with least cars:");
        printResults(leastCarsInOneHourAndHalfCalculator.contiguousTimeBlocksWithLeastCarsIn(inputs));
    }


    public static void main(String[] args) throws IOException {
        new TrafficReportApplication().printReportFor(sampleData());
    }

    private static List<CarsPerHalfHour> sampleData() throws IOException {
        File inputFile = new File(TrafficReportApplication.class.getClassLoader().getResource("sample_input").getFile());

        return new InputFileParser().parse(inputFile);
    }

    private static void printResults(List results) {
        results.stream().forEach(result -> System.out.println(result));
    }
}
