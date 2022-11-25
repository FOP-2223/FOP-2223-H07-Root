package h07;

import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingArray;
import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingScalar;
import h07.arrayoperators.ReduceDoubleArray;
import h07.operators.DoubleProductOfTwo;
import h07.operators.DoubleSumOfTwo;
import h07.operators.DoubleSumSqrtsOfTwo;
import h07.doubleoperators.ComposedDoubleBinaryOperator;
import org.junit.jupiter.api.Test;
import org.tudalgo.algoutils.reflect.ClassTester;
import spoon.Launcher;
import spoon.reflect.declaration.CtMethod;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoublePredicate;

public class Utils {

    public static ClassTester<?> getClassTester(String packageName, String className) {
        return new ClassTester<>(packageName, className);
    }

    public static Launcher getSpoonLauncherForClass(String packageName, String className) {
        ClassTester<?> CT = new ClassTester<>(packageName, className);
        CT.resolve();
        CT.assureSpoonLauncherModelsBuild();
        return CT.getSpoon();
    }

    public static CtMethod<?> getCtMethod(Launcher launcher, String methodName) {
        return (CtMethod<?>) launcher.getModel().getElements(element -> element instanceof CtMethod<?> && ((CtMethod<?>) element).getSimpleName().equals(methodName)).get(0);
    }

    @Test
    void printTestCases() {
        printTestCasesH3_4();
    }

    private static final int NUMBER_TESTCASES = 1000;

    public static DoubleBinaryOperator convertStringToOperator(String op) {
        switch (op) {
            case "DoubleProductOfTwo":
                return new DoubleProductOfTwo();
            case "DoubleSumOfTwo":
                return new DoubleSumOfTwo();
            case "DoubleSumSqrtsOfTwo":
                return new DoubleSumSqrtsOfTwo();
            default:
                break;
        }
        return null;
    }

    public static DoublePredicate convertStringToPredicate(String pred) {
        switch (pred) {
            case "IsPositive":
                return e -> e > 0;
            case "DivisibleByTwo":
                return e -> e % 2 == 0;
            case "IsNotNaN":
                return e -> !Double.isNaN(e);
            default:
                break;
        }
        return null;
    }

    private static String getRandomPredicateAsString() {
        String[] predicates = {"IsPositive", "DivisibleByTwo", "IsNotNaN"};
        return predicates[ThreadLocalRandom.current().nextInt(predicates.length)];
    }

    private static String getRandomOperatorAsString() {
        String[] operators = {"DoubleProductOfTwo", "DoubleSumOfTwo", "DoubleSumSqrtsOfTwo"};
        return operators[ThreadLocalRandom.current().nextInt(operators.length)];
    }

    public static void printTestCasesH1_1() {
        for (int i = 0; i < NUMBER_TESTCASES; i++) {
            String predicateAsString = getRandomPredicateAsString();
            ReduceDoubleArray reducer = new ReduceDoubleArray(convertStringToPredicate(predicateAsString));
            double[] input = ThreadLocalRandom.current().doubles(
                ThreadLocalRandom.current().nextInt(1, 10),
                -1,
                1
            ).toArray();

            if (predicateAsString.equals("IsNotNaN"))
                for (int j = 0; j < ThreadLocalRandom.current().nextInt(input.length); j++) {
                    input[ThreadLocalRandom.current().nextInt(input.length)] = Double.NaN;
                }

            double[] result = reducer.applyAsDoubleArray(input);

            System.out.printf(
                "%s; %s; %s",
                predicateAsString,
                Arrays.toString(input),
                Arrays.toString(result)
            );
            System.out.println();
        }
    }

    public static void printTestCasesH1_2() {
        for (int i = 0; i < NUMBER_TESTCASES; i++) {
            String operatorAsString = getRandomOperatorAsString();
            PairwiseDoubleArrayBinaryOperatorGivingArray reducer = new PairwiseDoubleArrayBinaryOperatorGivingArray(
                convertStringToOperator(operatorAsString)
            );
            double[] left = ThreadLocalRandom.current().doubles(
                ThreadLocalRandom.current().nextInt(1, 10),
                -1,
                1
            ).toArray();

            double[] right = ThreadLocalRandom.current().doubles(
                ThreadLocalRandom.current().nextInt(1, 10),
                -1,
                1
            ).toArray();

            double[] result = reducer.applyAsDoubleArray(left, right);

            System.out.printf(
                "%s; %s; %s; %s",
                operatorAsString,
                Arrays.toString(left),
                Arrays.toString(right),
                Arrays.toString(result)
            );
            System.out.println();
        }
    }

