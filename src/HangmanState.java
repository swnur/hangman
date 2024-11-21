public enum HangmanState {

    ZERO("""
                    +---+
                    |   |
                        |
                        |
                        |
                        |
                ==========="""
    ),
    ONE("""
                +---+
                |   |
                O   |
                    |
                    |
                    |
            ==========="""
    ),
    TWO("""
                +---+
                |   |
                O   |
                |   |
                    |
                    |
            ==========="""),
    THREE("""
                +---+
                |   |
                O   |
               /|   |
                    |
                    |
            ==========="""),
    FOUR("""
                +---+
                |   |
                O   |
               /|\\  |
                    |
                    |
            ==========="""),
    FIVE("""
                +---+
                |   |
                O   |
               /|\\  |
               /    |
                    |
            ==========="""),
    SIX("""
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
                    |
            ===========""");


    private final String scaffold;

    HangmanState(String scaffold) {
        this.scaffold = scaffold;
    }

    public String getScaffold() {
        return scaffold;
    }
}
