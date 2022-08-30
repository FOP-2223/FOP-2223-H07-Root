package h07;

import h07.Operators.DoubleProductOfTwo;
import h07.Operators.DoubleSumOfTwo;
import h07.Operators.DoubleSumSqrtsOfTwo;
import h07.doubleoperators.ComposedDoubleBinaryOperator;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleBinaryOperator;

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

}
