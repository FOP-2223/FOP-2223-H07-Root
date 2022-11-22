package h07;

import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingArray;
import h07.arrayoperators.ReduceDoubleArray;
import h07.operators.DoubleProductOfTwo;
import h07.operators.DoubleSumOfTwo;
import h07.operators.DoubleSumSqrtsOfTwo;
import h07.doubleoperators.ComposedDoubleBinaryOperator;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoublePredicate;

public class Utils {

    private static final int NUMBER_TESTCASES = 10000;

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

    }

    public static void printTestCasesH1_3() {

    }

    public static void generateRandomInputsForDoubleSumWithCoefficientsOpTest() {
        double coeff1 = ThreadLocalRandom.current().nextDouble(-1, 1);
        double coeff2 = ThreadLocalRandom.current().nextDouble(-1, 1);
        double left = ThreadLocalRandom.current().nextDouble(-1, 1);
        double right = ThreadLocalRandom.current().nextDouble(-1, 1);
        double result = coeff1 * left + coeff2 * right;

        System.out.print(coeff1 + ", " + coeff2 + ", " + left + ", " + right + ", " + result);
        System.out.println();
    }

    public static void generateRandomInputsForEuclideanNormTest() {
        double left = ThreadLocalRandom.current().nextDouble(-1, 1);
        double right = ThreadLocalRandom.current().nextDouble(-1, 1);
        double result = Math.sqrt(Math.pow(left, 2) + Math.pow(right, 2));

        System.out.print(left + ", " + right + ", " + result);
        System.out.println();
    }

    public static void generateRandomInputsForDoubleMaxOfTwoTest() {
        double left = ThreadLocalRandom.current().nextDouble(-1, 1);
        double right = ThreadLocalRandom.current().nextDouble(-1, 1);
        double result = Math.max(left, right);

        System.out.print(left + ", " + right + ", " + result);
        System.out.println();
    }

    public void generateRandomInputsForComposedDoubleBinaryOperatorTest() {
        ArrayList<String> operators = new ArrayList<>();
        operators.add("DoubleProductOfTwo");
        operators.add("DoubleSumOfTwo");
        operators.add("DoubleSumSqrtsOfTwo");

        for (int i = 0; i < 1000; i++) {
            String op1 = operators.get(ThreadLocalRandom.current().nextInt(operators.size()));
            String op2 = operators.get(ThreadLocalRandom.current().nextInt(operators.size()));
            String op3 = operators.get(ThreadLocalRandom.current().nextInt(operators.size()));

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

            System.out.print(op1 + ", " + op2 + ", " + op3 + ", " + left + ", " + right + ", " + result);
            System.out.println();
        }
    }

    public static void generateRandomInputsForReduceDoubleArrayTest() {
        ArrayList<String> predicates = new ArrayList<>();
        predicates.add("IsPositive");
        predicates.add("DivisibleByTwo");
        predicates.add("IsNotNaN");

        for (int i = 0; i < 1000; i++) {
            String pred1 = predicates.get(ThreadLocalRandom.current().nextInt(predicates.size()));

            DoublePredicate predicate1 = convertStringToPredicate(
                pred1
            );

            ReduceDoubleArray operator = new ReduceDoubleArray(predicate1);

            double[] array = ThreadLocalRandom.current().doubles(10,-1, 1).toArray();

            if (pred1.equals("IsNotNaN"))
                for (int j = 0; j < ThreadLocalRandom.current().nextInt(array.length); j++) {
                    array[ThreadLocalRandom.current().nextInt(array.length)] = Double.NaN;
                }

            double[] result = operator.applyAsDoubleArray(array);

            System.out.print(pred1 + "; " + Arrays.toString(array) + "; " + Arrays.toString(result));
            System.out.println();
        }
    }

    public static void generateRandomInputsForPairwiseDoubleArrayBinaryOperatorGivingArrayTest() {
        ArrayList<String> operators = new ArrayList<>();
        operators.add("DoubleProductOfTwo");
        operators.add("DoubleSumOfTwo");
        operators.add("DoubleSumSqrtsOfTwo");

        for (int i = 0; i < 1000; i++) {
            String op1 = operators.get(ThreadLocalRandom.current().nextInt(operators.size()));

            DoubleBinaryOperator operator1 = convertStringToOperator(
                op1
            );

            PairwiseDoubleArrayBinaryOperatorGivingArray operator = new PairwiseDoubleArrayBinaryOperatorGivingArray(operator1);

            double[] left = ThreadLocalRandom.current().doubles(ThreadLocalRandom.current().nextInt(6), -1, 1).toArray();
            double[] right = ThreadLocalRandom.current().doubles(ThreadLocalRandom.current().nextInt(6), -1, 1).toArray();

            double[] result = operator.applyAsDoubleArray(left, right);

            System.out.print(op1 + "; " + Arrays.toString(left) + "; " + Arrays.toString(right) + "; " + Arrays.toString(result));
            System.out.println();
        }
    }

    public static void generateRandomInputsForIllegalStringsTest() {
        ArrayList<String> objects = new ArrayList<>();
        objects.add("Object");
        objects.add("null");
        objects.add("PairOfDoubleCoefficients");
        objects.add("TripleOfDoubleBinaryOperators");

        for (int i = 0; i < 1000; i++) {
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, StandardCharsets.UTF_8);

            System.out.print(generatedString + ", " + objects.get(ThreadLocalRandom.current().nextInt(objects.size())));
            System.out.println();
        }
    }

    public static void generateRandomObjectsForDoubleBinaryOperatorFactoryTest() {
        ArrayList<String> objects = new ArrayList<>();
        objects.add("Object");
        objects.add("BooleanTrue");
        objects.add("BooleanFalse");

        for (int i = 0; i < 1000; i++) {
            System.out.println(objects.get(ThreadLocalRandom.current().nextInt(objects.size())));
        }
    }

    public static void generateTwoRandomDoubles() {
        for (int i = 0; i < 1000; i++) {
            double left = ThreadLocalRandom.current().nextDouble(-1, 1);
            double right = ThreadLocalRandom.current().nextDouble(-1, 1);

            System.out.print(left + ", " + right);
            System.out.println();
        }
    }

    public static void generateThreeRandomOperators() {
        ArrayList<String> operators = new ArrayList<>();
        operators.add("DoubleProductOfTwo");
        operators.add("DoubleSumOfTwo");
        operators.add("DoubleSumSqrtsOfTwo");

        for (int i = 0; i < 1000; i++) {
            String op1 = operators.get(ThreadLocalRandom.current().nextInt(operators.size()));
            String op2 = operators.get(ThreadLocalRandom.current().nextInt(operators.size()));
            String op3 = operators.get(ThreadLocalRandom.current().nextInt(operators.size()));

            System.out.print(op1 + ", " + op2 + ", " + op3);
            System.out.println();
        }
    }

}
