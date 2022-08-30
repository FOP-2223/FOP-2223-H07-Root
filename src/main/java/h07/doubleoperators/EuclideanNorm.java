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
     * @param x     The first parameter.
     * @param y     The second parameter.
     * @return          Euclidean norm of parameters.
     */
    @Override
    public double applyAsDouble(double x, double y) {
        // Return the square root of the sum of the first parameter
        // squared with the second parameter squared
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

}
