package h07;

import java.util.function.DoubleBinaryOperator;

public class DoubleMaxOfTwo implements DoubleBinaryOperator {

    @Override
    public double applyAsDouble(double left, double right) {
        return Math.max(left, right);
    }

}
