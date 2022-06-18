package h07;

import java.util.function.DoubleBinaryOperator;

public class EuclideanNorm implements DoubleBinaryOperator {

    @Override
    public double applyAsDouble(double left, double right) {
        return Math.sqrt(left * left + right * right);
    }

}
