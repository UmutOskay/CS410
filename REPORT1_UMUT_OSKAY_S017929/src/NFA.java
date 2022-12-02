import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NFA {
    private List<State> Q = new ArrayList<State>();// Sets of state
    private List<String> alphabet = new ArrayList<String>(); // 0,1
    private State startState; // q0
    private State finalState; // qFinal

    public List<State> getQ() {
        return Q;
    }

    public void setQ(List<State> q) {
        Q = q;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(List<String> alphabet) {
        this.alphabet = alphabet;
    }

    public State getStartState() {
        return startState;
    }

    public void setStartState(State startState) {
        this.startState = startState;
    }

    public State getFinalState() {
        return finalState;
    }

    public void setFinalState(State finalState) {
        this.finalState = finalState;
    }

}
