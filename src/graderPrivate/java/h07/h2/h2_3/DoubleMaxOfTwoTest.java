package h07.h2.h2_3;

import h07.doubleoperators.DoubleMaxOfTwo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;

public class DoubleMaxOfTwoTest {

    private static final String PATH_TO_CSV = "/h1/DoubleMaxOfTwoTest.csv";

    private static final DoubleMaxOfTwo OP = new DoubleMaxOfTwo();

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1)
    void applyAsDoubleTest(double left, double right, double expected) {
        var context = contextBuilder()
            .add("Left value", left)
            .add("Right value", right)
            .build();
        double actual = OP.applyAsDouble(left, right);

        assertEquals(
            expected,
            actual,
            context,
            r -> String.format(
                "Expected %s to return %f but it returned %f instead!",
                "applyAsDouble",
                expected,
                actual
            )
        );
    }
}
