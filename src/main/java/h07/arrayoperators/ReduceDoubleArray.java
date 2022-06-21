package h07.arrayoperators;

import java.util.function.DoublePredicate;

/**
 * Class implementing the interface DoubleArrayUnaryOperatorGivingArray,
 * such that the overwritten method applyAsDoubleArray returns an array
 * only containing elements of the given array for which the predicate
 * of the class returns true.
 */
public class ReduceDoubleArray implements DoubleArrayUnaryOperatorGivingArray {

    /**
     * The predicate.
     */
    private final DoublePredicate predicate;

    /**
     * Constructor initializes the predicate for reduction.
     *
     * @param predicate     The predicate.
     */
    public ReduceDoubleArray(DoublePredicate predicate) {
        // Assign given parameter to predicate
        this.predicate = predicate;
    }

    /**
     * Returns an array containing all the elements of the given array
     * for which the predicate returns true.
     *
     * @param array     The array.
     * @return          Reduced array.
     */
    @Override
    public double[] applyAsDoubleArray(double[] array) {
        // Check whether given array is null
        if (array == null) {

            // Return null
            return null;
        }

        // Initialize size of new array
        int n = 0;

        // Loop through given array
        for (double d : array) {

            // Check whether predicate.test returns true
            if (predicate.test(d)) {

                // Increase size for reduced array
                n++;
            }
        }

        // Initialize reduced array
        double[] result = new double[n];

        // Reset size to use as index
        n = 0;

        // Loop through array again
        for (double d : array) {

            // Check whether predicate.test returns true
            if (predicate.test(d)) {

                // Assign value in original array to reduced array and increase index
                result[n++] = d;
            }
        }

        // Return the reduced array
        return result;
    }
}
