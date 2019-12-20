
/**
 * Write a description of testCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;


public class testCaesarCipher {
  
    private int[] countLetters(String message){
    
      String alphabet = "abcdefghijklmnopqrstuvwxyz";
      int[] counts = new int[26];
      
      for(int i=0; i<message.length();i++){
        
          char ch = Character.toLowerCase(message.charAt(i));
          int index = alphabet.indexOf(ch);
          if(index != -1){
             
              counts[index] += 1;
            
            }
        
        }
    
        return counts;
    }
    
    
    public int maxIndex(int[] counts){
     
        int maxIndex = 0;
        for(int i=0;i<counts.length;i++){
          
            if(counts[i]>counts[maxIndex]){
              
                maxIndex = i;
            }
        
        }
    
       return maxIndex;
    }
    
    
    public void breakCaesarCipher(String input){
    
        int[] freqs = countLetters(input);
        int maxIndex = maxIndex(freqs);
        int dkey = maxIndex - 4;
        
        if(maxIndex < 4){
          dkey = 26 - (4-maxIndex);
        }
    
    
    
    OOCaesarCipher a = new OOCaesarCipher(dkey);
    
    System.out.println("Encrypted message:\n" + input);
    System.out.println("\nkey" + dkey);
    System.out.println("\nDecrypted message:\n" + a.decrypt(input));
    
}
    
 

public void simpleTests(){

  /*FileResource fr = new FileResource();
  String message = fr.asString();*/
  OOCaesarCipher c = new OOCaesarCipher(15);
  String encrypted = c.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
  
  System.out.println("encrypted:"+ encrypted);
  System.out.println("decrypted:"+c.decrypt(encrypted));
 }   
           
}
