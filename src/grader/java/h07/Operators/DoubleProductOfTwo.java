package h07.Operators;

import java.util.function.DoubleBinaryOperator;

public class DoubleProductOfTwo implements DoubleBinaryOperator {

    @Override
    public double applyAsDouble(double left, double right) {
        return left * right;
    }

}
