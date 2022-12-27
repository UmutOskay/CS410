import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Turing {
    private int head;
    private char[] tape;
    private Map<String, Map<Character, Transition>> transitions; //transition functions of the Turing machine
    private String state;
    private String[] rejectStates;
    private String[] acceptStates;
    private List<String> route;

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    public Turing(char[] tape, String startState, Map<String, Map<Character, Transition>> transitions, String[] acceptStates, String[] rejectStates) {
        this.tape = tape;
        this.head = 0;
        this.state = startState;
        this.transitions = transitions;
        this.acceptStates = acceptStates;
        this.rejectStates = rejectStates;
    }

    public boolean isInRejectState() {
        for (String rejectState : rejectStates) {
            if (state.equals(rejectState)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInAcceptState() {
        for (String acceptState : acceptStates) {
            if (state.equals(acceptState)) {
                return true;
            }
        }
        return false;
    }





    public boolean isAtLeftmostPosition() {
        return head == 0; // returns true if the head is at the leftmost position
    }


    public boolean isAtRightmostPosition() {
        return head == tape.length - 1; // returns true if the head is at the rightmost position
    }


    public void moveHeadLeft() { // moves the head one position to the left
        if (!isAtLeftmostPosition()) {
            head--;
        }
    }


    public void moveHeadRight() {  // moves the head one position to the right
        if (!isAtRightmostPosition()) {
            head++;
        }
    }


    public void writeSymbol(char symbol) { //  writes the given symbol to the the tape
        tape[head] = symbol;
    }

    public void run() {
        route = new ArrayList<>();

        String previousState = "";
        route.add(Main.startState);
        boolean isStuck = false; // it is how i check if its in a loop or not
        // im running the Turing machine until it reaches an accept or reject state, or gets stuck in a loop
        while (!isInAcceptState() && !isInRejectState() && !isStuck) {

            char symbol = tape[head]; // current symbol

            Transition transition = transitions.get(state).get(symbol); // get the transition function for the current state and symbol


            state = transition.getNextState(); // update the state of the Turing machine
            route.add(state);
            writeSymbol(transition.getWriteSymbol());  // write the symbol to the tape

            if (transition.getMoveDirection() == Direction.LEFT) { // move the head in the wanted direction
                moveHeadLeft();
            } else if (transition.getMoveDirection() == Direction.RIGHT) {
                moveHeadRight();
            } // check if the Turing machine is stuck in a loop


            if (state.equals(previousState)) {
                isStuck = true;
            }

            previousState = state; // update the previous state
        }

    }
}