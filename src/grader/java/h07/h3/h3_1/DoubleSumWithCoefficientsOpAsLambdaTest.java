package h07.h3.h3_1;

import h07.doubleoperators.DoubleSumWithCoefficientsOp;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;

public class DoubleSumWithCoefficientsOpAsLambdaTest {

    private static final String PATH_TO_CSV = "";

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1)
    void applyAsDoubleTest(double coeff1, double coeff2, double left, double right, double expected) {
        var context = contextBuilder()
            .add("First coefficient", coeff1)
            .add("Second coefficient", coeff2)
            .add("Left value", left)
            .add("Right value", right)
            .build();
        DoubleSumWithCoefficientsOp op = new DoubleSumWithCoefficientsOp(coeff1, coeff2);

        double actual = op.applyAsDouble(left, right);

        assertEquals(
            expected,
            actual,
            context,
            r -> String.format(
                "Expected the expression to return %f but it returned %f instead!",
                expected,
                actual
            )
        );
    }

}
