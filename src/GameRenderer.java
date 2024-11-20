import java.util.Set;

public class GameRenderer {

    public void print(char[] guessWord, Set<Character> listOfErrors, int countOfErrors, int totalAttempts) {
        System.out.println(getHangmanState(countOfErrors, totalAttempts));
        System.out.println(getCurrentState(guessWord, listOfErrors, totalAttempts));
    }

    private String getCurrentState(char[] guessWord, Set<Character> listOfErrors, int totalAttempts) {
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

    private String getHangmanState(int state, int totalAttempts) {
        if (state < 0 && state > totalAttempts) {
            throw new RuntimeException("Invalid state was given as a parameter");
        }
        return HangmanState.values()[state].getScaffold();
    }
}
