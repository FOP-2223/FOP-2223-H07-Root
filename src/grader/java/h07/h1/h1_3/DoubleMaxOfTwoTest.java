package h07.h1.h1_3;

import h07.doubleoperators.DoubleMaxOfTwo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;
import static h07.Utils.getGeneralInfo;

public class DoubleMaxOfTwoTest {

    private static final String PATH_TO_CSV = "/h1/DoubleMaxOfTwoTest.csv";

    private static final DoubleMaxOfTwo OP = new DoubleMaxOfTwo();

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1)
    void applyAsDoubleTest(double left, double right, double expected) {
        double actual = OP.applyAsDouble(left, right);

        assertEquals(
            expected,
            actual,
            getGeneralInfo("Left value: " + left + ", right value: " + right) +
                "Expected \"applyAsDouble\" to return " + expected + " but it returned " + actual + " instead!"
        );
    }
}
