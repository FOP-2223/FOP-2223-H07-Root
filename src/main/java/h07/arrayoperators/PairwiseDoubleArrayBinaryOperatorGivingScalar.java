package h07.arrayoperators;

import h07.arrayoperators.DoubleArrayBinaryOperatorGivingScalar;

import java.util.function.DoubleBinaryOperator;

public class PairwiseDoubleArrayBinaryOperatorGivingScalar implements DoubleArrayBinaryOperatorGivingScalar {

    private final DoubleBinaryOperator operator1;
    private final DoubleBinaryOperator operator2;
    private double d;

    public PairwiseDoubleArrayBinaryOperatorGivingScalar(DoubleBinaryOperator operator1, DoubleBinaryOperator operator2, double d) {
        this.operator1 = operator1;
        this.operator2 = operator2;
        this.d = d;
    }

    @Override
    public double applyAsDoubleArray(double[] left, double[] right) {
        int min = left.length < right.length ? left.length : right.length;

        for (int i = min - 1; i >= 0; i--) {
            d = operator2.applyAsDouble(operator1.applyAsDouble(left[i], right[i]), d);
        }

        return d;
    }
}
