package container;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import application.Main;


public class Core {

    private static List<InitializationListener> listeners = new ArrayList<>();
    
    private static String location;
    private static Map<String, Object> objects = new HashMap<>();

    public static void main(String[] args) {

        location = args[0];

        try {
            instantiate();
            
            listeners.add(((Main) getObject("main")));

            notifyInitializationFinished();  

            //((Main) getObject("main")).launch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private static void notifyInitializationFinished() {
        for (InitializationListener listener : listeners)
            listener.launch();
    }


    private static void instantiate() throws FileNotFoundException, IOException {
        Properties prop = new Properties();

        prop.load(new FileInputStream(location));

        prop.forEach((objectName, className) -> {
            try {
                objects.put(objectName.toString(), Class.forName(className.toString()).getDeclaredConstructor().newInstance());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        });
    }

    public static Object getObject(String name){
        return objects.get(name);
    }
    
}