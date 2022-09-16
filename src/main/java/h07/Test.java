package h07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        try {
            System.out.println(new File(".").getCanonicalPath());

            File file = new File(new File(".").getCanonicalPath() + "/src/main/java/h07/doubleoperators/DoubleBinaryOperatorFactory.java");

            BufferedReader br = new BufferedReader(new FileReader(file));

            StringBuilder sb = new StringBuilder();
            String str;
            while((str = br.readLine()) != null) {
                sb.append(str);
            }
            String[] array = sb.toString().split(";");
            for (String string : array) {
                if (string.contains("Math::max"))
                    System.out.println(string);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
