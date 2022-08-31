package h07;

import h07.arrayoperators.ReduceDoubleArray;
import h07.operators.DoubleProductOfTwo;
import h07.operators.DoubleSumOfTwo;
import h07.operators.DoubleSumSqrtsOfTwo;
import h07.doubleoperators.ComposedDoubleBinaryOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoublePredicate;

public class Utils {

    public static String getGeneralInfo(String information) {
        return "General Information:\n" + information +
            "\nTest failed because:\n";
    }

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

}
