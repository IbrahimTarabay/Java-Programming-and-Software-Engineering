
/**
 * Write a description of WordGram here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordGram {

   private String[] myWords; 
   private int myHash; 
    
    
   public WordGram(String[] source, int start,int size){
    
     myWords = new String[size];
     System.arraycopy(source,start,myWords,0,size);
     /*public static void arraycopy(Object source_arr, int sourcePos,
                            Object dest_arr, int destPos, int len)
     Parameters : 
     source_arr : array to be copied from
     sourcePos : starting position in source array from where to copy
     dest_arr : array to be copied in
     destPos : starting position in destination array, where to copy in
     len : total no. of components to be copied.*/
    
    } 
    
   
   public String wordAt(int index){
    
     if(index < 0 || index >= myWords.length){
        
        throw new IndexOutOfBoundsException("bad index in wordAt "+index);
           
     }
     return myWords[index];
   } 
    
   
   public int length(){
    
    return myWords.length; 
   }
    
   
   public String toString(){
    
    String ret = ""; 
    for(int i=0; i < myWords.length;i++){
    
     ret += myWords[i];
     ret += " ";
    } 
    return ret.trim();
     
   }
   
   public boolean Equals(Object o){
    
     WordGram other = (WordGram) o;
     
     for(int i=0; i<this.length();i++){
        if(this.length() != other.length()){ return false; }
        if(! other.wordAt(i).equals(this.wordAt(i))){
        
         return false;
        
        }
         
     }
    
     return true;   
   }
   
   
   public int hashCode(){
    
    return this.toString().hashCode(); 
    
   }
   
   
   public WordGram shiftAdd(String word){
    
    WordGram out = new WordGram(myWords,0,myWords.length);
    for(int i=0; i<myWords.length-1;i++){
    
     myWords[i] = myWords[i+1];
     
    }
    myWords[myWords.length-1] = word;
    return out;
   }
       
}
