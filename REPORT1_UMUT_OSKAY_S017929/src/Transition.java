public class Transition {
    private State stateIn;
    private char alphabet;
    private State stateOut;



    public Transition(State stateIn, char alphabet, State stateOut) { // A 1 A
        this.stateIn = stateIn;
        this.alphabet = alphabet;
        this.stateOut = stateOut;
    }
    public Transition() {}

    @Override
    public String toString() {
        return "" + stateIn + alphabet + stateOut;
    }

    public static Transition[] makeTransition (String[][] transitions) {
        Transition[] rows = new Transition[transitions.length];
        for(int i = 0; i < transitions.length;i++) {
            State stateIn = new State(transitions[i][0]);
            char alphabet = transitions[i][1].charAt(0);
            State stateOut = new State(transitions[i][2]);

            rows[i] = new Transition(stateIn,alphabet,stateOut);
        }
        return rows;
    }

    public State getStateIn() {
        return stateIn;
    }

    public void setStateIn(State stateIn) {
        this.stateIn = stateIn;
    }

    public State getStateOut() {
        return stateOut;
    }

    public void setStateOut(State stateOut) {
        this.stateOut = stateOut;
    }

    public char getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(char alphabet) {
        this.alphabet = alphabet;
    }
}
