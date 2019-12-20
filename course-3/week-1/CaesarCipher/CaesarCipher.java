import edu.duke.*;
import java.io.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String capitalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallAlphabet = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedCapitalAlphabet = capitalAlphabet.substring(key)+capitalAlphabet.substring(0,key);
        String shiftedSmallAlphabet = smallAlphabet.substring(key)+smallAlphabet.substring(0,key);
        
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            if(Character.isUpperCase(currChar)){
             
                int index = capitalAlphabet.indexOf(currChar);
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedCapitalAlphabet.charAt(index);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
               
            }
            else if(Character.isLowerCase(currChar)){
              
                int index = smallAlphabet.indexOf(currChar);
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedSmallAlphabet.charAt(index);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
              
            
            
            }else{
            
               continue;
            
            }
            
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    
    
    
    public String encryptTwoKeys(String input, int key1, int key2){
    
    StringBuilder encrypted = new StringBuilder("");
    
    
    int i;
    
    
    for(i=0;i<input.length();i++){
     if(i%2 == 0){
         
         
          encrypted.append(encrypt(Character.toString(input.charAt(i)), key1));
         
         
        }
        
     else{
        
          encrypted.append(encrypt(Character.toString(input.charAt(i)), key2));
        
        }
    
    }
    
    return encrypted.toString();
    
  }
    
    
    
    
    
    
    
    
    public void testCaesar() {
        /*FileResource fr = new FileResource();
        String message = fr.asString();*/
        /*int key = 15;
        String encrypted = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);*/
        String encrypted = encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24, 6);
        System.out.println(encrypted);
    }
}

