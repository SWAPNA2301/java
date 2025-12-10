package practice;

import java.util.HashMap;
import java.util.Scanner;

public class CharFrequency {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String str = sc.nextLine().toLowerCase(); // lowercase optional

        HashMap<Character, Integer> frequency = new HashMap<>();

        for (char ch : str.toCharArray()) {
            if (ch != ' ') { // ignore spaces optional
                frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
            }
        }

        System.out.println("\nCharacter Frequency: ");
        for (char key : frequency.keySet()) {
            System.out.println(key + " : " + frequency.get(key));
        }
    }
}
