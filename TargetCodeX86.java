import java.io.*;
import java.util.*;

public class TargetCodeX86 {

    // precedence
    static int prec(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        return 0;
    }

    // infix to postfix
    static String infixToPostfix(String exp) {
        Stack<Character> stack = new Stack<>();
        String result = "";

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result += c;
            }
            else if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result += stack.pop();
                stack.pop();
            }
            else {
                while (!stack.isEmpty() && prec(stack.peek()) >= prec(c))
                    result += stack.pop();
                stack.push(c);
            }
        }

        while (!stack.isEmpty())
            result += stack.pop();

        return result;
    }

    public static void main(String args[]) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter expression:");
        String infix = in.readLine();

        String postfix = infixToPostfix(infix);

        System.out.println("Postfix: " + postfix);

        Stack<String> stack = new Stack<>();

        System.out.println("\nGenerated Target Code (x86-like):\n");

        for (int i = 0; i < postfix.length(); i++) {

            char c = postfix.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            }
            else {
                String op2 = stack.pop();
                String op1 = stack.pop();

                System.out.println("MOV AX, " + op1);

                switch (c) {
                    case '+': System.out.println("ADD AX, " + op2); break;
                    case '-': System.out.println("SUB AX, " + op2); break;
                    case '*': System.out.println("MUL AX, " + op2); break;
                    case '/': System.out.println("DIV AX, " + op2); break;
                }

                stack.push("T"); // temporary
            }
        }
    }
}