    public static void printTestCasesH1_3() {
        for (int i = 0; i < NUMBER_TESTCASES; i++) {
            String firstOperatorAsString = getRandomOperatorAsString();
            String secondOperatorAsString = getRandomOperatorAsString();
            double init = ThreadLocalRandom.current().nextDouble(-10, 10);
            init = 10.0;
            PairwiseDoubleArrayBinaryOperatorGivingScalar reducer = new PairwiseDoubleArrayBinaryOperatorGivingScalar(
                convertStringToOperator(firstOperatorAsString),
                convertStringToOperator(secondOperatorAsString),
                init
            );

            double[] left = ThreadLocalRandom.current().doubles(
                ThreadLocalRandom.current().nextInt(1, 10),
                -1,
                1
            ).toArray();

            double[] right = ThreadLocalRandom.current().doubles(
                ThreadLocalRandom.current().nextInt(1, 10),
                -1,
                1
            ).toArray();

            double result = reducer.applyAsDoubleArray(
                left,
                right
            );

            System.out.printf(
                "%s; %s; %s; %s; %s; %s",
                firstOperatorAsString,
                secondOperatorAsString,
                init,
                Arrays.toString(left),
                Arrays.toString(right),
                result
            );
            System.out.println();
        }
    }

    public static void printTestCasesH2_1() {
        for (int i = 0; i < NUMBER_TESTCASES; i++) {
            double coeff1 = ThreadLocalRandom.current().nextDouble(-1, 1);
            double coeff2 = ThreadLocalRandom.current().nextDouble(-1, 1);
            double left = ThreadLocalRandom.current().nextDouble(-1, 1);
            double right = ThreadLocalRandom.current().nextDouble(-1, 1);
            double result = coeff1 * left + coeff2 * right;

            System.out.printf(
                "%s; %s; %s; %s; %s",
                coeff1,
                coeff2,
                left,
                right,
                result
            );
            System.out.println();
        }
    }

    public static void printTestCasesH2_2() {
        for (int i = 0; i < NUMBER_TESTCASES; i++) {
            double left = ThreadLocalRandom.current().nextDouble(-1, 1);
            double right = ThreadLocalRandom.current().nextDouble(-1, 1);
            double result = Math.sqrt(Math.pow(left, 2) + Math.pow(right, 2));

            System.out.printf(
                "%s; %s; %s",
                left,
                right,
                result
            );
            System.out.println();
        }
    }

    public static void printTestCasesH2_3() {
        for (int i = 0; i < NUMBER_TESTCASES; i++) {
            double left = ThreadLocalRandom.current().nextDouble(-1, 1);
            double right = ThreadLocalRandom.current().nextDouble(-1, 1);
            double result = Math.max(left, right);

            System.out.printf(
                "%s; %s; %s",
                left,
                right,
                result
            );
            System.out.println();
        }
    }

    public static void printTestCasesH2_4() {
        for (int i = 0; i < NUMBER_TESTCASES; i++) {
            String op1 = getRandomOperatorAsString();
            String op2 = getRandomOperatorAsString();
            String op3 = getRandomOperatorAsString();

            DoubleBinaryOperator operator1 = convertStringToOperator(
                op1
            );
            DoubleBinaryOperator operator2 = convertStringToOperator(
                op2
            );
            DoubleBinaryOperator operator3 = convertStringToOperator(
                op3
            );

            ComposedDoubleBinaryOperator operator = new ComposedDoubleBinaryOperator(
                operator1,
                operator2,
                operator3
            );

            double left = ThreadLocalRandom.current().nextDouble(-1, 1);
            double right = ThreadLocalRandom.current().nextDouble(-1, 1);

            double result = operator.applyAsDouble(left, right);

            if (Double.isNaN(result)) {
                i--;
                continue;
            }

            System.out.printf(
                "%s; %s; %s; %s; %s; %s",
                op1,
                op2,
                op3,
                left,
                right,
                result
            );
            System.out.println();
        }
    }

    public void printTestCasesH3_1() {
        printTestCasesH2_1();
    }

    public void printTestCasesH3_2() {
        printTestCasesH2_2();
    }

    public void printTestCasesH3_3() {
        printTestCasesH2_3();
    }

    public void printTestCasesH3_4() {
        printTestCasesH2_4();
    }

}
