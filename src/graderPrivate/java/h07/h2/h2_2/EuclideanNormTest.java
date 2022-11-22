package h07.h2.h2_2;

import h07.doubleoperators.EuclideanNorm;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class EuclideanNormTest {

    private static final String PATH_TO_CSV = "/h1/EuclideanNormTest.csv";

    private static final EuclideanNorm EUCLIDEAN_NORM = new EuclideanNorm();

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1)
    void applyAsDoubleTest(double x, double y, double expected) {
        var context = contextBuilder()
            .add("x", x)
            .add("y", y)
            .build();
        double actual = EUCLIDEAN_NORM.applyAsDouble(x, y);

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
