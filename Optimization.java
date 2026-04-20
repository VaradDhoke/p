import java.io.*;
import java.util.*;

public class Optimization {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String s1, s2;

        // Duplicate expression check
        System.out.println("Enter string1:");
        s1 = in.readLine();

        System.out.println("Enter string2:");
        s2 = in.readLine();

        if (s1.equals(s2)) {
            System.out.println("Duplicate expression detected → can be optimized");
            s2 = null;
        } else {
            System.out.println("Expressions are different");
        }

        // Read code lines
        System.out.println("\nEnter number of lines:");
        int n = Integer.parseInt(in.readLine());

        String code[] = new String[n];

        System.out.println("Enter code lines:");
        for (int i = 0; i < n; i++) {
            code[i] = in.readLine();
        }

        System.out.println("\n--- Optimization Result ---");

        for (int i = 0; i < n - 1; i++) {

            // Dead code detection example
            if (code[i].contains("int") && code[i + 1].contains("=")) {

                String parts1[] = code[i].split(" ");
                String parts2[] = code[i + 1].split("=");

                if (parts1.length > 1 && parts2.length > 0) {

                    String var1 = parts1[1].replace(";", "");
                    String var2 = parts2[0].trim();

                    if (!var1.equals(var2)) {
                        System.out.println("Dead code detected at line: " + code[i + 1]);
                    }
                }
            }
        }

        System.out.println("\nOptimized code:");
        for (int i = 0; i < n; i++) {
            System.out.println(code[i]);
        }
    }
}