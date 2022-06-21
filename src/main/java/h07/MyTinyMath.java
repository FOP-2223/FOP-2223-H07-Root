package h07;

/**
 * Class to test previously created classes and lambda-expressions :)
 */
public class MyTinyMath {

    /**
     * Boolean.
     */
    private final boolean bool1;

    /**
     * The cooler Boolean.
     */
    private final boolean bool2;

    /**
     * Constructor initializes the boolean values.
     *
     * @param bool1     First parameter.
     * @param bool2     Second parameter.
     */
    public MyTinyMath(boolean bool1, boolean bool2) {
        // Assign first parameter to first boolean
        this.bool1 = bool1;

        // Assign second parameter to cooler boolean
        this.bool2 = bool2;
    }

    /**
     * Squares given double values according to the encapsulated boolean values
     * and returns sum of the two.
     * Squares first parameter, if first boolean is true.
     * Squares second parameter, if cooler boolean is true.
     *
     * @param left      First parameter.
     * @param right     Second parameter.
     * @return          The sum.
     */
    public double conditionalSum(double left, double right) {
        // Check whether first boolean is true
        if (bool1) {

            // Square first parameter
            left *= 2;
        }

        // Check whether cooler boolean is true
        if (bool2) {

            // Square second parameter
            right *= 2;
        }

        // Return sum of the two parameters
        return left + right;
    }
}
