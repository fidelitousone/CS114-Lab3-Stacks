/*
* Author: Jatinder Singh
*/

import java.util.EmptyStackException;
import java.util.Stack;
import java.io.*;

public class Main {

    public static int eval(String data) {

        char[] chararry = data.toCharArray();

        Stack<Integer> val = new Stack<>();
        Stack<Character> operator = new Stack<>();

        for (int i = 0; i < chararry.length; i++) {

            if (chararry[i] == ' ') {
                continue;
            }

            if (chararry[i] >= '0' && chararry[i] <= '9') {

                StringBuffer buffer = new StringBuffer();

                while (i < chararry.length && chararry[i] >= '0' && chararry[i] <= '9') {
                    buffer.append(chararry[i++]);
                }

                val.push(Integer.parseInt(buffer.toString()));

            } else if (chararry[i] == '(') {
                operator.push(chararry[i]);
            } else if (chararry[i] == ')') {

                while (operator.peek() != '(') {
                    val.push(operation(operator.pop(), val.pop(), val.pop()));
                }

                operator.pop();
            } else if (chararry[i] == '+' || chararry[i] == '-' || chararry[i] == '*' || chararry[i] == '/') {

                while (!operator.empty() && pemdas(chararry[i], operator.peek())) {

                    val.push(operation(operator.pop(), val.pop(), val.pop()));

                }
                operator.push(chararry[i]);
            }
        }

        while (!operator.empty()) {
            val.push(operation(operator.pop(), val.pop(), val.pop()));
        }
        return val.pop();
    }

    public static boolean pemdas(char op, char op1) {

        if (op1 == '(' || op1 == ')') {
            return false;
        }

        if ((op == '*' || op == '/') && (op1 == '+' || op1 == '-')) {
            return false;
        } else {
            return true;
        }
    }


    public static int operation(char op, int u, int v) {

        switch (op) {
            case '+':
                return u + v;
            case '-':
                return u - v;
            case '*':
                return u * v;
            case '/':
                if (u == 0) {
                    System.out.println("Can't divide by 0");
                }
                return u / v;
        }

        return 0;
    }

    public static void main(String[] args) {
        String fileName = "infix.dat";
        String line;
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                try {
                    System.out.println(line);
                    System.out.println(Main.eval(line));
                } catch (EmptyStackException e) {
                    System.out.println("Invalid expression");
                }

            }

            bufferedReader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Unable to open file");
        }
        catch(IOException e) {
            System.out.println("Error reading file");
        }


    }


}
