package results;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import suites.TestSuiteJUnit5;

public class JUnitResultsAnalyser {
    public static void main(String[] args) {
        Result result =  JUnitCore.runClasses(TestSuiteJUnit5.class);

        getCommonStatistics(result);

        if(!result.wasSuccessful()) {
            System.out.println("Failed tests: ");

            result.getFailures().stream().forEach(System.out::println);
        }
    }

    private static void getCommonStatistics(Result result) {
        System.out.println("Test time: " + result.getRunTime() + " ms");
        System.out.println("Test cases passed: " + result.getRunCount());
        System.out.println("Tests failed: " + result.getFailures().stream().count());
    }
}
