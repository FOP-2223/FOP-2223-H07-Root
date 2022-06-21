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
public class PairwiseDoubleArrayBinaryOperatorGivingScalar implements DoubleArrayBinaryOperatorGivingScalar {

    /**
     * First operator, "Komponentenverknüpfung" (join-fct).
     */
    private final DoubleBinaryOperator operator1;

    /**
     * Second operator, "Faltungsoperation" (fold-fct).
     */
    private final DoubleBinaryOperator operator2;

    /**
     * Initial value (init).
     */
    private double init;

    /**
     * Constructor initializes the operators ("Komponentenverknüpfung" and "Faltungsoperation")
     * and the initial value (init).
     *
     * @param operator1     First operator, "Komponentenverknüpfung" (join-fct).
     * @param operator2     Second operator, "Faltungsoperation" (fold-fct).
     * @param d             Initial value (init).
     */
    public PairwiseDoubleArrayBinaryOperatorGivingScalar(DoubleBinaryOperator operator1, DoubleBinaryOperator operator2, double d) {
        // Assign first parameter to first operator
        this.operator1 = operator1;

        // Assign second parameter to second operator
        this.operator2 = operator2;

        // Assign third parameter to initial value
        this.init = d;
    }

    /**
     * Returns a scalar that is the result of the application of the two operators
     * ("Komponentenverknüpfung" and "Faltungsoperation") on the two given arrays
     * according to the following racket code:
     *
     * ( define (apply lst1 lst2 join-fct fold-fct init )
     *      ( cond
     *          [ ( or ( empty? lst1 ) ( empty? lst2 ) ) init ]
     *          [ else ( fold-fct
     *                  ( join-fct ( first lst1 ) ( first lst2 ) )
     *                  ( apply ( rest lst1 ) ( rest lst2 ) join-fct fold-fct init ) ] ) )
     *
     * @param left      First parameter.
     * @param right     Second parameter.
     * @return          The scalar.
     */
    @Override
    public double applyAsDoubleArray(double[] left, double[] right) {
        // Determine minimal length of the two arrays
        // Can be done with a java.lang.Math.min() call:
        // int min = Math.min(left.length, right.length);
        int min = left.length < right.length ? left.length : right.length;

        // Loop through both arrays from last index
        for (int i = min - 1; i >= 0; i--) {

            // Apply the first operator ("Komponentenverknüpfung") on the corresponding
            // elements in the arrays and save result
            double intermediate = operator1.applyAsDouble(left[i], right[i]);

            // Apply the second operator ("Faltungsoperation") on the current value of
            // init and the intermediate value to update init
            init = operator2.applyAsDouble(intermediate, init);
        }

        // Return init
        return init;
    }
}
