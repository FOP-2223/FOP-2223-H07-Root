package h07.doubleoperators;

import java.util.function.DoubleBinaryOperator;

public class DoubleMaxOfTwo implements DoubleBinaryOperator {

    @Override
    public double applyAsDouble(double left, double right) {
        return Math.max(left, right);
    }

}
