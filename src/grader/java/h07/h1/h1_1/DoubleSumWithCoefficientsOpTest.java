package h07.h1.h1_1;

import h07.doubleoperators.DoubleSumWithCoefficientsOp;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;
import static h07.Utils.getGeneralInfo;

public class DoubleSumWithCoefficientsOpTest {

    private static final String PATH_TO_CSV = "/h1/DoubleSumWithCoefficientsOpTestInputs.csv";

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1)
    void applyAsDoubleTest(double coeff1, double coeff2, double left, double right, double expected) {
        DoubleSumWithCoefficientsOp op = new DoubleSumWithCoefficientsOp(coeff1, coeff2);

        double actual = op.applyAsDouble(left, right);

        assertEquals(
            expected,
            actual,
            getGeneralInfo(
                "Coefficient 1: " + coeff1 + ", coefficient 2: " + coeff2 +
                    ", left value: " + left + ", right value: " + right
            ) + "Expected \"applyAsDouble\" to return " + expected + " but it returned " + actual + " instead!"
        );
    }

}
