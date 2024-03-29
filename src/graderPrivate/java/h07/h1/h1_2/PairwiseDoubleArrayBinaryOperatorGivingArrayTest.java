package h07.h1.h1_2;

import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingArray;
import h07.operators.DoubleSumOfTwo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;

import static h07.Utils.assertArrayAlmostEquals;
import static h07.Utils.convertStringToOperator;
import static h07.h1.H1Utils.convertStringToDoubleArray;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class PairwiseDoubleArrayBinaryOperatorGivingArrayTest {

    private static final String PATH_TO_CSV = "/h1/h1_2/PrivateTestcases.csv";

    @Test
    public void testNullInput() {
        PairwiseDoubleArrayBinaryOperatorGivingArray operator = new PairwiseDoubleArrayBinaryOperatorGivingArray(new DoubleSumOfTwo());

        call(
            () -> operator.applyAsDoubleArray(null, null),
            contextBuilder()
                .add("Operator", "DoubleSumOfTwo (see /src/graderPrivate/java/h07/operators)")
                .add("Left Value", null)
                .add("Right Value", null)
                .build(),
            r -> "Call resulted in an error"
        );

        assertCallNull(
            () -> operator.applyAsDoubleArray(null, new double[0]),
            contextBuilder()
                .add("Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Left Value", null)
                .add("Right Value", "Empty Array")
                .build(),
            r -> "Expected the method to return null when one of the given arguments is null!"
        );
        assertCallNull(
            () -> operator.applyAsDoubleArray(new double[0], null),
            contextBuilder()
                .add("Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Left Value", "Empty Array")
                .add("Right Value", null)
                .build(),
            r -> "Expected the method to return null when one of the given arguments is null!"
        );
        assertCallNull(
            () -> operator.applyAsDoubleArray(null, null),
            contextBuilder()
                .add("Operator", "DoubleSumOfTwo (see /src/graderPublic/java/h07/operators)")
                .add("Left Value", null)
                .add("Right Value", null)
                .build(),
            r -> "Expected the method to return null when both of the given arguments are null!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    public void testLengthOfResult(String op1, String leftArray, String rightArray, String result) {
        DoubleBinaryOperator operator1 = convertStringToOperator(op1);

        double[] left = convertStringToDoubleArray(leftArray);
        double[] right = convertStringToDoubleArray(rightArray);
        double[] expected = convertStringToDoubleArray(result);

        var context = contextBuilder()
            .add("Operator", op1)
            .add("Left Array", Arrays.toString(left))
            .add("Right Array", Arrays.toString(right))
            .build();

        PairwiseDoubleArrayBinaryOperatorGivingArray operator = new PairwiseDoubleArrayBinaryOperatorGivingArray(operator1);

        double[] actual = callObject(
            () -> operator.applyAsDoubleArray(left, right),
            context,
            r -> "Call resulted in an error"
        );

        int expectedLength = expected.length;
        int actualLength = actual.length;

        assertEquals(
            expectedLength,
            actualLength,
            context,
            r -> String.format(
                "Expected resulting array to have length %d but it actually has length %d!",
                expectedLength,
                actualLength
            )
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testModificationOfInputArrays(String op1, String leftArray, String rightArray, String result) {
        DoubleBinaryOperator operator1 = convertStringToOperator(op1);

        double[] left = convertStringToDoubleArray(leftArray);
        double[] leftCopy = Arrays.copyOf(left, left.length);
        double[] right = convertStringToDoubleArray(rightArray);
        double[] rightCopy = Arrays.copyOf(right, right.length);

        var context = contextBuilder()
            .add("Operator", op1)
            .add("Left Array", Arrays.toString(left))
            .add("Right Array", Arrays.toString(right))
            .build();

        PairwiseDoubleArrayBinaryOperatorGivingArray operator = new PairwiseDoubleArrayBinaryOperatorGivingArray(operator1);

        call(
            () -> operator.applyAsDoubleArray(left, right),
            context,
            r -> "Call resulted in an error"
        );

        for (int i = 0; i < left.length; i++) {
            assertEquals(
                leftCopy[i],
                left[i],
                context,
                r -> String.format(
                    "Did not expect any input array to be modified but the left input was modified to look like %s after the call",
                    Arrays.toString(left)
                )
            );
        }

        for (int i = 0; i < right.length; i++) {
            assertEquals(
                rightCopy[i],
                right[i],
                context,
                r -> String.format(
                    "Did not expect any input array to be modified but the right input was modified to look like %s after the call",
                    Arrays.toString(right)
                )
            );
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testResult(String op1, String leftArray, String rightArray, String result) {
        DoubleBinaryOperator operator1 = convertStringToOperator(op1);

        double[] left = convertStringToDoubleArray(leftArray);
        double[] right = convertStringToDoubleArray(rightArray);
        double[] expected = convertStringToDoubleArray(result);

        var context = contextBuilder()
            .add("Operator", op1)
            .add("Left Array", Arrays.toString(left))
            .add("Right Array", Arrays.toString(right))
            .build();

        PairwiseDoubleArrayBinaryOperatorGivingArray operator = new PairwiseDoubleArrayBinaryOperatorGivingArray(operator1);

        double[] actual = callObject(
            () -> operator.applyAsDoubleArray(left, right),
            context,
            r -> "Call resulted in an error"
        );

        assertArrayAlmostEquals(
            expected,
            actual,
            context
        );

    }
}
