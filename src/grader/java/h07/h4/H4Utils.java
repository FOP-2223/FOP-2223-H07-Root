package h07.h4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class H4Utils {

    public static String getFactoryContent() {
        try {
            return String.join(
                "",
                Files.readAllLines(Paths.get("../../src/main/java/h07/doubleoperators/DoubleBinaryOperatorFactory.java"))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
