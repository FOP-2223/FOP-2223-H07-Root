package h07.h3.h3_2;

import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingArray;
import h07.operators.DoubleSumOfTwo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;

import static org.junit.jupiter.api.Assertions.*;
import static h07.Utils.*;
import static h07.h3.H3Utils.convertStringToDoubleArray;

public class PairwiseDoubleArrayBinaryOperatorGivingArrayTest {

    private static final String PATH_TO_CSV = "/h3/PairwiseDoubleArrayBinaryOperatorGivingArrayTest.csv";

    @Test
    void testNull() {
        PairwiseDoubleArrayBinaryOperatorGivingArray operator = new PairwiseDoubleArrayBinaryOperatorGivingArray(new DoubleSumOfTwo());

        assertNull(
            operator.applyAsDoubleArray(null, new double[0]),
            "Expected \"applyAsDoubleArray\" to return null when one of the given arguments is null!"
        );
        assertNull(
            operator.applyAsDoubleArray(new double[0], null),
            "Expected \"applyAsDoubleArray\" to return null when one of the given arguments is null!"
        );
        assertNull(
            operator.applyAsDoubleArray(null, null),
            "Expected \"applyAsDoubleArray\" to return null when both of the given arguments are null!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testSizeOfResult(String op1, String leftArray, String rightArray, String result) {
        DoubleBinaryOperator operator1 = convertStringToOperator(op1);

        double[] left = convertStringToDoubleArray(leftArray);
        double[] right = convertStringToDoubleArray(rightArray);
        double[] expected = convertStringToDoubleArray(result);

        PairwiseDoubleArrayBinaryOperatorGivingArray operator = new PairwiseDoubleArrayBinaryOperatorGivingArray(operator1);

        double[] actual = operator.applyAsDoubleArray(left, right);

        int expectedLength = expected.length;
        int actualLength = actual.length;

        assertEquals(
            expectedLength,
            actualLength,
            "Expected resulting array to have length " + expectedLength + " but it actually has length " + actualLength
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testForModificationsOfInputArrays(String op1, String leftArray, String rightArray, String result) {
        DoubleBinaryOperator operator1 = convertStringToOperator(op1);

        double[] left = convertStringToDoubleArray(leftArray);
        double[] leftCopy = Arrays.copyOf(left, left.length);
        double[] right = convertStringToDoubleArray(rightArray);
        double[] rightCopy = Arrays.copyOf(right, right.length);

        PairwiseDoubleArrayBinaryOperatorGivingArray operator = new PairwiseDoubleArrayBinaryOperatorGivingArray(operator1);

        operator.applyAsDoubleArray(left, right);

        assertArrayEquals(
            leftCopy,
            left,
            "Did not expect the left input array to be modified but the array " + leftArray +
                " was modified to look like this after method call: " + Arrays.toString(left)
        );

        assertArrayEquals(
            rightCopy,
            right,
            "Did not expect the right input array to be modified but the array " + rightArray +
                " was modified to look like this after method call: " + Arrays.toString(right)
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testResult(String op1, String leftArray, String rightArray, String result) {
        DoubleBinaryOperator operator1 = convertStringToOperator(op1);

        double[] left = convertStringToDoubleArray(leftArray);
        double[] right = convertStringToDoubleArray(rightArray);
        double[] expected = convertStringToDoubleArray(result);

        PairwiseDoubleArrayBinaryOperatorGivingArray operator = new PairwiseDoubleArrayBinaryOperatorGivingArray(operator1);

        double[] actual = operator.applyAsDoubleArray(left, right);

        assertArrayEquals(
            expected,
            actual,
            "Expected method \"applyAsDoubleArray\" to return " + result +
                " but it returned " + Arrays.toString(actual) + " instead!"
        );
    }
}
