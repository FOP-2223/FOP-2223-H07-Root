package h07.h1.h1_2;

import h07.doubleoperators.EuclideanNorm;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;
import static h07.Utils.getGeneralInfo;

public class EuclideanNormTest {

    private static final String PATH_TO_CSV = "/h1/EuclideanNormTest.csv";

    private static final EuclideanNorm EUCLIDEAN_NORM = new EuclideanNorm();

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1)
    void applyAsDoubleTest(double x, double y, double expected) {
        double actual = EUCLIDEAN_NORM.applyAsDouble(x, y);

        assertEquals(
            expected,
            actual,
            getGeneralInfo("x: " + x + ", y: " + y) +
                "Expected \"applyAsDouble\" to return " + expected + " but it returned " + actual + " instead!"
        );
    }

}
