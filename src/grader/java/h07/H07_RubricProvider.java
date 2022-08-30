package h07;

import h07.h1.h1_1.DoubleSumWithCoefficientsOpTest;
import h07.h1.h1_2.EuclideanNormTest;
import h07.h1.h1_3.DoubleMaxOfTwoTest;
import h07.h1.h1_4.ComposedDoubleBinaryOperatorTest;
import org.sourcegrade.jagr.api.rubric.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;

@RubricForSubmission("h07")
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
        .shortDescription("H1.1: Erste binäre Operatorklasse auf double")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Method \"applyAsDouble\" works as intended.",
                () -> DoubleSumWithCoefficientsOpTest.class.getDeclaredMethod("applyAsDoubleTest", double.class, double.class, double.class, double.class, double.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H1_2 = Criterion
        .builder()
        .shortDescription("H1.2: Zweite binäre Operatorklasse auf double")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Method \"applyAsDouble\" works as intended.",
                () -> EuclideanNormTest.class.getDeclaredMethod("applyAsDoubleTest", double.class, double.class, double.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H1_3 = Criterion
        .builder()
        .shortDescription("H1.3: Dritte binäre Operatorklasse auf double")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Method \"applyAsDouble\" works as intended.",
                () -> DoubleMaxOfTwoTest.class.getDeclaredMethod("applyAsDoubleTest", double.class, double.class, double.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H1_4 = Criterion
        .builder()
        .shortDescription("H1.4: Vierte binäre Operatorklasse auf double")
        .addChildCriteria(
            DEFAULT_CRITERION.apply(
                "Method \"applyAsDouble\" works as intended.",
                () -> ComposedDoubleBinaryOperatorTest.class.getDeclaredMethod("applyAsDoubleTest", String.class, String.class, String.class, double.class, double.class, double.class)
            )
        )
        .build();

    private static final Criterion CRITERION_H1 = Criterion
        .builder()
        .shortDescription("H1: Binäre Operatoren auf double als Functional Interfaces")
        .addChildCriteria(
            CRITERION_H1_1,
            CRITERION_H1_2,
            CRITERION_H1_3,
            CRITERION_H1_4
        )
        .build();

    private static final Criterion CRITERION_H2_1 = Criterion
        .builder()
        .shortDescription("H2.1: Die Operatorenfabrik")
        .addChildCriteria()
        .build();

    private static final Criterion CRITERION_H2_2 = Criterion
        .builder()
        .shortDescription("H2.2: Operatoren mittels new")
        .addChildCriteria()
        .build();

    private static final Criterion CRITERION_H2_3 = Criterion
        .builder()
        .shortDescription("H2.3: Operatoren mittels Lambda-Ausdrücken in Kurzform und Standardform")
        .addChildCriteria()
        .build();

    private static final Criterion CRITERION_H2 = Criterion
        .builder()
        .shortDescription("H2: Lambda-Ausdrücke in Kurzform und Standardform in „Operatorenfabrik“")
        .addChildCriteria(
            CRITERION_H2_1,
            CRITERION_H2_2,
            CRITERION_H2_3
        )
        .build();

    private static final Criterion CRITERION_H3_1 = Criterion
        .builder()
        .shortDescription("H3.1: Unäre Filter-Klasse auf „Array von double“")
        .addChildCriteria()
        .build();

    private static final Criterion CRITERION_H3_2 = Criterion
        .builder()
        .shortDescription("H3.2: Binäre Map-Klasse auf „Array von double“")
        .addChildCriteria()
        .build();

    private static final Criterion CRITERION_H3_3 = Criterion
        .builder()
        .shortDescription("H3.3: Binäre Fold-Klasse auf „Array von double“")
        .addChildCriteria()
        .build();

    private static final Criterion CRITERION_H3 = Criterion
        .builder()
        .shortDescription("H3: Unäre und binäre Operatoren auf „Array von double“ als Functional Interfaces")
        .addChildCriteria(
            CRITERION_H3_1,
            CRITERION_H3_2,
            CRITERION_H3_3
        )
        .build();

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H07")
        .addChildCriteria(
            CRITERION_H1,
            CRITERION_H2,
            CRITERION_H3
        )
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
