import java.util.Set;

public class GameRenderer {

    public void print(char[] guessWord, Set<Character> listOfErrors, int countOfErrors, int totalAttempts) {
        System.out.println(hangmanState(countOfErrors, totalAttempts));
        System.out.println(currentState(guessWord, listOfErrors, totalAttempts));
    }

    private String currentState(char[] guessWord, Set<Character> listOfErrors, int totalAttempts) {
        StringBuilder output = new StringBuilder();
        output.append("Word: ");

        for (char character: guessWord) {
            output.append(character);
        }

        output.append("\nErrors [").append(listOfErrors.size()).append("]: ");

        for(char character: listOfErrors) {
            output.append(character).append(" ");
        }

        output.append("\nTotal attempts left ").append(totalAttempts - listOfErrors.size());

        return output.toString();
    }

    private String hangmanState(int state, int totalAttempts) {
        if (state < 0 && state > totalAttempts) {
            throw new IllegalArgumentException("Wrong state number");
        }
        return HangmanState.values()[state].getScaffold();
    }
}
