package practice;

import java.util.Scanner;

public class reversenumber {
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter a number: ");
            int num = sc.nextInt();

            int reversed = 0;

            while (num != 0) {
                int digit = num % 10; // extract last digit
                reversed = reversed * 10 + digit; // append digit to reverse
                num /= 10; // remove last digit
            }

            System.out.println("Reversed number is: " + reversed);
        }
    }


