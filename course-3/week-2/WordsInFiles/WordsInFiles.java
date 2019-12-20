
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.File;
import edu.duke.*;


public class WordsInFiles {
  
    private HashMap<String, ArrayList<String>> map;
    
    /*Initializes private variables*/
    
    public WordsInFiles(){
    
     map = new HashMap<String, ArrayList<String>>();
    }
    
    /*adds all words from file f into map*/
    
    private void addsWordsFromFile(File f){
     
        String filename = f.getName();
        //Access the file
        FileResource fr = new FileResource(f);
        
        for(String word: fr.words()){
          //if word is already in map and filename doesn't exist in its ArrayList, add filename
          //otherwise, map word to a new Arraylist<String> containing filename
          if(map.containsKey(word)){
            ArrayList<String> arr = map.get(word);
            if(!arr.contains(filename)){
             
                arr.add(filename);
            
            }
            
           }else{
            
             ArrayList<String> arr = new ArrayList<String>();
             arr.add(filename);
             map.put(word, arr);
            }
        }
    }
    
    /*put all of words from a group of user-selected files into the map by
       calling the method addsWordsFromFile*/
    
    private void buildWordFileMap(){
    
     map.clear();
     DirectoryResource dir = new DirectoryResource();
    
     for(File f : dir.selectedFiles()){
        
          addsWordsFromFile(f);
        }
    
    }
    
    
    
    
    /*Returns the maximum number of files any word appears in,
       considering all words from a group of files*/
    
    
    private int maxNumber(){
    
     int max = 0;
     //for each word
     for(String word : map.keySet()){
        //Get size of its mapped ArrayList
        int count = map.get(word).size();
        //if size is greater than max, update max
        if(count > max){
          max = count;
        }
        
       }
    
      return max;
    
    }
    
    /*Returns an ArrayList of words that appear in exactly number files*/
    
    private ArrayList<String> wordsInNumFiles(int number){
     int count = 0;
     
     ArrayList<String> words = new ArrayList<String>();
     for(String word : map.keySet()){
        //Get size of its mapped ArrayList
        int appearances = map.get(word).size();
        if(appearances == number){
        
         words.add(word);
         count++;
        }
             
     }
     System.out.println(count);
     return words;
   }
    
    /*prints the names of the files this word appears in, one filename per line*/
    private void printFilesln(String word){
    
     ArrayList<String> filenames = map.get(word);
     
     for(int i = 0; i<filenames.size();i++){
        
        System.out.println(filenames.get(i));
        
        }
    }
    
    
    
        public void tester() {
            
        buildWordFileMap();
        System.out.println(wordsInNumFiles(5));
        printFilesln("red");
        //int max = maxNumber();
        //System.out.println("Maximum number of files any word appears in: " + max);
        //System.out.println("All the words that are in " + max + " files:");
        // Determine all the words that are in the maximum number of files
        // and for each such word, print the filenames of the files it is in
        /*ArrayList<String> words = wordsInNumFiles(max);
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            //System.out.println("The word " + word + " appears in the following files: ");
            //printFilesln(word);
            
        }*/
        // Print out the complete map
        //System.out.println(map.toString());
        
        // To answer quiz 
        //System.out.println("Count of words that appear in " + max + " files: " + words.size());
        /*System.out.println("To answer the quiz: ");
        words = wordsInNumFiles(5);
        System.out.println("Words in 5 files: " + words.size());
        System.out.println("tree");
        printFilesln("tree");*/
        
       
    }
    
     
}
