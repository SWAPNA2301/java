package practice;
import java.util.Scanner;

public class sumoftwo {
    public static void main(String[] args) {

        Scanner plus = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int i = plus.nextInt();

        System.out.print("Enter second number: ");
        int r = plus.nextInt();

        int sum = i + r;

        System.out.println("The sum of the digits is: " + sum);
    }
}
