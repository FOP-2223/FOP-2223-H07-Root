package h07.h2_old.h2_1;

import h07.doubleoperators.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.function.DoubleBinaryOperator;

import static h07.Utils.*;
import static h07.h2_old.H2Utils.*;
import static h07.doubleoperators.DoubleBinaryOperatorFactory.buildOperator;
import static org.junit.jupiter.api.Assertions.*;

public class DoubleBinaryOperatorFactoryFalseTest {

    private static final String PATH_TO_CSV = "/h2/ObjectsForDoubleBinaryOperatorFactoryTest.csv";
    private static final String PATH_TO_ILLEGAL_STRINGS = "/h2/IllegalStringsForDoubleBinaryOperatorFactoryTest.csv";
    private static final String PATH_TO_DOUBLE_PAIRS = "/h2/DoublesForDoubleBinaryOperatorFactoryTest.csv";
    private static final String PATH_TO_OPERATORS = "/h1/ComposedDoubleBinaryOperatorTest.csv";

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
            "Expected no Exceptions to be thrown! Maybe a check for null is missing!"
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

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_DOUBLE_PAIRS)
    void testCoeffsWithPairOfDoubleCoefficients(double expectedCoeff1, double expectedCoeff2) {
        PairOfDoubleCoefficients pair = new PairOfDoubleCoefficients();
        pair.coeff1 = expectedCoeff1;
        pair.coeff2 = expectedCoeff2;

        Object operator = buildOperator(COEFFS, pair, LAMBDA);

        assertTrue(
            operator instanceof DoubleSumWithCoefficientsOp,
            "Expected \"buildOperator\" to return an object of type \"DoubleSumWithCoefficientsOp\" when given " +
                "type \"PairOfDoubleCoefficients\" as the second parameter!"
        );

        double actualCoeff1 = ((DoubleSumWithCoefficientsOp) operator).applyAsDouble(1, 1) - expectedCoeff2;
        double actualCoeff2 = ((DoubleSumWithCoefficientsOp) operator).applyAsDouble(1, 1) - expectedCoeff1;

        assertEquals(
            expectedCoeff1,
            actualCoeff1,
            "Expected \"buildOperator\" to return an object of type \"DoubleSumWithCoefficientsOp\" with coeff1 = " + expectedCoeff1 +
                "but it's coeff1 is actually " + actualCoeff1 + "!"
        );

        assertEquals(
            expectedCoeff2,
            actualCoeff2,
            "Expected \"buildOperator\" to return an object of type \"DoubleSumWithCoefficientsOp\" with coeff2 = " + expectedCoeff2 +
                "but it's coeff2 is actually " + actualCoeff2 + "!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV)
    void testCoeffsWithOtherTypes(String object) {
        Object obj = convertStringToObject(object);

        assertNull(
            buildOperator(COEFFS, obj, LAMBDA),
            "Expected \"buildOperator\" to return null when second parameter is not of type \"PairOfDoubleCoefficients\"!"
        );
    }

    @Test
    void testCoeffsWithNull() {
        assertNull(
            buildOperator(COEFFS, null, LAMBDA),
            "Expected \"buildOperator\" to return null for \"Coeffs\" when second parameter is null!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV)
    void testEuclideanWithOtherTypes(String object) {
        Object obj = convertStringToObject(object);

        Object operator = buildOperator(EUCLIDEAN, obj, LAMBDA);

        assertNotNull(
            operator,
            "Expected \"buildOperator\" to return an object of type \"EuclideanNorm\", even when given random objects as a second parameter!"
        );

        assertTrue(
            operator instanceof EuclideanNorm,
            "Expected \"buildOperator\" to return an object of type \"EuclideanNorm\", even when given random objects as a second parameter!"
        );
    }

    @Test
    void testEuclideanWithNull() {
        Object operator = buildOperator(EUCLIDEAN, null, LAMBDA);

        assertNotNull(
            operator,
            "Expected \"buildOperator\" to return an object of type \"EuclideanNorm\", even when given null as a second parameter!"
        );

        assertTrue(
            operator instanceof EuclideanNorm,
            "Expected \"buildOperator\" to return an object of type \"EuclideanNorm\", even when given null as a second parameter!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV)
    void testMaxWithOtherTypes(String object) {
        Object obj = convertStringToObject(object);

        Object operator = buildOperator(MAX, obj, LAMBDA);

        assertNotNull(
            operator,
            "Expected \"buildOperator\" to return an object of type \"DoubleMaxOfTwo\", even when given random objects as a second parameter!"
        );

        assertTrue(
            operator instanceof DoubleMaxOfTwo,
            "Expected \"buildOperator\" to return an object of type \"DoubleMaxOfTwo\", even when given random objects as a second parameter!"
        );
    }

    @Test
    void testMaxWithNull() {
        Object operator = buildOperator(MAX, null, LAMBDA);

        assertNotNull(
            operator,
            "Expected \"buildOperator\" to return an object of type \"DoubleMaxOfTwo\", even when given null as a second parameter!"
        );

        assertTrue(
            operator instanceof DoubleMaxOfTwo,
            "Expected \"buildOperator\" to return an object of type \"DoubleMaxOfTwo\", even when given null as a second parameter!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_OPERATORS, numLinesToSkip = 1)
    void testComposedWithTripleOfDoubleBinaryOperators(String op1, String op2, String op3, double left, double right, double expected) {
        DoubleBinaryOperator operator1 = convertStringToOperator(op1);
        DoubleBinaryOperator operator2 = convertStringToOperator(op2);
        DoubleBinaryOperator operator3 = convertStringToOperator(op3);

        TripleOfDoubleBinaryOperators triple = new TripleOfDoubleBinaryOperators();

        triple.operator1 = operator1;
        triple.operator2 = operator2;
        triple.operator3 = operator3;

        Object operator = buildOperator(COMPOSED, triple, LAMBDA);

        assertTrue(
            operator instanceof ComposedDoubleBinaryOperator,
            "Expected \"buildOperator\" to return an object of type \"ComposedDoubleBinaryOperator\" when given " +
                "type \"TripleOfDoubleBinaryOperators\" as the second parameter!"
        );

        double actual = ((ComposedDoubleBinaryOperator) operator).applyAsDouble(left, right);

        assertEquals(
            expected,
            actual,
            "\"buildOperator\" did not correcte assign the operators to the returned object of type \"ComposedDoubleBinaryOperator\"!" +
                "Expected \"applyAsDouble\" of the object to return " + expected + " but it returned " + actual + " instead!"
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV)
    void testComposedWithOtherType(String obj) {
        Object object = convertStringToObject(obj);

        assertNull(
            buildOperator(COMPOSED, object, LAMBDA),
            "Expected \"buildOperator\" to return null for \"Composed\" when second parameter is not of type \"TripleOfDoubleBinaryOperator\"!"
        );
    }

    @Test
    void testComposedWithNull() {
        assertNull(
            buildOperator(COMPOSED, null, LAMBDA),
            "Expected \"buildOperator\" to return null for \"Composed\" when second parameter is null!"
        );
    }
}
