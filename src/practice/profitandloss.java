package practice;
import java.util.Scanner;
public class profitandloss {
    public static void main(String[] args) {
        Scanner proloss = new Scanner(System.in);
        System.out.println("enter cost price : ");
        double c = proloss.nextDouble();
        Scanner profitloss = new Scanner(System.in);
        System.out.println("enter selling price : ");
        double s = profitloss.nextDouble();
        if(c>s)
        {
            System.out.println("its a loss of");
            System.out.println(c-s);
        }
        else if (s > c){
            System.out.println("its a profit of :");
            System.out.println(s-c);
        }

    }
}
