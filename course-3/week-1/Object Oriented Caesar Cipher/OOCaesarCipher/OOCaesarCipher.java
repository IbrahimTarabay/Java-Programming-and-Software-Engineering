
/**
 * Write a description of OOCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class OOCaesarCipher {

    private String alphabet;    
    private String shiftedAlphabet;
    private int mainKey;
    
    
    public OOCaesarCipher(int key){
      
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
        mainKey = key;
    }
    
    
    public String encrypt(String input){
    
     StringBuilder encrypted = new StringBuilder(input);
    
     for(int i=0;i<encrypted.length();i++){
        
         char currChar = encrypted.charAt(i);
         int index = alphabet.indexOf(Character.toUpperCase(currChar));
         
         
         if(index != -1){
            if(currChar == Character.toUpperCase(currChar)){
              
                char newChar = shiftedAlphabet.charAt(index);
            
                encrypted.setCharAt(i,newChar);
            
            }
            
            else{
            
              char newChar = shiftedAlphabet.charAt(index);
              encrypted.setCharAt(i, Character.toLowerCase(newChar));
            }
            
          }
        
        }
    
        return encrypted.toString();
    }
    
    public String decrypt(String input){
    
        OOCaesarCipher cc = new OOCaesarCipher(26-mainKey);
    
        return cc.encrypt(input);
    
    }
    
}


















