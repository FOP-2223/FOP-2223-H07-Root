package h07;

import java.util.function.DoubleBinaryOperator;

public class DoubleBinaryOperatorFactory {

    public static Object buildOperator(String str, Object obj, boolean bool) {
        if (!bool) {

            switch (str) {

                case "Coeffs":
                    if (obj instanceof PairOfDoubleCoefficients) {
                        return new DoubleSumWithCoefficientsOp(
                            ((PairOfDoubleCoefficients) obj).coeff1,
                            ((PairOfDoubleCoefficients) obj).coeff2
                        );
                    }

                case "Euclidean":
                    return new EuclideanNorm();

                case "Max":
                    return new DoubleMaxOfTwo();

                case "Composed":
                    if (obj instanceof TripleOfDoubleBinaryOperators) {
                        return new ComposedDoubleBinaryOperator(
                            ((TripleOfDoubleBinaryOperators) obj).operator1,
                            ((TripleOfDoubleBinaryOperators) obj).operator2,
                            ((TripleOfDoubleBinaryOperators) obj).operator3
                        );
                    }

                default:
                    return null;
            }
        } else {
            DoubleBinaryOperator operator = null;

            if (str.equals("Coeffs") && obj instanceof PairOfDoubleCoefficients) {
                operator = (double left, double right) -> {
                    return left * ((PairOfDoubleCoefficients) obj).coeff1 + right * ((PairOfDoubleCoefficients) obj).coeff2;
                };
            }

            if (str.equals("Euclidean")) {
                operator = (double left, double right) -> {
                    return Math.sqrt(left * left + right * right);
                };
            }

            if (str.equals("Max")) {
                if ((obj instanceof Boolean)) {
                    if ((Boolean) obj) {
                        operator = (left, right) -> left < right ? right : left;
                    } else {
                        operator = Math::max;
                    }
                }
            }

            if (str.equals("Composed") && obj instanceof TripleOfDoubleBinaryOperators) {
                operator = (left, right) ->
                    ((TripleOfDoubleBinaryOperators) obj).operator3.applyAsDouble(
                        ((TripleOfDoubleBinaryOperators) obj).operator1.applyAsDouble(left, right),
                        ((TripleOfDoubleBinaryOperators) obj).operator2.applyAsDouble(left, right)
                    );
            }

            return operator;
        }
    }

}
