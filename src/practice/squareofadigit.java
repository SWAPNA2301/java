package basics;
import java.util.Scanner;
public class squareofadigit {
    public static void main(String[] args) {
        Scanner sq = new Scanner(System.in);
        System.out.println("enter a number : ");
        int number = sq.nextInt();
        int square= number*number;
        System.out.println("the square of the give number is ");
        System.out.println(square);
    }
}
