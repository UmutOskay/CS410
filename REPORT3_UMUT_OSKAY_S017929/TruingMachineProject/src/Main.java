import java.util.HashMap;
import java.util.Map;

public class Main {
    static final String startState = "q1";
    public static void main(String[] args) {
        // define the tape of the Turing machine
        char[] tape = {'1', '0', '1', '0'};


        // define the transition functions of the Turing machine
        Map<String, Map<Character, Transition>> transitions = new HashMap<>();
        Map<Character, Transition> q1Transitions = new HashMap<>();
        q1Transitions.put('0', new Transition("q2", '1', Direction.RIGHT));
        q1Transitions.put('1', new Transition("q3", '1', Direction.RIGHT));
        transitions.put("q1", q1Transitions);
        Map<Character, Transition> q2Transitions = new HashMap<>();
        q2Transitions.put('0', new Transition("q2", '0', Direction.RIGHT));
        q2Transitions.put('1', new Transition("q2", '1', Direction.RIGHT));
        transitions.put("q2", q2Transitions);
        Map<Character, Transition> q3Transitions = new HashMap<>();
        q3Transitions.put('0', new Transition("q4", '1', Direction.LEFT));
        q3Transitions.put('1', new Transition("q5", '1', Direction.LEFT));
        transitions.put("q3", q3Transitions);
        Map<Character, Transition> q4Transitions = new HashMap<>();
        q4Transitions.put('0', new Transition("q4", '0', Direction.LEFT));
        q4Transitions.put('1', new Transition("q4", '1', Direction.LEFT));
        transitions.put("q4", q4Transitions);
        Map<Character, Transition> q5Transitions = new HashMap<>();
        q5Transitions.put('0', new Transition("q5", '1', Direction.LEFT));
        q5Transitions.put('1', new Transition("q6", '1', Direction.RIGHT));
        transitions.put("q5", q5Transitions);
        Map<Character, Transition> q6Transitions = new HashMap<>();
        q6Transitions.put('0', new Transition("q6", '0', Direction.RIGHT));
        q6Transitions.put('1', new Transition("q6", '1', Direction.RIGHT));
        transitions.put("q6", q6Transitions);
        // define the accept states of the Turing machine
        String[] acceptStates = {"q2","q4"};
        // define the reject states of the Turing machine
        String[] rejectStates = {"q5"};
        // create a new Turing machine
        Turing tm = new Turing(tape, startState, transitions, acceptStates, rejectStates);
        // run the Turing machine
        tm.run();

        // Print the ROUT and RESULT by using isInAcceptState function
        if (tm.isInAcceptState()) {
            System.out.print("ROUT: ");
            for(String str: tm.getRoute()) {
                System.out.print(str + " ");

            }
            System.out.println();
            System.out.println("RESULT: accepted");
        }
        else{
            System.out.print("ROUT: ");
            for(String str: tm.getRoute()) {
                System.out.print(str + " ");

            }
            System.out.println();
            System.out.println("RESULT: rejected");
        }
    }
}