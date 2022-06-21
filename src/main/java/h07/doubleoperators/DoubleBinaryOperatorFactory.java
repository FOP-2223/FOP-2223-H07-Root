package h07.doubleoperators;

import java.util.function.DoubleBinaryOperator;

/**
 * Class to build an operator with the buildOperator()-method
 */
public class DoubleBinaryOperatorFactory {

    /**
     * Returns an operator depending on the given input parameters.
     *
     * @param str       Sets the type of the operator.
     * @param obj       Encapsulates features of operator.
     * @param bool      Chooses style of operator creation.
     * @return          The operator.
     */
    public static Object buildOperator(String str, Object obj, boolean bool) {
        // If the third parameter encapsulates false:
        if (!bool) {

            // switch-statement on given String in first parameter
            switch (str) {

                // Case "Coeffs"
                case "Coeffs":

                    // Check whether second parameter is of type PairOfDoubleCoefficients
                    if (obj instanceof PairOfDoubleCoefficients) {

                        // Return an object of type DoubleSumWithCoefficientsOp initialized
                        // with the coefficients encapsulated in the second parameter
                        return new DoubleSumWithCoefficientsOp(
                            ((PairOfDoubleCoefficients) obj).coeff1,
                            ((PairOfDoubleCoefficients) obj).coeff2
                        );
                    }

                // Case "Euclidean"
                case "Euclidean":

                    // Return an object of type EuclideanNorm
                    return new EuclideanNorm();

                // Case "Max"
                case "Max":

                    // Return an object of type DoubleMaxOfTwo
                    return new DoubleMaxOfTwo();

                // Case "Composed"
                case "Composed":

                    // Check whether second parameter is of type TripleOfDoubleBinaryOperators
                    if (obj instanceof TripleOfDoubleBinaryOperators) {

                        // Return an object of type ComposedDoubleBinaryOperator initialized
                        // with the operators encapsulated in the second parameter
                        return new ComposedDoubleBinaryOperator(
                            ((TripleOfDoubleBinaryOperators) obj).operator1,
                            ((TripleOfDoubleBinaryOperators) obj).operator2,
                            ((TripleOfDoubleBinaryOperators) obj).operator3
                        );
                    }

                // Default case (necessary to avoid endless loops)
                default:

                    // Return null
                    return null;
            }
        } else { // Third parameter encapsulates true

            // Initialize a DoubleBinaryOperator
            DoubleBinaryOperator operator = null;

            // Case "Coeffs"
            // Check whether second parameter is of type PairOfDoubleCoefficients
            if (str.equals("Coeffs") && obj instanceof PairOfDoubleCoefficients) {

                // Assign regular lambda expression representing class DoubleSumWithCoefficientsOp
                // to initialized operator
                operator = (double left, double right) -> {
                    return left * ((PairOfDoubleCoefficients) obj).coeff1 +
                        right * ((PairOfDoubleCoefficients) obj).coeff2;
                };
            }

            // Case "Euclidean"
            if (str.equals("Euclidean")) {

                // Assign regular lambda expression representing class EuclideanNorm to initialized
                // operator
                operator = (double left, double right) -> {
                    return Math.sqrt(left * left + right * right);
                };
            }

            // Case "Max"
            if (str.equals("Max")) {

                // Check whether second parameter encapsulates a boolean value
                if ((obj instanceof Boolean)) {

                    // Check whether second parameter is true
                    if ((Boolean) obj) {

                        // Assign short lambda expression representing class DoubleMaxOfTwo with a
                        // conditional expression to initialized operator
                        operator = (left, right) -> left < right ? right : left;

                    } else { // second parameter is false

                        // Assign short lambda expression representing class DoubleMaxOfTwo with a
                        // call of the max()-method from class java.lang.Math to initialized
                        // operator
                        operator = Math::max;
                    }
                }
            }

            // Case "Composed"
            // Check whether second parameter is of type TripleOfDoubleBinaryOperators
            if (str.equals("Composed") && obj instanceof TripleOfDoubleBinaryOperators) {

                // Assign short lambda expression representing class ComposedDoubleBinaryOperator
                // to initialized operator
                operator = (left, right) ->
                    ((TripleOfDoubleBinaryOperators) obj).operator3.applyAsDouble(
                        ((TripleOfDoubleBinaryOperators) obj).operator1.applyAsDouble(left, right),
                        ((TripleOfDoubleBinaryOperators) obj).operator2.applyAsDouble(left, right)
                    );
            }

            // Return initialized operator
            return operator;
        }
    }

}
