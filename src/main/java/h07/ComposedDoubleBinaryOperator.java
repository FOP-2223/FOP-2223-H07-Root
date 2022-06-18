package h07;

import java.util.function.DoubleBinaryOperator;

public class ComposedDoubleBinaryOperator implements DoubleBinaryOperator {

    private final DoubleBinaryOperator op1;
    private final DoubleBinaryOperator op2;
    private final DoubleBinaryOperator op3;

    public ComposedDoubleBinaryOperator(DoubleBinaryOperator op1, DoubleBinaryOperator op2, DoubleBinaryOperator op3) {
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
    }

    @Override
    public double applyAsDouble(double left, double right) {
        double result1 = op1.applyAsDouble(left, right);
        double result2 = op2.applyAsDouble(left, right);

        return op3.applyAsDouble(result1, result2);
    }

}
