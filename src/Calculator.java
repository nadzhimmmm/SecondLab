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
public class Calculator
{
    /**
     * The field in which we save the answer
     */
    static String ANSWER;

    /**
     * The field that stores the original expression
     */
    static String expression;

    /**
     * Class constructor
     *
     * @param _expression the original expression
     */
    Calculator(String _expression)
    {
        expression = _expression;
    }

    /**
     * A method that adds a multiplication sign if it is needed there
     * @return modified expression
     */
    public static String working_with_the_multiplication_symbol()
    {
        String preparedExpression = new String();
        preparedExpression="";
        preparedExpression+=expression.charAt(0);
        for (int token = 1; token < expression.length(); token++)
        {
            char symbol = expression.charAt(token);
            char symbol1 = expression.charAt(token-1);
            if (symbol == '('&&symbol1!='*'&&symbol1!='+'&&symbol1!='-'&&symbol1!='/') {
                preparedExpression += '*';
            }
            preparedExpression += symbol;
        }
        expression=preparedExpression;
        return expression;
    }

    /**
     * A method that corrects an expression to work with negative numbers
     * @return modified expression
     */
    public static String working_with_negative_numbers() {
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

    /**
     * The method returns the priority of  symbol
     * @param element The symbol in the expression
     * @return priority
     */
    public static int get_priority(char element)
    {
        switch(element) {
            case '*':
            case '/':
                return 3;
            case '+':
            case '-':
                return 2;
            case '(': return 1;
            case ')': return -1;
        }
        return 0;
    }

    /**
     * The method that solves the expression
     * @return solving the expression
     * @throws Exception invalid input
     */
    public static String getting_a_solution()
    {
        try {
            working_with_negative_numbers();
            working_with_the_multiplication_symbol();
            working_with_a_degree();
            return postfix_to_answer(our_example_to_postfix());
        }
        catch(java.lang.NumberFormatException e)
        {
            return "Ошибка!";
        }
        catch(java.util.EmptyStackException q)
        {
            return "Ошибка!";
        }
        catch(java.lang.StringIndexOutOfBoundsException w)
        {
            return "Ошибка!";
        }
    }


    /**
     * A method that translates the original expression into a postfix form
     *
     * @return expression in a postfix entry
     */
    public static String our_example_to_postfix()
    {
        String postfix_entry = "";
        Stack<Character> stack = new Stack();
        int priority;
        for (int i = 0; i < expression.length(); i++)
        {
            priority = get_priority(expression.charAt(i));
            if (priority == 0)//0-число
                postfix_entry += expression.charAt(i);

            else
                if (priority == 1)//1-открывающая скобка
                     stack.push(expression.charAt(i));
                else
                        if (priority > 1)
                        {
                            postfix_entry += " ";
                            while (!stack.isEmpty())
                            {
                                if (get_priority(stack.peek()) >= priority)
                                    postfix_entry += stack.pop();
                                else break;
                            }
                            stack.push(expression.charAt(i));
                        }
                        else
                            if (priority == -1) //-1 закрывающая скобка
                                 {
                                     postfix_entry += " ";
                                     while (get_priority(stack.peek()) != 1)
                                     {
                                         postfix_entry += stack.pop();
                                     }
                                     stack.pop();
                                 }
        }
        while (!stack.empty())
            postfix_entry += stack.pop();
        return postfix_entry;
    }

    /**
     * A method that searches for a solution to the original expression using a postfix entry
     *
     * @param postfix_entry The original expression in the postfix
     * @return solving the expression
     */
    public static String postfix_to_answer(String postfix_entry) {
        String numbers = new String();
        Stack<Double> stack = new Stack();
        for (int i = 0; i < postfix_entry.length(); i++)
        {
            if (postfix_entry.charAt(i) == ' ') continue;
            if (get_priority(postfix_entry.charAt(i)) == 0)
            {
                while (postfix_entry.charAt(i) != ' ' && get_priority(postfix_entry.charAt(i)) == 0)
                {
                    numbers += postfix_entry.charAt(i++);
                    if (i == postfix_entry.length())
                        break;
                }
                stack.push(Double.parseDouble(numbers));
                numbers = new String();
            }
            if (get_priority(postfix_entry.charAt(i)) > 1&&get_priority(postfix_entry.charAt(i)) <4)
            {
                double a = stack.pop();
                double b = stack.pop();
                if (postfix_entry.charAt(i) == '+')
                    stack.push(b + a);
                if (postfix_entry.charAt(i) == '-')
                    stack.push(b - a);
                if (postfix_entry.charAt(i) == '*')
                    stack.push(b * a);
                if (postfix_entry.charAt(i) == '/')
                    stack.push(b / a);
            }

        }
        double ANSWER1 = stack.pop();
        ANSWER = Double.toString(ANSWER1);
        return ANSWER;
    }








    /**
     * Ф method that works with a degree
     * @return modified expression
     */

    public static String working_with_a_degree()
    {
        String preparedExpression = new String();
        preparedExpression="";
        preparedExpression+=expression.charAt(0);
        for (int token = 1; token < expression.length()-1; token++)
        {
            char symbol = expression.charAt(token);
            char symbol1=expression.charAt(token-1);

            if (symbol == '^'&&symbol1!=')')
            {
                char symbol2=expression.charAt(token+1);
                int pow = Character.getNumericValue(symbol2);
                for(int i=0;i<pow-1;i++)
                {
                    preparedExpression+='*';
                    preparedExpression+=symbol1;
                }
                ++token;

            }
/*
            if(symbol=='^'&&symbol1==')')
            {
                int t2=token;
                int t1 = 0;
                for(int i=t2;t2>=0;t2--)
                {
                    if(expression.charAt(i)=='(')
                    {
                        t1 = i;
                    }
                }
                preparedExpression+='*';
                String s1="";
                for(int i=t1;i<=t2;i++)
                {
                    s1+=expression.charAt(i);
                }
                preparedExpression+=s1;
                ++token;
            }
*/
            else
            preparedExpression += symbol;
            if(token==expression.length()-2)
            {
                preparedExpression += expression.charAt(token+1);
            }

        }
        expression= preparedExpression;
        return expression;
    }




    /**
     * Redefined method equals
     * @param o the object being compared
     * @return 1 if equal, 0 otherwise
     */
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null||getClass()!=o.getClass()) return false;
        Calculator calculator=(Calculator) o;
        return this.ANSWER==calculator.ANSWER;
    }

    /**
     * Redefined method toString
     * @return expression response
     */
    @Override
    public String toString() {
        return String.valueOf(ANSWER);
    }

    /**
     * Redefined method hashCode
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}