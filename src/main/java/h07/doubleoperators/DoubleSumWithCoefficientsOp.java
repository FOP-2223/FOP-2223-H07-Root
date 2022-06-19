package h07.doubleoperators;

import java.util.function.DoubleBinaryOperator;

public class DoubleSumWithCoefficientsOp implements DoubleBinaryOperator {

    private final double coeff1;
    private final double coeff2;

    public DoubleSumWithCoefficientsOp(double coeff1, double coeff2) {
        this.coeff1 = coeff1;
        this.coeff2 = coeff2;
    }

    @Override
    public double applyAsDouble(double left, double right) {
        return coeff1 * left + coeff2 * right;
    }
}
