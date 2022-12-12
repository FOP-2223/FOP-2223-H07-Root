package h07;

import h07.doubleoperators.*;

import java.util.function.DoubleBinaryOperator;

/**
 * Class to build an operator with the buildOperator()-method
 */
public class DoubleBinaryOperatorFactory {

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in DoubleSumWithCoefficientsOp. If the object encapsulates
     * a PairOfDoubleCoefficients, the coefficients for the expression are taken
     * directly from said object.
     *
     * @param obj   The object specifying the operation.
     * @return      The lambda-expression.
     */
    private static DoubleBinaryOperator doubleSumWithCoefficientsOpAsLambda(Object obj) {
        // Check whether parameter is of type PairOfDoubleCoefficients or subtype
        if (obj instanceof PairOfDoubleCoefficients pair) {
            // Return standard lambda-expression
            return (double left, double right) -> {

                // Completely equivalent to implementation of applyAsDouble in DoubleSumWithCoefficientsOp
                // Take coefficients from the given object
                return left * pair.coeff1 +
                    right * pair.coeff2;
            };
        }

        // Else return null
        return null;
    }

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in EuclideanNorm.
     *
     * @return  The lambda-expression
     */
    private static DoubleBinaryOperator euclideanNormAsLambda() {
        // Return standard lambda-expression
        return (double left, double right) -> {

            // Completely equivalent to implementation of applyAsDouble in EuclideanNorm
            return Math.sqrt(left * left + right * right);
        };
    }

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in DoubleMaxOfTwo. If the object encapsulates a Boolean and if
     * said Boolean encapsulates true, a lambda-expression is returned. If it encapsulates
     * false a method reference is returned.
     *
     * @param obj   The object specifying the operation.
     * @return      The lambda-expression.
     */
    private static DoubleBinaryOperator doubleMaxOfTwoAsLambda(Object obj) {
        // Check whether parameter is of type Boolean
        if (obj instanceof Boolean) {

            // Check whether boolean value is true by casting object to Boolean
            if ((Boolean) obj) {

                // Return short lambda-expression but use "<"-operator for comparison
                // Completely equivalent to implementation of applyAsDouble in DoubleMaxOfTwo
                return (left, right) -> left < right ? right : left;
            } else { // boolean value in Boolean is false

                // Return method reference of max()-method from class java.lang.Math
                // Completely equivalent to implementation of applyAsDouble in DoubleMaxOfTwo
                return Math::max;
            }
        }

        // Else return null
        return null;
    }

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in ComposedDoubleBinaryOperator. If the object encapsulates
     * a TripleOfDoubleBinaryOperators, the operators for the expression are taken
     * directly from said object.
     *
     * @param obj   The object specifying the operation.
     * @return      The lambda-expression.
     */
    private static DoubleBinaryOperator composedDoubleBinaryOperatorAsLambda(Object obj) {
        // Check whether parameter is of type TripleOfDoubleBinaryOperators or subtype
        if (obj instanceof TripleOfDoubleBinaryOperators triple) {

            // Completely equivalent to implementation of applyAsDouble in ComposedDoubleBinaryOperator
            // Take operators from the given object
            return (left, right) -> (
                triple.operator3.applyAsDouble(
                triple.operator1.applyAsDouble(left, right),
                triple.operator2.applyAsDouble(left, right))
            );
        }

        // Else return null
        return null;
    }

    /**
     * Returns an operator depending on the given input parameters.
     *
     * @param str   The type of the operator.
     * @param obj   The (optional) features of the operator.
     * @param bool  The style of operator creation.
     * @return      The operator.
     */
    public static Object buildOperator(String str, Object obj, boolean bool) {
        if (bool) {
            return buildOperatorWithNew(str, obj);
        } else {
            return buildOperatorWithLambda(str, obj);
        }
    }

    /**
     * Returns an operator that is created solely by using new.
     *
     * @param str   The type of the operator.
     * @param obj   The (optional) features of the operator.
     * @return      The operator.
     */
    private static Object buildOperatorWithNew(String str, Object obj) {
        // switch-statement on first parameter
        switch (str) {

            // Case "Coeffs"
            case "Coeffs":

                // Check whether second parameter is of type PairOfDoubleCoefficients or subtype
                if (obj instanceof PairOfDoubleCoefficients pair) {

                    // Return an object of type DoubleSumWithCoefficientsOp
                    // Coefficients are taken from second parameter
                    return new DoubleSumWithCoefficientsOp(
                        pair.coeff1,
                        pair.coeff2
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

                // Check whether second parameter is of type TripleOfDoubleBinaryOperators or subtype
                if (obj instanceof TripleOfDoubleBinaryOperators triple) {

                    // Return an object of type ComposedDoubleBinaryOperator
                    // Operators are taken from second parameter
                    return new ComposedDoubleBinaryOperator(
                        triple.operator1,
                        triple.operator2,
                        triple.operator3
                    );
                }

            // Default case (necessary to avoid endless loops)
            default:

                // Return null
                return null;
        }
    }

    /**
     * Returns an operator that is created solely by using a lambda-expression.
     *
     * @param str   The type of the operator.
     * @param obj   The (optional) features of the operator.
     * @return      The operator.
     */
    private static Object buildOperatorWithLambda(String str, Object obj) {
        // switch-statement on first parameter
        return switch (str) {

            // Case "Coeffs"
            case "Coeffs" ->

                // Method call of doubleSumWithCoefficientsOpAsLambda
                doubleSumWithCoefficientsOpAsLambda(obj);

            // Case "Euclidean"
            case "Euclidean" ->

                // Method call of euclideanNormAsLambda
                euclideanNormAsLambda();

            // Case "Max"
            case "Max" ->

                // Method call of doubleMaxOfTwoAsLambda
                doubleMaxOfTwoAsLambda(obj);

            // Case "Composed"
            case "Composed" ->

                // Method call of composedDoubleBinaryOperatorAsLambda
                composedDoubleBinaryOperatorAsLambda(obj);

            // Default case (necessary to avoid endless loops)
            default ->

                // Return null
                null;
        };
    }
}
