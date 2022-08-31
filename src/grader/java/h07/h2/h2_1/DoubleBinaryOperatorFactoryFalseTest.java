package h07.h2.h2_1;

import h07.doubleoperators.DoubleBinaryOperatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static h07.Utils.*;
import static h07.h2.H2Utils.*;
import static h07.doubleoperators.DoubleBinaryOperatorFactory.buildOperator;
import static org.junit.jupiter.api.Assertions.*;

public class DoubleBinaryOperatorFactoryFalseTest {

    private static final String PATH_TO_CSV = "/h2/DoubleBinaryOperatorFactoryFalseTest.csv";
    private static final String PATH_TO_ILLEGAL_STRINGS = "/h2/IllegalStringsForDoubleBinaryOperatorFactoryTest.csv";

    private static final String COEFFS = "Coeffs";
    private static final String EUCLIDEAN = "Euclidean";
    private static final String MAX = "Max";
    private static final String COMPOSED = "Composed";

    private static final boolean LAMBDA = false;

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_ILLEGAL_STRINGS, numLinesToSkip = 1)
    void checkForExceptions(String illegalString, String object) {
        Object obj = convertStringToObject(object);

        assertDoesNotThrow(
            () -> buildOperator(illegalString, obj, LAMBDA),
            "Expected no Exceptions to be thrown! Maybe check for null is missing!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_ILLEGAL_STRINGS, numLinesToSkip = 1)
    void testIllegalStrings(String illegalString, String object) {
        Object obj = convertStringToObject(object);

        assertNull(
            buildOperator(illegalString, obj, LAMBDA),
            "Expected \"buildOperator\" to return null when given a string other than \"Coeffs\", \"Euclidean\", \"Max\" or \"Composed\" " +
                "but for string " + illegalString + " it did not return null!"
        );
    }

    // Test "Coeffs"
    // Test with PairOfDoubleCoefficients
    // Test with other type
    // Test with null

    // Test "Euclidean"
    // Test with other type as second parameter
    // Test with null as second parameter

    // Test "Max"
    // Test with other type as second parameter
    // Test with null as second parameter

    // Test "Composed"
    // Test with TripleOfBinaryOperators
    // Test with other type
    // Test with null

    @Test
    void generate() {
        generateRandomInputsForIllegalStringsTest();
    }
}
