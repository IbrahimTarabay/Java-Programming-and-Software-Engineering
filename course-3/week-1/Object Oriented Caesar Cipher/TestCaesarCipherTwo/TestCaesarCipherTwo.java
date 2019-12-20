
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import edu.duke.*;

public class TestCaesarCipherTwo {
    
    private int[] countLetters(String message){
        
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        
        for (int k = 0; k<message.length(); k++) {
            
            char ch = Character.toLowerCase(message.charAt(k));
            int index = alphabet.indexOf(ch);
            
            if (index != -1) {
                counts[index] += 1;
            }
        }
        return counts;
    }
    
     private int maxIndex(int[] counts){
        int max=0;
        for(int i=0; i< counts.length;i++){
            
            if(counts[i] > counts[max]){
                max = i;
            }  
        }    
        return max;
    }
    
    private String decrypt(String encrypted,int key){
        
        OOCaesarCipher cc = new OOCaesarCipher(26-key);
        String message = cc.encrypt(encrypted);
        return message;
        
    }
    
    private String halfOfString(String message, int start){
        StringBuilder answer = new StringBuilder("");
        
        for (int k = start; k< message.length() ; k+= 2) {
            answer.append(message.charAt(k));   	
        }
        return answer.toString();
    }
    
    private int getKey(String s){
        int[] letterFreqs = countLetters(s);
        int maxIndex = maxIndex(letterFreqs);
        int dkey = maxIndex - 4;
        
        if (maxIndex < 4) {
            dkey = 26 - (4-maxIndex);
        }
        return dkey;
    }
    
    public void breakCaesarCipher(String input){
        
        String s1 = halfOfString(input, 0);
        String s2 = halfOfString(input, 1);
        
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        
        OOCaesarCipherTwo CC2 = new OOCaesarCipherTwo(key1,key2);
        
        System.out.println("\nEncrypted message:" + input);
        System.out.println("Two keys found: key1= " + key1 + ", key2= " + key2);
        System.out.println("Decrypted message:" + CC2.decrypt(input));
        //return encrypt(input);
    }
    
    
    public void simpleTest() {
        
         /*FileResource fr = new FileResource();
         String message = fr.asString();*/
         /*OOCaesarCipherTwo cc = new OOCaesarCipherTwo(21,8);
         String encrypted = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
         
         System.out.println(encrypted);
         System.out.println(cc.decrypt(encrypted));*/
         
         /*breakCaesarCipher("Akag tjw Xibhr awoa aoee xakex znxag xwko");*/
         breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
     }
}
