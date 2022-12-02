import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\DELL\\Desktop\\cs410\\NFA1.txt");
        Scanner sc = new Scanner(file);
        NFA nfa = new NFA();

        List<String> alphabet = nfa.getAlphabet();
        List<State> Q = nfa.getQ();
        State startState = nfa.getStartState();
        State finalState = nfa.getFinalState();
        State stateIn = new State();
        State stateOut = new State();
        List<Transition> transitions = new ArrayList<>();
        Transition transition;

        String str = "";

        while(sc.hasNextLine()) {
            str += sc.nextLine();
            str += "\n";
        }

        String[] str2 = str.split("\n");

        int statesIndex = 0;
        int startIndex = 0;
        int finalIndex = 0;
        int transitionsIndex = 0;
        int endIndex = 0;

        for(int i = 0; i < str2.length;i++) {
            if(str2[i].equals("STATES"))
                statesIndex = i;
            if(str2[i].equals("START"))
                startIndex = i;
            if(str2[i].equals("FINAL"))
                finalIndex = i;
            if(str2[i].equals("TRANSITIONS"))
                transitionsIndex = i;
            if(str2[i].equals("END"))
                endIndex = i;
        }

        for(int i = 0; i < str2.length; i++) {
            if(i == statesIndex - 1)
                break;
            alphabet.add(str2[i+1]);
        }

        for(int i = statesIndex + 1; i < str2.length; i++) {
            if(i == startIndex)
                break;
            State state = new State(str2[i]);
            Q.add(state);

        }

        for(int i = startIndex + 1; i< str2.length; i++) {
            if(i == finalIndex)
                break;
            startState = new State(str2[i]);
        }

        for(int i = finalIndex + 1; i< str2.length; i++) {
            if(i == transitionsIndex)
                break;
            finalState = new State(str2[i]);
        }

        for(int i = transitionsIndex + 1; i < str2.length; i++) {
            if(i == endIndex)
                break;
            stateIn = new State(Character. toString(str2[i].charAt(0)));
            stateOut = new State(Character. toString(str2[i].charAt(4)));
            transition = new Transition(stateIn,str2[i].charAt(2),stateOut);
            transitions.add(transition);
        }
        System.out.println("Alphabet = " + alphabet);
        System.out.println("Q = " + Q);
        System.out.println("q0 = " + startState);
        System.out.println("qF = " + finalState);
        System.out.println("Transitions = " + transitions);

        //Setting operations
        nfa.setQ(Q);
        nfa.setAlphabet(alphabet);
        nfa.setStartState(startState);
        nfa.setFinalState(finalState);

        HashMap<String,List<String>> hashMap = new HashMap<String, List<String>>();

        for(Transition t: transitions) {
            String inputState = t.getStateIn().getName();
            char alph = t.getAlphabet();
            String outputState = t.getStateOut().getName();
            if(!hashMap.containsKey(inputState+alph)){
                List<String> outputList = new ArrayList<>();
                outputList.add(outputState);
                hashMap.put(inputState+alph,outputList);
            }
            else{
                List<String> tempList = hashMap.get(inputState+alph);
                tempList.add(outputState);
                hashMap.put(inputState+alph,tempList);
            }
        }
        HashMap<String,List<String>> dfa = new HashMap<>();
        dfa.put("A0",hashMap.get("A0"));
        dfa.put("A1",hashMap.get("A1"));

        while(true){
            boolean notAllVisited = true;
            for(int i = 0; i < dfa.size();i++ ) { // dfa in bütün elemanlarını gezmek için // A1 A0 A BAKCAK
                List<String> outputsOfHashMapPerIteration = dfa.get(dfa.keySet().toArray()[i]);
                int length = outputsOfHashMapPerIteration.size();
        //        System.out.println("iteration " + i + ": ");
                String newInputState = "";

                List<String> outputStatesZero = new ArrayList<String>();
                List<String> outputStatesOne = new ArrayList<String>();
                for(int j = 0; j < length;j++) { // herhangi bir cevap için onun // A1 İÇİN BC
                    newInputState = outputsOfHashMapPerIteration.get(j);
                    if(hashMap.containsKey(newInputState + "0"))
                        outputStatesZero.add(String.join("", hashMap.get(newInputState + "0")));
                    else
                        outputStatesZero.add("");

                        // b1 burda var mı kontrol et
                    if(hashMap.containsKey(newInputState + "1"))
                        outputStatesOne.add(String.join("", hashMap.get(newInputState + "1")));
                    else
                        outputStatesOne.add("");
            }

                if(!dfa.containsKey(String.join("",outputStatesZero)+"0")) {
                    dfa.put(String.join("",outputStatesZero) + "0", outputStatesZero);
                }
                else notAllVisited = false;

                if(!dfa.containsKey(String.join("",outputStatesOne) +"1")) {
                    dfa.put(String.join("",outputStatesOne) + "1", outputStatesOne);
                }
                else notAllVisited = false;
            }
            if(!notAllVisited)
            break;
            }
        System.out.println("DFA: " + dfa);
        }
    }
