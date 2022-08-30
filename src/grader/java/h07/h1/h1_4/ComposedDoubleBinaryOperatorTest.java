package h07.h1.h1_4;

import h07.doubleoperators.ComposedDoubleBinaryOperator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.function.DoubleBinaryOperator;

import static org.junit.jupiter.api.Assertions.*;
import static h07.Utils.*;

public class ComposedDoubleBinaryOperatorTest {

    private static final String PATH_TO_CSV = "/h1/ComposedDoubleBinaryOperatorTest.csv";

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1)
    void applyAsDoubleTest(String op1, String op2, String op3, double left, double right, double expected) {
        DoubleBinaryOperator operator1 = convertStringToOperator(op1);
        DoubleBinaryOperator operator2 = convertStringToOperator(op2);
        DoubleBinaryOperator operator3 = convertStringToOperator(op3);

        ComposedDoubleBinaryOperator operator = new ComposedDoubleBinaryOperator(
            operator1,
            operator2,
            operator3
        );

        double actual = operator.applyAsDouble(left, right);

        assertEquals(
            expected,
            actual,
            getGeneralInfo(
                "First operator: " + op1 + ", second operator: " + op2 + ", third operator: " + op3 +
                ", left value: " + left + ", right value: " + right
            ) + "Expected \"applyAsDouble\" to return " + expected + " but it returned " + actual + " instead!"
        );
    }

}
