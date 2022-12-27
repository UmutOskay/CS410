public class Transition {

    private char writeSymbol; // the symbol to be written to the tape during the transition

    private Direction moveDirection; // the direction in which the head should move during the transition

    private String nextState; // the next state of the Turing machine after the transition


    public Transition(String nextState, char writeSymbol, Direction moveDirection) {
        this.nextState = nextState;
        this.writeSymbol = writeSymbol;
        this.moveDirection = moveDirection;
    }

    public char getWriteSymbol() {
        return writeSymbol;
    }

    public Direction getMoveDirection() {
        return moveDirection;
    }

    public String getNextState() {
        return nextState;
    }




}
