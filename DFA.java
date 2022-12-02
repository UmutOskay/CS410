import java.util.ArrayList;
import java.util.List;

public class DFA {
    private List<State> Q = new ArrayList<State>();// Sets of state
    private List<String> alphabet = new ArrayList<String>(); // 0,1
    private List<State> startState; // q0
    private List<State> finalState; // qFinal

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

    public List<State> getStartState() {
        return startState;
    }

    public void setStartState(List<State> startState) {
        this.startState = startState;
    }

    public List<State> getFinalState() {
        return finalState;
    }

    public void setFinalState(List<State> finalState) {
        this.finalState = finalState;
    }
}
