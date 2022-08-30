package h07.Operators;

import java.util.function.DoubleBinaryOperator;

public class DoubleSumSqrtsOfTwo implements DoubleBinaryOperator {

    @Override
    public double applyAsDouble(double left, double right) {
        return Math.sqrt(left) + Math.sqrt(right);
    }

}
