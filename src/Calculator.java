import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
/**
 * @author Mokhammad Nadzhim
 * @version 1.0
 */
/**
 * A class that parses an expression and gets its value
 */

public class Calculator {
    /**
     * The field in which we save the answer
     */

     static Double ANSWER;
     static String expression;
     Calculator(String _expression)
     {
         expression=_expression;
     }


    /**
     * A method that checks whether the characters '(' and ')' are entered correctly
     * @return 1 if entered correctly, otherwise 0
     */
    static int check() {
        int q1 = 0, q2 = 0;
        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);
            if (symbol == ')' && q1 == 0) {
                return 0;
            } else if (symbol == ')' && q1 != 0) {
                q1--;
            } else if (symbol == '(') {
                q1++;
            }
        }
        if (q1 == 0) {
            return 1;
        } else return 0;
    }
    /**
     *
     * @return
     */
    static int check1() {
        int k = 0;
        for (int i = 0; i < expression.length(); i++) {

            if (expression.charAt(i) == ','||expression.charAt(i) == '.'||expression.charAt(i) == '*' || expression.charAt(i) == '/' || expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '(' || expression.charAt(i) == ')' || Character.isDigit(expression.charAt(i)))
                k++;

        }

        if (k == expression.length())
            return 1;
        else return 0;


    }



    public static String our_example_to_postfix()  {
        String postfix = "";
        Stack<Character> stack = new Stack();
        int priority;
        for (int i = 0; i < expression.length(); i++) {
            priority = get_priority(expression.charAt(i));
            if (priority==0) {
                postfix += expression.charAt(i);
            }
            else if (priority == 1)
                stack.push(expression.charAt(i));
            else if (priority > 1) {
                postfix += " ";
                while (!stack.isEmpty()) {

                    if (get_priority(stack.peek()) >= priority) {
                        postfix += stack.pop();

                    }
                    else break;
                }
                stack.push(expression.charAt(i));
            } else if (priority == -1) {
                postfix += " ";
                while (get_priority(stack.peek()) != 1) {
                    postfix += stack.pop();

                }
                stack.pop();
            }
        }
        while (!stack.empty())
            postfix += stack.pop();
        return postfix;
    }

    /**
     *
     * @return
     */
    public static String our_example_to_postfix1()  {
        String postfix = "";
        Stack<Character> stack = new Stack();
        int priority;
        for (int i = 0; i < expression.length(); i++) {
            priority = get_priority(expression.charAt(i));
            if (priority==0)
                postfix += expression.charAt(i);
            else if (priority == 1)
                stack.push(expression.charAt(i));
            else if (priority > 1) {
                postfix += " ";
                while (!stack.isEmpty()) {
                    if (get_priority(stack.peek()) >= priority)
                        postfix += stack.pop();
                    else break;
                }
                stack.push(expression.charAt(i));
            } else if (priority == -1) {
                postfix += " ";
                while (get_priority(stack.peek()) != 1)
                    postfix += stack.pop();
                stack.pop();
            }
        }
        while (!stack.empty()) postfix += stack.pop();
        return postfix;
    }

    /**
     * A method that searches for a solution to the original expression using a postfix entry
     * @param postfix The original expression in the postfix
     * @return solving the expression
     */
    public static double postfix_to_answer(String postfix)  {
        String numbers = new String();
        Stack<Double> stack = new Stack();
        for (int i = 0; i < postfix.length(); i++) {
            if (postfix.charAt(i) == ' ') continue;
            if (get_priority(postfix.charAt(i)) == 0) {
                while (postfix.charAt(i) != ' ' && get_priority(postfix.charAt(i)) == 0) {
                    numbers += postfix.charAt(i++);
                    if (i == postfix.length())
                        break;
                }
                stack.push(Double.parseDouble(numbers));
                numbers = new String();
            }
            if (get_priority(postfix.charAt(i)) > 1) {
                    double a = stack.pop();
                    double b = stack.pop();
                    if (postfix.charAt(i) == '+')
                        stack.push(b + a);
                    if (postfix.charAt(i) == '-')
                        stack.push(b - a);
                    if (postfix.charAt(i) == '*')
                        stack.push(b * a);
                    if (postfix.charAt(i) == '/')
                        stack.push(b / a);
            }
        }
        ANSWER = stack.pop();
        return ANSWER;
    }

    /**
     * The method returns the priority of  symbol
     * @param element The symbol in the expression
     * @return priority
     */
    public static int get_priority(char element) {
        if (element == '*' || element == '/')
            return 3;
        else if (element == '+' || element == '-')
            return 2;
        else if (element == '(')
            return 1;
        else if (element == ')')
            return -1;
        else
            return 0;

    }




    /**
     * The method that solves the expression
     * @throws Exception if incorrect is entered
     * @return
     */


    public static double getting_a_solution() throws Exception {

        if (check1() == 1 && check() == 1) {
            working_with_negative_numbers();
            return postfix_to_answer(our_example_to_postfix());
        }

               else throw new Exception("Ошибка. Неверный ввод.");


    }



    /**
     * A method that corrects an expression to work with negative numbers
     * @return modified expression
     */
    public static String working_with_negative_numbers()
    {

            String preparedExpression = new String();
            for (int token = 0; token < expression.length(); token++) {
                char symbol = expression.charAt(token);
                if (symbol == '-') {
                    if (token == 0)
                        preparedExpression += '0';
                    else if (expression.charAt(token - 1) == '(')
                        preparedExpression += '0';
                }
                preparedExpression += symbol;

            }
            expression=preparedExpression;
            return expression;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null||getClass()!=o.getClass()) return false;
        Calculator calculator=(Calculator) o;
        return this.ANSWER==calculator.ANSWER;
    }

    @Override
    public String toString() {
        return String.valueOf(ANSWER);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}