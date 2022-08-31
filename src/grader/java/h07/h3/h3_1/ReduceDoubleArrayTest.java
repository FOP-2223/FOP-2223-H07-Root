package h07.h3.h3_1;

import h07.arrayoperators.ReduceDoubleArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.function.DoublePredicate;

import static h07.Utils.*;
import static h07.h3.H3Utils.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReduceDoubleArrayTest {

    private static final String PATH_TO_CSV = "/h3/ReduceDoubleArrayTest.csv";

    @Test
    void testForNullInput() {
        ReduceDoubleArray reducer = new ReduceDoubleArray(e -> e == 0);

        assertNull(
            reducer.applyAsDoubleArray(null),
            "Expected method \"applyAsDoubleArray\" to return null for null as input!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testForModificationOfInputArray(String predicate, String inputArray, String outputArray) {
        DoublePredicate pred = convertStringToPredicate(predicate);
        double[] array = convertStringToDoubleArray(inputArray);
        double[] arrayCopy = Arrays.copyOf(array, array.length);

        ReduceDoubleArray reducer = new ReduceDoubleArray(pred);

        reducer.applyAsDoubleArray(array);

        assertArrayEquals(
            arrayCopy,
            array,
            "Did not expect input array to be modified but array " + inputArray +
                " was modified to look like this after method call: " + Arrays.toString(array)
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testResizingOfArray(String predicate, String inputArray, String outputArray) {
        DoublePredicate pred = convertStringToPredicate(predicate);
        double[] array = convertStringToDoubleArray(inputArray);
        double[] expected = convertStringToDoubleArray(outputArray);

        ReduceDoubleArray reducer = new ReduceDoubleArray(pred);

        double[] actual = reducer.applyAsDoubleArray(array);

        int expectedLength = expected.length;
        int actualLength = actual.length;

        assertEquals(
            expectedLength,
            actualLength,
            "Expected array " + inputArray + " to have length " + expectedLength +
                " after call of \"applyAsDoubleArray\" but it actually has length " + actualLength + "!"
        );
    }

    // Check whether correct values are chosen (with different predicates)
    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    void testApplyAsDoubleArray(String predicate, String inputArray, String outputArray) {
        DoublePredicate pred = convertStringToPredicate(predicate);
        double[] array = convertStringToDoubleArray(inputArray);
        double[] expected = convertStringToDoubleArray(outputArray);

        ReduceDoubleArray reducer = new ReduceDoubleArray(pred);

        double[] actual = reducer.applyAsDoubleArray(array);

        assertArrayEquals(
            expected,
            actual,
            "Expected array " + inputArray + " to look like " + outputArray +
                " after call of method \"applyAsDoubleArray\" but it actually looks like: " + Arrays.toString(actual)
        );
    }

}
