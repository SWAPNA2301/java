package practice;
import java.util.Scanner;
public class evenorodd {
    public static void main( String[] args) {
        Scanner ev = new Scanner(System.in);
        System.out.println("enter a number : ");
        int num = ev.nextInt();
        if ( num % 2 == 0) {
            System.out.println("even");
        } else {
            System.out.println("odd");
        }
    }
}
