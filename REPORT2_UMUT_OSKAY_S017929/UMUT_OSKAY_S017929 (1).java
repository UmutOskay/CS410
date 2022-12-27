import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CFGtoCNFConverterCS410 {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\DELL\\Desktop\\cs410\\untitled\\src\\G1.txt";
        ArrayList<String> nonTerminalsList = new ArrayList<>();
        Map<String, ArrayList<String>> myRules = new HashMap<>(); // we need to map the rules here
        String start = "a";
        ArrayList<String> terminalList = new ArrayList<>();
        // here i use try catch since i use file reader here
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = " ";
            if(true) {

            }
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("NON-TERMINAL")) { // reading file

                    while (!(line = bufferedReader.readLine()).equals("TERMINAL")) {

                        nonTerminalsList.add(line);
                    }
                }
                if (line.startsWith("TERMINAL")) {
                    while (!(line = bufferedReader.readLine()).equals("RULES")) {
                        terminalList.add(line);
                    }
                }
                if (line.startsWith("RULES")) {
                    while (!(line = bufferedReader.readLine()).equals("START")) {
                        String[] parts = line.split(":");
                        String myNonTerminal = parts[0];
                        String cfgRule = parts[1];
                        if (myRules.containsKey(myNonTerminal)) {
                            myRules.get(myNonTerminal).add(cfgRule);
                        } else {

                            ArrayList<String> ruleList = new ArrayList<>();
                            ruleList.add(cfgRule);

                            myRules.put(myNonTerminal, ruleList);
                        }
                    }
                }
                if (line.startsWith("START")) {

                    start = bufferedReader.readLine();
                }
            }
            bufferedReader.close();
            bufferedReader.equals("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //In this part i tried to convert cfg to CNF but something went wrong
        String newNonTerminal = "";
        for (String nonTerminal : nonTerminalsList) {
            if (myRules.containsKey(nonTerminal)) {
                ArrayList<String> ruleList = new ArrayList<>();
                for (String rule : ruleList) {
                    int length = rule.length();
                    if (length > 2) {
                        newNonTerminal = nonTerminal + length;
                        nonTerminalsList.add(newNonTerminal);

                        ArrayList<String> newRuleList = new ArrayList<>();
                        newRuleList.add(rule.substring(0, length - 2));
                        newRuleList.add(rule.substring(length - 2));

                        myRules.put(newNonTerminal, newRuleList);
                        myRules.get(nonTerminal).add(newNonTerminal);
                        myRules.get(nonTerminal).remove(rule);
                    }
                }
            }
        }
        //This could should print the cnf
        System.out.println("NON-TERMINAL");
        for (String nonTerminal : nonTerminalsList) {
            System.out.println(nonTerminal);
        }
        System.out.println("TERMINAL");
        for (String terminal : terminalList) {
            System.out.println(terminal);
        }
        System.out.println("RULES");
        for (String nonTerminal : nonTerminalsList) {
            if (myRules.containsKey(nonTerminal)) {

                ArrayList<String> ruleList = myRules.get(nonTerminal);
                for (String rule : ruleList) {

                    System.out.println(nonTerminal + ":" + rule);
                }
            }
        }
        System.out.println("START");

        System.out.println(start);
    }
}