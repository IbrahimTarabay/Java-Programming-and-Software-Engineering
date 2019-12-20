import edu.duke.*;
import java.util.*;

public class Tester {
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows);
    }
    
    public void testGetFollowsWithFile(){
        MarkovModel markov = new MarkovModel(2);
        FileResource fr = new FileResource();
        
        String frStr = fr.asString().replace('\n', ' ');
        markov.setTraining(frStr);
        ArrayList<String> follows = markov.getFollows("th");
        System.out.println(follows);
        System.out.println(follows.size());
        
    }
}
