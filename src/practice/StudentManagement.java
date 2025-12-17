package practice;
import java.util.Scanner;

class Student {
    int roll;
    String name;
    int[] marks = new int[5];

    int total() {
        int sum = 0;
        for (int m : marks) {
            sum += m;
        }
        return sum;
    }

    double average() {
        return total() / 5.0;
    }

    char grade() {
        double avg = average();
        if (avg >= 90) return 'A';
        else if (avg >= 75) return 'B';
        else if (avg >= 60) return 'C';
        else if (avg >= 40) return 'D';
        else return 'F';
    }

    void display() {
        System.out.println("\nRoll No: " + roll);
        System.out.println("Name: " + name);
        System.out.println("Total Marks: " + total());
        System.out.println("Average: " + average());
        System.out.println("Grade: " + grade());
    }
}

public class StudentManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter roll number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        Student s = new Student();
        s.roll = roll;
        s.name = name;

        System.out.println("Enter marks for 5 subjects:");
        for (int i = 0; i < 5; i++) {
            s.marks[i] = sc.nextInt();
        }

        s.display();
    }
}
