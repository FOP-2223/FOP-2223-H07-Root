package h07.h1.h1_1;

import h07.arrayoperators.ReduceDoubleArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Arrays;
import java.util.function.DoublePredicate;

import static h07.Utils.convertStringToPredicate;
import static h07.h1.H1Utils.convertStringToDoubleArray;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class ReduceDoubleArrayTest {

    private static final String PATH_TO_CSV = "/h1/h1_1/PrivateTestcases.csv";

    @Test
    public void testNullInput() {
        ReduceDoubleArray reducer = new ReduceDoubleArray(e -> e == 0);

        var context = contextBuilder()
            .add("Predicate", "e -> e == 0")
            .add("Input", null)
            .build();

        call(
            () -> reducer.applyAsDoubleArray(null),
            context,
            r -> "Call resulted in an error"
        );

        assertCallNull(
            () -> reducer.applyAsDoubleArray(null),
            context,
            r -> "Expected the method to return null for null as input!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    public void testModificationOfInputArray(String predicate, String inputArray) {
        var context = contextBuilder()
            .add("Predicate", predicate)
            .add("Input", inputArray)
            .build();
        DoublePredicate pred = convertStringToPredicate(predicate);
        double[] array = convertStringToDoubleArray(inputArray);
        double[] arrayCopy = Arrays.copyOf(array, array.length);

        ReduceDoubleArray reducer = new ReduceDoubleArray(pred);

        call(
            () -> reducer.applyAsDoubleArray(array),
            context,
            r -> "Call resulted in an error"
        );

        for (int i = 0; i < array.length; i++) {
            assertEquals(
                arrayCopy[i],
                array[i],
                context,
                r -> String.format(
                    "Did not expect the input array to be modified but the input-array %s was modified in the method call to look like %s after the call",
                    Arrays.toString(arrayCopy),
                    Arrays.toString(array)
                )
            );
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    public void testLengthOfResult(String predicate, String inputArray, String outputArray) {
        var context = contextBuilder()
            .add("Predicate", predicate)
            .add("Input", inputArray)
            .build();
        DoublePredicate pred = convertStringToPredicate(predicate);
        double[] array = convertStringToDoubleArray(inputArray);
        double[] expected = convertStringToDoubleArray(outputArray);

        ReduceDoubleArray reducer = new ReduceDoubleArray(pred);

        call(
            () -> reducer.applyAsDoubleArray(array),
            context,
            r -> "Call resulted in an error"
        );

        double[] actual = reducer.applyAsDoubleArray(array);

        int expectedLength = expected.length;
        int actualLength = actual.length;

        assertEquals(
            expectedLength,
            actualLength,
            context,
            r -> String.format(
                "Expected the output-array %s to have length %d after method call but it actually has length %d!",
                Arrays.toString(actual),
                expectedLength,
                actualLength
            )
        );
    }

    // Check whether correct values are chosen (with different predicates)
    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1, delimiter = ';')
    public void testResult(String predicate, String inputArray, String outputArray) {
        var context = contextBuilder()
            .add("Predicate", predicate)
            .add("Input", inputArray)
            .build();
        DoublePredicate pred = convertStringToPredicate(predicate);
        double[] array = convertStringToDoubleArray(inputArray);
        double[] expected = convertStringToDoubleArray(outputArray);

        ReduceDoubleArray reducer = new ReduceDoubleArray(pred);

        double[] actual = callObject(() -> reducer.applyAsDoubleArray(array), context,
            r -> "Call resulted in an error");

        for (int i = 0; i < expected.length; i++) {
            int finalI = i;
            assertEquals(expected[i], actual[i], context,
                r -> String.format(
                    "The resulting array %s differs from the expected outcome %s at index %d!",
                    Arrays.toString(actual),
                    Arrays.toString(expected),
                    finalI
                )
            );
        }
    }

}
