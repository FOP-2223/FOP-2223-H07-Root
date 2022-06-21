package h07;

import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingArray;
import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingScalar;
import h07.arrayoperators.ReduceDoubleArray;

import java.util.function.DoubleBinaryOperator;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ReduceDoubleArray reducer = new ReduceDoubleArray(x -> x >= 100 && x <= 1000);

        MyTinyMath math1 = new MyTinyMath(true, true);
        MyTinyMath math2 = new MyTinyMath(true, false);
        MyTinyMath math3 = new MyTinyMath(false, true);
        MyTinyMath math4 = new MyTinyMath(false, false);

        DoubleBinaryOperator op1 = math1::conditionalSum;
        DoubleBinaryOperator op2 = math2::conditionalSum;
        DoubleBinaryOperator op3 = math3::conditionalSum;
        DoubleBinaryOperator op4 = math4::conditionalSum;

        PairwiseDoubleArrayBinaryOperatorGivingArray arrayGiver1 = new PairwiseDoubleArrayBinaryOperatorGivingArray(op2);
        PairwiseDoubleArrayBinaryOperatorGivingArray arrayGiver2 = new PairwiseDoubleArrayBinaryOperatorGivingArray(op3);

        PairwiseDoubleArrayBinaryOperatorGivingScalar scalarGiver1 = new PairwiseDoubleArrayBinaryOperatorGivingScalar(op1, op4, 0.0);

        double[][] arrays = new double[12][300];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 300; j++) {
                if (i < 4) {
                    arrays[i][j] = i * j;
                } else {
                    arrays[i][j] = 1200 - i * j;
                }
            }
        }

        arrays[8] = reducer.applyAsDoubleArray(arrays[0]);
        arrays[9] = reducer.applyAsDoubleArray(arrays[1]);
        arrays[10] = reducer.applyAsDoubleArray(arrays[6]);
        arrays[11] = reducer.applyAsDoubleArray(arrays[7]);
    }
}
