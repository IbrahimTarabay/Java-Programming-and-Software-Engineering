
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class OOCaesarCipherTwo {
    
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int k1;
    private int k2;
    
    
    
    public OOCaesarCipherTwo(int key1, int key2){
     
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        
        k1 = key1;
        k2 = key2;    
    
    
    }
    
     public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i = 0; i < encrypted.length(); i += 2) {
            char currChar = encrypted.charAt(i);
            
            if (Character.isUpperCase(currChar) == true){
                int index = alphabet.toUpperCase().indexOf(currChar);
                if (index != -1){
                    char newChar = shiftedAlphabet1.toUpperCase().charAt(index);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else{
                int idx = alphabet.indexOf(currChar);
                if (idx != -1){
                    char newChar = shiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        
        for(int i = 1; i < encrypted.length(); i += 2) {
            char currChar = encrypted.charAt(i);
            
            if (Character.isUpperCase(currChar) == true){
                int idx = alphabet.toUpperCase().indexOf(currChar);
                if (idx != -1){
                    char newChar = shiftedAlphabet2.toUpperCase().charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else{
                int idx = alphabet.indexOf(currChar);
                if (idx != -1){
                    char newChar = shiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        OOCaesarCipherTwo a = new OOCaesarCipherTwo(26 - k1, 26 - k2);
        return a.encrypt(input);
    }
    

}