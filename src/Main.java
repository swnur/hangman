/*
    HangMan Project
    1. Start new game or exit
    2. Player starts guessing random word from dictionary of nouns
    3. Input from player, and checking the game state
    4. After finishing with game, go to step 1.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int TOTAL_ATTEMPTS = 7;

    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // startGameLoop();

        String result = "A" + ('\t' + '\u0003');
        System.out.println(result);
    }

    public static void startGameLoop() {
        System.out.println("\tWelcome to HangMan Game!");

        do {
            System.out.print("\nEnter [S] to start game or [E] to exit: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("e")) {
                break;
            } else if (input.equals("s")) {
                startGameRound();
            } else {
                System.out.println("Invalid input. Please try again to enter [S] to star or [E] to end.");
            }
        } while (true);
    }

    public static void startGameRound() {
        // it is known that the result of function is all lowercase letters
        String word = getRandomWord();
        char[] guessWord = new char[word.length()];
        Arrays.fill(guessWord, '_');

        ArrayList<Character> listOfErrors = new ArrayList<>();
        int countOfErrors = 0;

        do {
            System.out.println(getHangManState(countOfErrors));

            System.out.println(getCurrentState(guessWord, listOfErrors));

            System.out.println();
            char inputCharacter = getInputFromPlayer();
            boolean isCorrectGuess = checkUserInputGuess(word, inputCharacter);

            if (isCorrectGuess) {
                replaceFoundCharacter(guessWord, word, inputCharacter);
                if (word.equals(new String(guessWord))) {
                    System.out.println("You won the game!!!");
                    System.out.println("The correct word is " + word);
                    break;
                }
            } else {
                countOfErrors += 1;
                if (countOfErrors == TOTAL_ATTEMPTS) {
                    System.out.println("\nUnfortunately you lost the game!");
                    System.out.println("Better luck next time!");
                    System.out.println("The word was: " + word);
                    break;
                } else {
                    listOfErrors.add(inputCharacter);
                }
            }
        } while (true);
    }

    public static void replaceFoundCharacter(char[] guessWord, String word, char ch) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ch) {
                guessWord[i] = ch;
            }
        }
    }

    public static boolean checkUserInputGuess(String word, char input) {
        return word.contains(Character.toString(input));
    }

    public static String getCurrentState(char[] guessWord, ArrayList<Character> listOfErrors) {
        StringBuilder output = new StringBuilder();
        output.append("Word: ");

        for (char character: guessWord) {
            output.append(character);
        }

        output.append("\nErrors: ");

        for(char character: listOfErrors) {
            output.append(character).append(" ");
        }

        output.append("\nTotal attempts left ").append(TOTAL_ATTEMPTS - listOfErrors.size());

        return output.toString();
    }

    public static char getInputFromPlayer() {
        System.out.print("Enter letter from (a-z) or (A-Z): ");
        char input = scanner.nextLine().charAt(0);

        while (!Character.isAlphabetic(input)) {
            System.out.println("Invalid input. Please try again");
            input = scanner.nextLine().charAt(0);
        }

        if (input >= 'A' && input <= 'Z') {
            input = (char)(input + 32);
        }

        return input;
    }

    public static String getRandomWord() {
        String[] dictionary = {"government", "conversation", "condition", "preference", "decision", "daughter", "behavior",
                                "damage", "platform", "connection", "solution", "hearing", "flight", "interest", "administration",
                                "revenue", "priority", "profession", "driver"};

        return dictionary[random.nextInt(dictionary.length)];
    }

    public static String getHangManState(int state) {
        return switch(state) {
            case 0 -> """
                        +---+
                        |   |
                            |
                            |
                            |
                            |
                    ===========""";
            case 1 -> """
                        +---+
                        |   |
                        O   |
                            |
                            |
                            |
                    ===========""";
            case 2 -> """
                        +---+
                        |   |
                        O   |
                        |   |
                            |
                            |
                    ===========""";
            case 3 -> """
                        +---+
                        |   |
                        O   |
                       /|   |
                            |
                            |
                    ===========""";
            case 4 -> """
                        +---+
                        |   |
                        O   |
                       /|\\  |
                            |
                            |
                    ===========""";
            case 5 -> """
                        +---+
                        |   |
                        O   |
                       /|\\  |
                       /    |
                            |
                    ===========""";
            case 6 -> """
                        +---+
                        |   |
                        O   |
                       /|\\  |
                       / \\  |
                            |
                    ===========""";
            default -> throw new IllegalStateException("Unexpected value: " + state);
        };
    }

}