package h07.arrayoperators;

import java.util.function.DoubleBinaryOperator;

/**
 * Class implementing the interface DoubleArrayBinaryOperatorGivingArray,
 * such that the overwritten method applyAsDoubleArray returns an array
 * only containing as many elements as the smaller array of the given
 * ones.
 * Applies the operator to the elements in the old arrays and fills the
 * new array with the results.
 */
public class PairwiseDoubleArrayBinaryOperatorGivingArray implements DoubleArrayBinaryOperatorGivingArray {

    /**
     * The operator
     */
    private final DoubleBinaryOperator operator;

    /**
     * Constructor initializes the operator.
     *
     * @param operator      The operator.
     */
    public PairwiseDoubleArrayBinaryOperatorGivingArray(DoubleBinaryOperator operator) {
        // Assign parameter to operator
        this.operator = operator;
    }

    /**
     * Returns an array containing as many elements as the smaller array of the given ones.
     * Each element is the result of the application of the operator on the elements of
     * the given arrays.
     *
     * @param left      First parameter.
     * @param right     Second Parameter.
     * @return          The result.
     */
    @Override
    public double[] applyAsDoubleArray(double[] left, double[] right) {
        // Check whether one of the arrays is null
        if (left == null || right == null) {

            // Return null
            return null;
        }

        // Initialize resulting array with length of the smaller given array
        double[] result = left.length < right.length ? new double[left.length] : new double[right.length];

        // Loop through new array
        for (int i = 0; i < result.length; i++) {

            // Assign each element as the result of the operator on the corresponding elements in the
            // given arrays
            result[i] = operator.applyAsDouble(left[i], right[i]);
        }

        // Return result
        return result;
    }
}
