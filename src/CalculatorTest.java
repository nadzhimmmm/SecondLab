import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @org.junit.jupiter.api.Test
    void test_our_example_to_postfix()
    {
        String expression1="4+5+3";
        Calculator test1=new Calculator(expression1);
        assertEquals("4 5 +3+", test1.our_example_to_postfix());

    }

    @org.junit.jupiter.api.Test
    void test_postfix_to_answer()
    {
        assertEquals(12.0, Calculator.postfix_to_answer("4 5 + 3 +"));
        assertEquals(18.0, Calculator.postfix_to_answer("8 3 - 3 * 6 - 2 * "));
        assertEquals(1008.0, Calculator.postfix_to_answer("4 8 7 3 5 - * - 6 + * 9 *"));
    }

    @org.junit.jupiter.api.Test
    void test_check()
    {
        Calculator test1=new Calculator("4*(8-7*(3-5)+6)*9))");
        assertEquals(0,test1.check());
        Calculator test2=new Calculator("4+4+(4+4)");
        assertEquals(1,test2.check());
    }

    @org.junit.jupiter.api.Test
    void test_get_priority()
    {
        Calculator test1=new Calculator("");
        assertEquals(3, test1.get_priority('*'));
        assertEquals(3, test1.get_priority('/'));
        assertEquals(2, test1.get_priority('+'));
        assertEquals(2, test1.get_priority('-'));
        assertEquals(3, test1.get_priority('*'));
        assertEquals(1, test1.get_priority('('));
        assertEquals(-1, test1.get_priority(')'));
        assertEquals(0, test1.get_priority('6'));
    }

    @org.junit.jupiter.api.Test
    void test_working_with_negative_numbers()
    {
        Calculator test1=new Calculator("4-(-6)");
        assertEquals("4-(0-6)", test1.working_with_negative_numbers());
        Calculator test2=new Calculator("4-(-6)*(-7-(-8))");
        assertEquals("4-(0-6)*(0-7-(0-8))", test2.working_with_negative_numbers());
    }






}
