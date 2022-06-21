package h07.doubleoperators;

import java.util.function.DoubleBinaryOperator;

/**
 * Class implementing the interface DoubleBinaryOperator, such that
 * the overwritten method applyAsDouble returns the Euclidean norm
 * of the given parameters.
 */
public class EuclideanNorm implements DoubleBinaryOperator {

    /**
     * Returns the Euclidean norm of the given parameters.
     *
     * @param left      The first parameter.
     * @param right     The second parameter.
     * @return          Euclidean norm of parameters.
     */
    @Override
    public double applyAsDouble(double left, double right) {
        // Return the square root of the sum of the first parameter
        // squared with the second parameter squared
        return Math.sqrt(left * left + right * right);
    }

}
