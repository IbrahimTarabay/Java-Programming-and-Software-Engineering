
/**
 * Write a description of MakrovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    
    
    public MarkovWordTwo(){
     myRandom = new Random();
    }
    
    
    public void setRandom(int seed){
    
     myRandom = new Random(seed);
    
    }
    
    
    public void setTraining(String text){
    
    myText = text.split("\\s+");
    }
    
    
    
    public String getRandomText(int numWords){
    
     StringBuilder sb = new StringBuilder();
     //Random word to start
     int index = myRandom.nextInt(myText.length-2);
     String key1 = myText[index];
     String key2 = myText[index+1];
     
     sb.append(key1);
     sb.append(" ");
     sb.append(key2);
     sb.append(" ");
     
     for(int k =0;k<numWords-1;k++){
        ArrayList<String> follows = getFollows(key1,key2);
        if(follows.size() == 0){
         break;
        }
       
        
        index = myRandom.nextInt(follows.size());
        String next = follows.get(index);
        sb.append(next);
        sb.append(" ");
        
        key1 = key2;
        key2 = next;
        
     }
     
      return sb.toString().trim();
    }
    
    private int indexOf(String[] words,String target1,
    String target2,int start){
    
     int index = -1;
     for(int k=start;k<words.length-1;k++){
        if(words[k].equals(target1) && words[k+1].equals(target2)){
        
         return k;
        
        }
     }
     return index;
    }
    
    
    private ArrayList<String> getFollows(String key1,String key2){
    
     ArrayList<String> follows = new ArrayList<String>();
     
     int idx = 0;
     
     while(true){
        
        idx = indexOf(myText,key1,key2,idx);
        
        if(idx == myText.length-2 || idx == -1){
          break;
        }
        
        follows.add(myText[idx+2]);
        idx++;
     }
    
      return follows;
   }
}