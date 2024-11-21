import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random_number = new Random();
            int guess_number = random_number.nextInt(1000) + 1;
            System.out.println(guess_number);
            user(guess_number);
    }

    public static void user(int guess_number) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Угадайте число от 1 до 1000");

        int user_number = 0;

        while (user_number != guess_number) {
            System.out.print("Введите число: ");
            user_number = scanner.nextInt();

            if (user_number > guess_number) {
                System.out.println("Меньше");
            }
            else if (user_number < guess_number) {
                System.out.println("Больше");
            }
            else {
                System.out.println("Угадал");
            }
        }
    }
}