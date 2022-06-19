package h07;

public class MyTinyMath {

    private final boolean bool1;
    private final boolean bool2;

    public MyTinyMath(boolean bool1, boolean bool2) {
        this.bool1 = bool1;
        this.bool2 = bool2;
    }

    public double conditionalSum(double left, double right) {
        if (bool1)
            left *= 2;
        if (bool2)
            right *= 2;
        return left + right;
    }
}
