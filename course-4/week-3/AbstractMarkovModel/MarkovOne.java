
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovOne extends AbstractMarkovModel {
    
 
  
  
  public MarkovOne(){
     myRandom = new Random();
     order =1;
    }
    
    
  public void setRandom(int seed){
    
     myRandom = new Random(seed);
    }  
    
 /* public void setTraining(String s){
    
     myText = s.trim(); //to remove spaces before and the end of text
    }  */
    
  public String getRandomText(int numChars){
     if(myText == null){
        return "";
        }
        
     StringBuilder sb = new StringBuilder();   
     int index = myRandom.nextInt(myText.length()-1);
     //seeded by myText.length()-1 because we need one follow after the key
     String key = myText.substring(index,index+1);
     sb.append(key);   
     
     for(int k=0; k<numChars;k++){
        ArrayList<String> follows = getFollows(key);
        
        if(follows.size() == 0){
        
          break;
        }
        
        index = myRandom.nextInt(follows.size());
        String successor = follows.get(index);
        sb.append(successor);
        key = successor;
        
        }
          
       return sb.toString(); 
    }  
    
}
