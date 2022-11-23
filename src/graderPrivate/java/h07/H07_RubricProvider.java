package h07;

import h07.h1.h1_1.ReduceDoubleArrayTest;
import h07.h1.h1_2.PairwiseDoubleArrayBinaryOperatorGivingArrayTest;
import h07.h1.h1_3.PairwiseDoubleArrayBinaryOperatorGivingScalarTest;
import h07.h2.h2_1.DoubleSumWithCoefficientsOpTest;
import h07.h2.h2_2.EuclideanNormTest;
import h07.h2.h2_3.DoubleMaxOfTwoTest;
import h07.h2.h2_4.ComposedDoubleBinaryOperatorTest;
import org.sourcegrade.jagr.api.rubric.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;

public class H07_RubricProvider implements RubricProvider {

    private static final BiFunction<String, Callable<Method>, Criterion> DEFAULT_CRITERION = (s, methodCallable) ->
        Criterion.builder()
            .shortDescription(s)
            .grader(Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofMethod(methodCallable))
                .pointsFailedMin()
                .pointsPassedMax()
                .build())
            .build();

    private static final Criterion CRITERION_H1_1 = Criterion
        .builder()
        .shortDescription("H1.1: Unäre Filter-Klasse auf „Array von double“")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekterweise \"null\" zurück, sollte sie mit \"null\" aufgerufen werden.",
                () -> ReduceDoubleArrayTest.class.getDeclaredMethod("testNullInput")
            ),
            DEFAULT_CRITERION.apply(
                "Die Rückgabe der Methode hat die korrekte Länge.",
                () -> ReduceDoubleArrayTest.class.getDeclaredMethod("testLengthOfResult", String.class, String.class, String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei Verwendung verschiedener Operatoren.",
                () -> ReduceDoubleArrayTest.class.getDeclaredMethod("testResult", String.class, String.class, String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode verändert das übergebene Array nicht.",
                () -> ReduceDoubleArrayTest.class.getDeclaredMethod("testModificationOfInputArray", String.class, String.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H1_2 = Criterion
        .builder()
        .shortDescription("H1.2: Binäre Map-Klasse auf „Array von double“")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekterweise \"null\" zurück, sollte einer ihrer Parameter ebenfalls \"null\" sein.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingArrayTest.class.getDeclaredMethod("testNullInput")
            ),
            DEFAULT_CRITERION.apply(
                "Die Rückgabe der Methode hat die korrekte Länge.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingArrayTest.class.getDeclaredMethod("testLengthOfResult", String.class, String.class, String.class, String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei Verwendung verschiedener Operatoren.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingArrayTest.class.getDeclaredMethod("testResult", String.class, String.class, String.class, String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode verändert die übergebenen Arrays nicht.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingArrayTest.class.getDeclaredMethod("testModificationOfInputArrays", String.class, String.class, String.class, String.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H1_3 = Criterion
        .builder()
        .shortDescription("H1.3: Binäre Fold-Klasse auf „Array von double“")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekterweise \"null\" zurück, sollte sie mit \"null\" aufgerufen werden.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingScalarTest.class.getDeclaredMethod("testNullInput")
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei Verwendung verschiedener Operatoren.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingScalarTest.class.getDeclaredMethod("testResult", String.class, String.class, String.class, String.class, String.class, String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode verwendet keine Rekursion.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingScalarTest.class.getDeclaredMethod("checkRecursion", String.class, String.class, String.class, String.class, String.class)
            ),
            DEFAULT_CRITERION.apply(
                "Die Methode verwendet lediglich eine Schleife.",
                () -> PairwiseDoubleArrayBinaryOperatorGivingScalarTest.class.getDeclaredMethod("checkLoops")
            )
        )
        .build();

    private static final Criterion CRITERION_H1 = Criterion
        .builder()
        .shortDescription("H1: Unäre und binäre Operatoren auf „Array von double“ als Functional Interfaces")
        .addChildCriteria(
            CRITERION_H1_1,
            CRITERION_H1_2,
            CRITERION_H1_3
        )
        .build();

    private static final Criterion CRITERION_H2_1 = Criterion
        .builder()
        .shortDescription("H2.1: Erste binäre Operatorklasse auf double")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei verschiedenen Eingabewerten.",
                () -> DoubleSumWithCoefficientsOpTest.class.getDeclaredMethod("testResults", double.class, double.class, double.class, double.class, double.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H2_2 = Criterion
        .builder()
        .shortDescription("H2.2: Zweite binäre Operatorklasse auf double")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei verschiedenen Eingabewerten.",
                () -> EuclideanNormTest.class.getDeclaredMethod("testResults", double.class, double.class, double.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H2_3 = Criterion
        .builder()
        .shortDescription("H2.3: Dritte binäre Operatorklasse auf double")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Die Methode liefert korrekte Ergebnisse bei verschiedenen Eingabewerten.",
                () -> DoubleMaxOfTwoTest.class.getDeclaredMethod("testResults", double.class, double.class, double.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H2_4 = Criterion
        .builder()
        .shortDescription("H2.4: Vierte binäre Operatorklasse auf double")
        .addChildCriteria(

        )
        .build();

    private static final Criterion CRITERION_H2 = Criterion
        .builder()
        .shortDescription("H1: Binäre Operatoren auf double als Functional Interfaces")
        .addChildCriteria(
            CRITERION_H2_1,
            CRITERION_H2_2,
            CRITERION_H2_3,
            CRITERION_H2_4
        )
        .build();

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H07")
        .addChildCriteria(
            CRITERION_H1,
            CRITERION_H2
            //CRITERION_H3
        )
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
