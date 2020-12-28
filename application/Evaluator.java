package application;

import java.util.List;
import java.util.Map;

public class Evaluator {
    
    private String text;
    private Map<Integer, Map<String, List<Integer>>> diagram;
    private List<String> possibleChars;
    private List<Integer> finalStates;


    public void setState(String text, Map<Integer, Map<String, List<Integer>>> diagram, List<String> possibleChars, List<Integer> finalStates) {
        this.text = text;
        this.diagram = diagram;
        this.possibleChars = possibleChars;
        this.finalStates = finalStates;
    }

    public boolean run(){
        // upotreba lokalne funkcije da se ne moraju zadavati parametri run funkciji
        return evaluate(0, text, 0);
    }

    
    private boolean evaluate(Integer state, String text, Integer spacing){

        for (int i=0; i<spacing; i++) {
            System.out.print(' ');
        }
        System.out.println(state + ", " + text);


        if (text.isEmpty()) 
            return finalStates.contains(state);
        else {
            String nextChar = String.valueOf(text.charAt(0));

            if (!possibleChars.contains(nextChar)) 
                nextChar = "*";

            // ako postoji prijelaz za tekuce stanje i znak
            if (diagram.containsKey(state) && diagram.get(state).containsKey(nextChar)) {
                                                                                        
                // za sva stanja u koja se moze ici za tekuci znak (NFA)
                for (int i=0; i < diagram.get(state).get(nextChar).size(); i++) {

                    if ( evaluate(diagram.get(state).get(nextChar).get(i), text.substring(1), spacing + 4) )
                        return true;
                }
            }

            return false;
        }
    }


}
