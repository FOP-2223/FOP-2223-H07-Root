package h07.h3.h3_2;

import h07.doubleoperators.DoubleBinaryOperatorFactory;
import h07.h4.H4Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.function.DoubleBinaryOperator;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

public class EuclideanNormAsLambdaTest {

    private static final String PATH_TO_CSV = "/h1/EuclideanNormTest.csv";

    @Test
    void test() {
        String[] content = H4Utils.getFactoryContent().split(";");
        for (String str :
            content) {
            if (str.contains("doubleSumWithCoefficientsOpAsLambda") || str.contains("euclideanNormAsLambda") || str.contains("doubleMaxOfTwoAsLambda") || str.contains("composedDoubleBinaryOperatorAsLambda"))
                System.out.println(str);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV, numLinesToSkip = 1)
    void calculationsTest(double x, double y, double expected) {
        var context = contextBuilder()
            .add("x", x)
            .add("y", y)
            .build();
        double actual = ((DoubleBinaryOperator) DoubleBinaryOperatorFactory.buildOperator("Euclidean", null, false)).applyAsDouble(x, y);

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
