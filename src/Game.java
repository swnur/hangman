
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private final GameRenderer renderer;
    private final WordService wordService;

    public Game() {
        renderer = new GameRenderer();
        wordService = new WordService();
    }

    public void start() {
        System.out.println("\tWelcome to Hangman Game!");

        do {
            System.out.print("\nEnter [S] to start game or [E] to exit: ");
            String input = wordService.getUserStartInput();

            if (input.equals("e")) {
                System.exit(0);
            } else if (input.equals("s")) {
                startRound();
            } else {
                System.out.println("Invalid input. Please try again to enter [S] to start or [E] to end.");
            }
        } while (true);
    }

    private void startRound() {
        final int TOTAL_ATTEMPTS = 7;
        String word = wordService.getRandomWord();
        char[] guessWord = new char[word.length()];
        Arrays.fill(guessWord, '_');

        Set<Character> listOfErrors = new HashSet<>();
        int countOfErrors = 0;

        do {
            renderer.print(guessWord, listOfErrors, countOfErrors, TOTAL_ATTEMPTS);

            char inputCharacter = wordService.getUserGuessInput();
            boolean isCorrectGuess = wordService.checkUserGuessInput(word, inputCharacter);

            if (isCorrectGuess) {
                wordService.replaceFoundCharacter(guessWord, word, inputCharacter);
                if (word.equals(new String(guessWord))) {
                    System.out.println("You won the game!!!");
                    System.out.println("The correct word is " + word);
                    return;
                }
            } else {
                if (!listOfErrors.contains(inputCharacter)) {
                    countOfErrors += 1;
                    listOfErrors.add(inputCharacter);
                }
                if (countOfErrors == TOTAL_ATTEMPTS) {
                    System.out.println("\nUnfortunately you lost the game!");
                    System.out.println("Better luck next time!");
                    System.out.println("The word was: " + word);
                    return;
                }
            }
        } while (true);
    }
}
