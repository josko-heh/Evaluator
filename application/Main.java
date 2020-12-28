package application;

import container.Core;
import container.InitializationListener;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main implements InitializationListener {

    private String text = "aadaab";
    private Map<Integer, Map<String, List<Integer>>> transitionDiagram = Map.of(
        0, Map.of("a", Arrays.asList(0,1), "b", Arrays.asList(0)),
        1, Map.of("a", Arrays.asList(1,2), "b", Arrays.asList(1)),
        2, Map.of("a", Arrays.asList(2), "b", Arrays.asList(2,3), "*", Arrays.asList(0))        
    );


    @Override
    public void launch() {
        System.out.println("Input string: " + text);

        Evaluator evaluator = (Evaluator) Core.getObject("evaluator");
        evaluator.setState(text, transitionDiagram, Arrays.asList("a","b"), Arrays.asList(3));

        boolean result = evaluator.run();

        System.out.println(result);
    }


}
