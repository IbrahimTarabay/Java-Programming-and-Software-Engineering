
/**
 * Write a description of VigenereBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;


public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String sliced = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sliced += message.charAt(i);
        }
        return sliced;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker ccr = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String m = sliceString(encrypted, i, klength);
            int k = ccr.getKey(m);
            key[i] = k;
        }
        return key;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String message = fr.asString().toLowerCase();

        FileResource frd = new FileResource();
        HashSet<String> dictionary = readDictionary(frd);

        String decrypt = breakForLanguage(message, dictionary);

        System.out.println("Valid words are in the decrypted String is " + countWords(decrypt,dictionary));
        System.out.println(decrypt);

        String dec38 = new VigenereCipher(tryKeyLength(message,38,'e')).decrypt(message);
        System.out.println("Valid words are in the \"decrypted\" is " + countWords(dec38,dictionary));

    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> d = new HashSet<>();
        for (String l : fr.lines()) {
            d.add(l.toLowerCase());
        }
        return d;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] messageArray = message.split("\\W+");
        int count = 0;


        for (String word : messageArray) {
            if (dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int max = 0;
        String d = "";
        int keyLong = 0;
        char mostCommonChar =  mostCommonCharIn(dictionary);
        
        
        for (int i = 1; i < 100; i++) {
            int[] key;
            
            key = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            if (countWords(decrypted, dictionary) > max) {
                max = countWords(decrypted, dictionary);
                d = decrypted;
                keyLong = i;
            }
        }
        System.out.println("key length used to encrypt is " + keyLong);
        return d;
    }
    
    
    public char mostCommonCharIn(HashSet<String> dictionary){
    
     HashMap<Character, Integer> count = new HashMap<>();
     char mostCommonChar = 'a';
     int max = 0;
     
     for(String word : dictionary){
        
        for(int i = 0; i<word.length();i++){
          if(count.containsKey(word.charAt(i))){
            
             count.put(word.charAt(i), count.get(word.charAt(i))+1);
            
            }
            else{
            
             count.put(word.charAt(i),1);
            
            }
        
        }
        
     }
    
     for(Character character : count.keySet()){
        if(count.get(character) > max){
        
         max = count.get(character);
         mostCommonChar = character;
        
        }
          
      }
    
     System.out.println("The most common char is " + mostCommonChar);
     
     return mostCommonChar;
    
    }
    
    
    
    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages){
    
    for(String languageName : languages.keySet()){
     
        System.out.println("This is "+ languageName);
        String decrypted = breakForLanguage(encrypted, languages.get(languageName));
        System.out.println(decrypted);
    }
    
   }
    
    
}



















































/*public class VigenereBreaker {

  public String sliceString(String message,int whichSlice, int totalSlices){
    
      String sliced = "";
      for(int i=whichSlice;i<message.length(); i +=totalSlices){
        
         char letter = message.charAt(i);
         sliced = sliced + letter;
        
        }
    
      return sliced;
    }  
    
    
  
  public int[] tryKeyLength(String encrypted, int numOfKeys, char mostCommon){
    
    int[] key = new int[numOfKeys];
    
    for(int i = 0; i<numOfKeys; i++){
    
     String sliced = sliceString(encrypted,i,numOfKeys);
     CaesarCracker cc = new CaesarCracker(mostCommon);
     key[i] = cc.getKey(sliced);
    
    }
    
    return key;  
  }
    
    
    public void testSliceString(){
        System.out.println(sliceString("abcdefghijklm", 0, 3));
        System.out.println(sliceString("abcdefghijklm", 1, 3));
        System.out.println(sliceString("abcdefghijklm", 2, 3));
        System.out.println(sliceString("abcdefghijklm", 0, 4));
        System.out.println(sliceString("abcdefghijklm", 1, 4));
        System.out.println(sliceString("abcdefghijklm", 2, 4));
        System.out.println(sliceString("abcdefghijklm", 3, 4));
        System.out.println(sliceString("abcdefghijklm", 0, 5));
        System.out.println(sliceString("abcdefghijklm", 1, 5));
        System.out.println(sliceString("abcdefghijklm", 2, 5));
        System.out.println(sliceString("abcdefghijklm", 3, 5));
        System.out.println(sliceString("abcdefghijklm", 4, 5));
    } 
    
   
    
   public void testTryKeyLength(){
    
     FileResource fr = new FileResource();
     String message = fr.asString();
     
     //int numOfKeys = "flute".length();
    
     int numOfKeys = 4;
     int[] key = tryKeyLength(message,numOfKeys,'e');
     
     for(int i = 0;i<key.length;i++){
        
        System.out.println(key[i]);
       }
    
    } 
    
    
   public void testbreakVigenere(){
    
    System.out.println("--------------------");
    /*FileResource fr = new FileResource();
    String message = fr.asString();*/
    
    //int[] key = tryKeyLength(message,4,'e');
    /*int[] key ={3,20,10,4};
    VigenereCipher vc = new VigenereCipher(key);
    String decrypted = vc.decrypt("Hhdiu LVXNEW uxh WKWVCEW, krg k wbbsqa si Mmwcjiqm");
    System.out.println(decrypted);
    
    } 
      








  /*unkonwn length key*/
 /*public HashSet<String> readDictionary(FileResource fr){
 
 //make a new HashSet of Strings
 HashSet<String> dictionaryWords = new HashSet<>();
 //read each line in fr

   for(String line : fr.lines()){
    
     line = line.toLowerCase();
    //put that line into the HashSet that you created
    
    dictionaryWords.add(line);
    
    }
  return dictionaryWords;
 }

 
 
 public int countWords(String message, HashSet<String> dictionary){
    
    int realWordNums = 0;
    //split the message into words
    String[] words = message.split("\\W");//hahahahah bug w small is not true it must be capital
    //iterate over those words
    for(String word: words){
     //see how many of those words are "real words"
     word = word.toLowerCase();
     if(dictionary.contains(word)){
        
        realWordNums++;
        
        }
    
     }
    return realWordNums;
   }
 
 
   public String breakForLanguage(String encrypted,HashSet<String> dictionary){
    
    
    int maxRealWords = 0;
    int finalKeyLength = 1;//number of keys in key array
    String decryptionWithMostRealWords = "";
    
    //try all key lengths from 1 to 100
    
    for(int keyLength=1; keyLength<=100;keyLength++){
    
     //decrypt message
     int[] key = tryKeyLength(encrypted,keyLength,'e');//we suppose the message in english
     VigenereCipher vc = new VigenereCipher(key);
     String decrypted = vc.decrypt(encrypted);
     //count how many of the words in it are real words in english
     int realWords = countWords(decrypted,dictionary);
     if(realWords > maxRealWords){
        
        maxRealWords = realWords;
        decryptionWithMostRealWords = decrypted;
        finalKeyLength = keyLength;
        }
    }
     
     //return decryptionWithMostRealWords; 
     return decryptionWithMostRealWords;
   }
   
   
   
   
    public void testCountWords(){
         // create a new FileResource
      
       FileResource fr = new FileResource();
       // Use that FileResource's .asString()
       String message = fr.asString();
       // make a new FileResource to read from the English dictionary
       FileResource dicFile = new FileResource("English.txt");
       
       /* use readDictionary method to read the
       contents of that dicFile into a HashSet of Strings*/
      /* HashSet<String> dictionary = readDictionary(dicFile);
       int realWordNums = countWords(message,dictionary);
       System.out.println("realWordNums " + realWordNums);
    }
 
 
 
    
   public void testBreakForLanguage(){
       /*FileResource fr = new FileResource();
       String message = fr.asString();*/
       /*FileResource dicFile = new FileResource("English.txt");
       HashSet<String> dictionary = readDictionary(dicFile);
       //breakForLanguage(message,dictionary);
       String message = "Muo Gffmdxq au Aazaii, Irherv ed OnasuzsXSZ D: ESUJK D. Rvdvbtxd. U euttsdvb qxfnit bxc njfzfm.CHGKXMVOE wz ccl zzfh. Dhlyd ch uxq VTKNZISFRCKYJEJIEcc'i qciuq?BXKIWBFMZBfe, shelnk zt: mitnc, rvt nyobrx gjioikia.";

       String decryptionWithMostRealWords = breakForLanguage(message,dictionary);
       System.out.println("decryptionWithMostRealWords:" + decryptionWithMostRealWords);
        
       
       
       /*FileResource fr = new FileResource();
        String message = fr.asString().toLowerCase();

        FileResource frd = new FileResource();
        HashSet<String> dictionary = readDictionary(frd);

        String decrypt = breakForLanguage(message, dictionary);

        System.out.println("Valid words are in the decrypted String is " + countWords(decrypt,dictionary));
        System.out.println(decrypt);

        String dec38 = new VigenereCipher(tryKeyLength(message,38,'e')).decrypt(message);
        System.out.println("Valid words are in the \"decrypted\" is " + countWords(dec38,dictionary));*/
    /*}
    
    
    
    
    
    
 
 
 
 

}*/


















