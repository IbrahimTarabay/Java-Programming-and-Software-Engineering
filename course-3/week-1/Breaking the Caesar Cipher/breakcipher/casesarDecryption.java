
/**
 * Write a description of breakCeaser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class casesarDecryption {
    public static void main(String[] args) {
        /*String message1 = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        System.out.println("Encrypted message is :" + message1);
        String b = decrypt(message1);
        System.out.println("Decrypted message is :" + b);*/
        //decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko");
        FileResource fr = new FileResource();
        String message = fr.asString();
        String s1 = halfOfString(message, 0);
        String s2 = halfOfString(message, 1);
        System.out.println(s1);
        System.out.println(s2);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        System.out.println("Two keys found: key1= " + key1 + ", key2= " + key2);
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc.encryptTwoKeys(message, 26 - key1, 26 - key2));


    }

    public static int[] countLetters(String message) {
        int[] count = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int index = alphabet.indexOf(ch);
            if (index != -1) {
                count[index] += 1;
            }
        }
        return count;
    }

    public static int maxIndex(int[] count) {
        int max = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] >= count[max]) {
                max = i;
            }

        }
        return max;
    }

    public static String decrypt(String encrypted) {

        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = 4 - maxDex;

        if (maxDex > 4) {

            dkey = 26 - (maxDex - 4);

        }


        System.out.println("Key is " + dkey);

        return cc.encrypt(encrypted, 26 - dkey);

    }

    
    
    
    
    
    //methods used to decrypt message with two keys
    
    public static String halfOfString(String message, int start) {
        String answer = "";
        for (int i = start; i < message.length(); i += 2) {
            answer = answer + message.charAt(i);
        }
        return answer;
    }

    public static int getKey(String s) {
        
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);//distance between (most common letter
            //in english language (e) and the most freq letter in encrypted message)
        }
        return dkey;
    }

    public static void decryptTwoKeys(String encrypted) {
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        System.out.println(s1);
        System.out.println(s2);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        System.out.println("Two keys found: key1= " + key1 + ", key2= " + key2);
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2));
    }
}









 class CaesarCipher {
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








