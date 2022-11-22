package h07.h2.h2_4;

import h07.doubleoperators.ComposedDoubleBinaryOperator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.function.DoubleBinaryOperator;

import static h07.Utils.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;

public class ComposedDoubleBinaryOperatorTest {

    private static final String PATH_TO_CSV = "/h1/ComposedDoubleBinaryOperatorTest.csv";

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1)
    void applyAsDoubleTest(String op1, String op2, String op3, double left, double right, double expected) {
        var context = contextBuilder()
            .add("First operator", op1)
            .add("Second operator", op2)
            .add("Third operator", op3)
            .add("Left value", left)
            .add("Right value", right)
            .build();
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
