package h07.arrayoperators;

import h07.arrayoperators.DoubleArrayUnaryOperatorGivingArray;

import java.util.function.DoublePredicate;

public class ReduceDoubleArray implements DoubleArrayUnaryOperatorGivingArray {

    private final DoublePredicate predicate;

    public ReduceDoubleArray(DoublePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public double[] applyAsDoubleArray(double[] array) {
        if (array == null)
            return null;

        int n = 0;

        for (double d : array) {
            if (predicate.test(d))
                n++;
        }
        double[] result = new double[n];
        n = 0;

        for (double d : array) {
            if (predicate.test(d)) {
                result[n++] = d;
            }
        }

        return result;
    }
}
