package h07.h1.h1_3;

import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingScalar;
import h07.operators.DoubleSumOfTwo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.util.Arrays;

import static h07.Utils.convertStringToOperator;
import static h07.h1.H1Utils.getClassContent;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static h07.h1.H1Utils.convertStringToDoubleArray;

public class PairwiseDoubleArrayBinaryOperatorGivingScalarTest {

    private static final String PATH_TO_CSV = "/h1/h1_3/PrivateTestcases.csv";

    @Test
    void testNullInput() {
        PairwiseDoubleArrayBinaryOperatorGivingScalar operator = new PairwiseDoubleArrayBinaryOperatorGivingScalar(
            new DoubleSumOfTwo(),
            new DoubleSumOfTwo(),
            0
        );

        call(
            () -> operator.applyAsDoubleArray(null, null),
            contextBuilder()
                .add("First Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Second Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Left Value", null)
                .add("Right Value", null)
                .build(),
            r -> "Call resulted in an error"
        );

        assertNull(
            operator.applyAsDoubleArray(null, new double[0]),
            contextBuilder()
                .add("Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Left Value", null)
                .add("Right Value", "Empty Array")
                .build(),
            r -> "Expected the method to return null when one of the given arguments is null!"
        );
        assertNull(
            operator.applyAsDoubleArray(new double[0], null),
            contextBuilder()
                .add("Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Left Value", "Empty Array")
                .add("Right Value", null)
                .build(),
            r -> "Expected the method to return null when one of the given arguments is null!"
        );
        assertNull(
            operator.applyAsDoubleArray(null, null),
            contextBuilder()
                .add("Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Left Value", null)
                .add("Right Value", null)
                .build(),
            r ->"Expected the method to return null when both of the given arguments are null!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testResult(String op1, String op2, String init, String leftArray, String rightArray, String result) {
        System.out.println(Double.parseDouble(init));
        PairwiseDoubleArrayBinaryOperatorGivingScalar operator = new PairwiseDoubleArrayBinaryOperatorGivingScalar(
            convertStringToOperator(op1),
            convertStringToOperator(op2),
            Double.parseDouble(init)
        );

        double[] left = convertStringToDoubleArray(leftArray);
        double[] right = convertStringToDoubleArray(rightArray);
        double expected = Double.parseDouble(result);

        var context = contextBuilder()
            .add("First operator", op1)
            .add("Second operator", op2)
            .add("Init", Double.parseDouble(init))
            .add("Left Array", Arrays.toString(left))
            .add("Right Array", Arrays.toString(right))
            .build();

        PairwiseDoubleArrayBinaryOperatorGivingScalar finalOperator = operator;
        call(
            () -> finalOperator.applyAsDoubleArray(left, right),
            context,
            r -> "Call resulted in an error"
        );

        operator = new PairwiseDoubleArrayBinaryOperatorGivingScalar(
            convertStringToOperator(op1),
            convertStringToOperator(op2),
            Double.parseDouble(init)
        );
        double actual = operator.applyAsDoubleArray(left, right);

        assertEquals(
            expected,
            actual,
            context,
            r -> String.format(
                "Expected the method to return %f but it actually returned %f!",
                expected,
                actual
            )
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void checkRecursion(String op1, String op2, String init, String leftArray, String rightArray) {
        PairwiseDoubleArrayBinaryOperatorGivingScalar operator = new PairwiseDoubleArrayBinaryOperatorGivingScalar(
            convertStringToOperator(op1),
            convertStringToOperator(op2),
            Double.parseDouble(init)
        );

        PairwiseDoubleArrayBinaryOperatorGivingScalar operatorSpy = Mockito.spy(operator);

        var context = contextBuilder()
            .add("Operator", op1)
            .add("Operator", op2)
            .add("Init", Double.parseDouble(init))
            .add("Left Array", Arrays.toString(convertStringToDoubleArray(leftArray)))
            .add("Right Array", Arrays.toString(convertStringToDoubleArray(rightArray)))
            .build();

        call(
            () -> operatorSpy.applyAsDoubleArray(convertStringToDoubleArray(leftArray), convertStringToDoubleArray(rightArray)),
            context,
            r -> "Call resulted in an error"
        );

        call(
            () -> Mockito.verify(operatorSpy, Mockito.atMostOnce()).applyAsDoubleArray(Mockito.any(), Mockito.any()),
            context,
            r -> "No recursion allowed"
        );
    }

    @Test
    void checkLoops() {
        String[] classContentAsArrays = getClassContent().split("\n");
        int comment = 0;
        int numberForLoops = 0;
        for (String line : classContentAsArrays) {
            if (line.contains("//")) {
                continue;
            }
            if (line.contains("/*")) {
                comment++;
            }
            if (line.contains("*/")) {
                comment--;
            }
            if (comment == 0 && line.contains("for(") || line.contains("for (") || line.contains("while(") || line.contains("while (")) {
                numberForLoops++;
            }
        }
        int finalNumberForLoops = numberForLoops;
        assertEquals(
            1,
            numberForLoops,
            emptyContext(),
            r -> String.format("Expected only one loop but found %d", finalNumberForLoops)
        );
    }
}
