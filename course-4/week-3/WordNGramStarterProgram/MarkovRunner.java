
/**
 * Write a description of MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {

  public void runModel(IMarkovModel markov, String text, int size){
    
    markov.setTraining(text); 
    System.out.println("running with "+markov);
    
    for(int k=0; k<3;k++){
    
      String st = markov.getRandomText(size);
      printOut(st);
    }
 
  }  
    
    
  public void runModel(IMarkovModel markov, String text, int size, int seed){ 
     markov.setTraining(text); 
     markov.setRandom(seed);
     //System.out.println("running with " + markov); 
     for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
     } 
    } 
  
    
  public void runMarkovOne(){
    
   FileResource fr = new FileResource();  
   String st = fr.asString(); 
   st = st.replace('\n',' '); 
    
   MarkovWordOne markovWord = new MarkovWordOne(); 
   runModel(markovWord,st,100,139); 
      
  }  
  
  
  
  private void printOut(String s){
    String[] words = s.split("\\s+");
    int psize = 0;
    System.out.println("----------------------------------");
     for(int k=0; k < words.length; k++){
        System.out.print(words[k]+ " ");
        psize += words[k].length() + 1;
        if (psize > 60) {
                System.out.println(); 
                psize = 0;
        } 
      } 
        System.out.println("\n----------------------------------");
    }
  
  
  
  
      public void testGetFollows(){
        String st = "this is just a test yes this is a simple test";
        MarkovWordOne markovwordone = new MarkovWordOne();
        runModel(markovwordone,st,13);
    }
    
    public void runMarkovTwo(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n',' ');
        MarkovWordTwo markovWordTwo = new MarkovWordTwo(); 

        // runModel(markovWordTwo, st, 20, 549);
        //quiz int seed = 832
        runModel(markovWordTwo, st, 120, 832);

    }  
    
    
    
    
    
    
    
    
    
    
    
    
}
