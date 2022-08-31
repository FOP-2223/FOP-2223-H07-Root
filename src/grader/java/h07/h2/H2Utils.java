package h07.h2;

import h07.doubleoperators.PairOfDoubleCoefficients;
import h07.doubleoperators.TripleOfDoubleBinaryOperators;

public class H2Utils {

    public static Object convertStringToObject(String objectAsString) {
        if (objectAsString == null)
            return null;
        switch (objectAsString) {
            case "null":
                return null;
            case "Object":
                return new Object();
            case "BooleanTrue":
                return new Boolean(true);
            case "BooleanFalse":
                return new Boolean(false);
            case "PairOfDoubleCoefficients":
                return new PairOfDoubleCoefficients();
            case "TripleOfDoubleBinaryOperators":
                return new TripleOfDoubleBinaryOperators();
            default:
                break;
        }
        return null;
    }

}
