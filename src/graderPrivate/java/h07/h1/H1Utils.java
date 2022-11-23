package h07.h1;

import h07.Utils;
import h07.arrayoperators.PairwiseDoubleArrayBinaryOperatorGivingScalar;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class H1Utils {

    public static double[] convertStringToDoubleArray(String arrayAsString) {
        arrayAsString = arrayAsString.substring(1, arrayAsString.length() - 1);
        String[] split = arrayAsString.split(", ");
        if (Objects.equals(split[0], ""))
            return new double[0];
        return Arrays.stream(split).mapToDouble(Double::parseDouble).toArray();
    }

    public static String getClassContent() {
        String className = PairwiseDoubleArrayBinaryOperatorGivingScalar.class.getName().replace('.', '/') + ".java";
        try {
            return String.join(
                "\n",
                Files.readAllLines(Paths.get("src/main/java/" + className))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
