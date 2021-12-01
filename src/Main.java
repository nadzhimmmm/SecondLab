import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;
public class Main
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("1)Ввести выражение.");
        System.out.println("0)Выход.");
        Scanner in = new Scanner(System.in);
        while (!in.hasNextInt()) {
            System.out.println("Ошибка. Неверный ввод. Введите число.");
            in.next();
        }
        int quit = in.nextInt();
        while (quit<0||quit>1) {
            System.out.println("Ошибка. Введите число 0 или 1.");
            quit = in.nextInt();
        }
        while (quit != 0)
        {

            Scanner scan = new Scanner(System.in);
            String example=scan.nextLine();
            new Calculator(example);
            System.out.print("Ответ:");
            System.out.println(Calculator.getting_a_solution());
            System.out.println("1)Ввести выражение.");
            System.out.println("0)Выход.");
            while (!in.hasNextInt())
            {
                System.out.println("Ошибка. Неверный ввод. Введите число.");
                in.next();
            }
            quit = in.nextInt();
            while (quit<0||quit>1)
            {
                System.out.println("Ошибка. Введите число 0 или 1.");
                quit = in.nextInt();
            }
        }
    }


}
