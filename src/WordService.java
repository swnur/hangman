import java.util.Random;
import java.util.Scanner;

public class WordService {
    private final Scanner scanner;
    private final Random random;

    public WordService() {
        random = new Random();
        scanner = new Scanner(System.in);
    }

    public String getUserStartInput() {
        return scanner.nextLine().toLowerCase();
    }


    public char getUserGuessInput() {
        System.out.print("Enter letter from (a-z) or (A-Z): ");
        String input = scanner.nextLine();
        System.out.println(input.length());
        while (input.length() != 1 || !Character.isAlphabetic(input.charAt(0))) {
            System.out.println("Invalid input. Please try again");
            input = scanner.nextLine();
        }

        char ch = input.charAt(0);
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char)(ch + 32);
        }

        return ch;
    }

    public void replaceFoundCharacter(char[] guessWord, String word, char ch) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ch) {
                guessWord[i] = ch;
            }
        }
    }

    public boolean checkUserGuessInput(String word, char input) {
        return word.contains(Character.toString(input));
    }

    public String getRandomWord() {
        String[] dictionary = {"government", "conversation", "condition", "preference", "decision", "daughter", "behavior",
                "damage", "platform", "connection", "solution", "hearing", "flight", "interest", "administration",
                "revenue", "priority", "profession", "driver"};

        return dictionary[random.nextInt(dictionary.length)];
    }

}
