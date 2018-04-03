/*
* Author: Jatinder Singh
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;


public class PostFixEval {
    public static int op(String data, int n1, int n2) {
        if (data.equals("+")) {
            return n1 + n2;
        } else if (data.equals("*")) {
            return n1 * n2;
        } else if (data.equals("-")) {
            return n1 - n2;
        } else if (data.equals("/")) {
            return n1 / n2;

        } else {
            throw new IllegalArgumentException("wrong operation");

        }
    }

    public int calculation(String data) {
        Stack<String> eval = new Stack<>();

        StringTokenizer tokens = new StringTokenizer(data, "+-*/ ", true);

        try {
            while (tokens.hasMoreTokens()) {
                String token = tokens.nextToken();

                if ("+-*/ ".indexOf(token) >= 0) {

                    if (token.equals(" ")) {
                        continue;
                    }
                } else {
                    eval.push(token);
                }
            }
        } catch (EmptyStackException e) {
            System.out.println("Invalid expression");
        }

        int res = Integer.parseInt(eval.pop());

        return res;

    }

    public static void main(String[] args) {
        PostFixEval p = new PostFixEval();

        String fileName = "postfix.dat";
        String line;
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                try {
                    System.out.println(line);
                    System.out.println(p.calculation(line));
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

