
/**
 * Write a description of AbastractMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import java.util.*;


public abstract class AbstractMarkovModel implements IMarkovModel {
    
 protected String myText;   
 protected Random myRandom;   
 protected int order;   
    
    
 public AbstractMarkovModel(){
    
   myRandom = new Random();  
  }   
    
 public void setTraining(String s){
    
    myText = s.trim();
 }   
    
    
 abstract public String getRandomText(int numChars);   
    
 public ArrayList<String> getFollows(String key){
   ArrayList<String> follows = new ArrayList<String>(); 
   int pos = 0;
   
   while(true){
    
    int index = myText.indexOf(key, pos);
    int indexOfSuccessor = index + key.length();
    
    
    if(index == -1 || indexOfSuccessor >= myText.length()){
    
     break;
    
    }
    
    String successor = myText.substring(indexOfSuccessor,indexOfSuccessor+1);
    follows.add(successor);
    
    pos = index + key.length();
    
    }
    
   return follows;
 }   
    
    
    
 @Override
 public String toString(){
  return "MarkovModel of order "+order;
 }  
    
    

}
