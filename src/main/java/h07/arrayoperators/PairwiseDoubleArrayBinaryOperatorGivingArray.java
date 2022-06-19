package h07.arrayoperators;

import h07.arrayoperators.DoubleArrayBinaryOperatorGivingArray;

import java.util.function.DoubleBinaryOperator;

public class PairwiseDoubleArrayBinaryOperatorGivingArray implements DoubleArrayBinaryOperatorGivingArray {

    private final DoubleBinaryOperator operator;

    public PairwiseDoubleArrayBinaryOperatorGivingArray(DoubleBinaryOperator operator) {
        this.operator = operator;
    }

    @Override
    public double[] applyAsDoubleArray(double[] left, double[] right) {
        if (left == null || right == null)
            return null;

        double[] result = left.length < right.length ? new double[left.length] : new double[right.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = operator.applyAsDouble(left[i], right[i]);
        }

        return result;
    }
}
