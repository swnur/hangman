/*
    HangMan Project
    1. Start new game or exit
    2. Player starts guessing random word from dictionary of nouns
    3. Input from player, and checking the game state
    4. After finishing with game, go to step 1.
 */;

public class Main {
    public static void main(String[] args) {
         Game game = new Game();
         game.start();
    }


}