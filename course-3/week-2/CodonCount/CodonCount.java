
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import edu.duke.*;

public class CodonCount {
  
    private HashMap<String, Integer> map;
    
    /*Initializes any private variables.*/
    public CodonCount(){
    
      map = new HashMap<String, Integer>();
    
    }
    
    /*Builds a new map of condons mapped to their counts from the string dna*/
    
    private void buildCodonMap(int start,String dna){
     
        //Ensure map is empty before building it
        map.clear();
        int count = 0;
        
        for(int i = start; i<=dna.length()-3;i+=3){
         
            String codon = dna.substring(i,i+3);
            
            if(map.containsKey(codon)){
             
                count = map.get(codon);
                map.put(codon, count+1);
            }else{
             
             map.put(codon,1);
            
            }
        
        }
        
        System.out.println(map.toString());
    }
    
    /*Return a String, the codon in a reading frame that
       has the largest count if there are several such codons,
       return any one of them*/
    
    private String getMostCommonCodon(){
    
     int count = 0;
     int curr = 0;
     String mostCommonCodon = "";
     
     for(String codon: map.keySet()){
         curr = map.get(codon);
        
        if(curr>count){
         
            mostCommonCodon = codon;
            count = curr; 
        
        }
        
     }
     
     return mostCommonCodon;
  }
    
   /*prints all the codons in the hashMap along with their counts if
      their count is between start and end, inclusive*/
      
      
  private void printCodonCounts(int start, int end){
      int count = 0;
      
      
      for(String codon : map.keySet()){
        
         count = map.get(codon);
         
         if(count >= start && count <= end){
            
            System.out.println(codon+ " "+count);
            
            
            }
        
        }
    }
    
  public void tester(){
    FileResource fr = new FileResource();
    String dna = fr.asString().toUpperCase().trim();/*public String trim()
    Returns a copy of the string, with leading and trailing whitespace omitted*/
    System.out.println(dna);
    
    for(int start = 0;start < 3; start++){
     
        buildCodonMap(start,dna);
        System.out.println("Reading frame starting with "+ start + " results in"+map.size() +
        " unique codons");
        
        String mostCommonCodon = getMostCommonCodon();
        System.out.println("and the most common codon is " + mostCommonCodon+
        " with count "+map.get(mostCommonCodon));
        System.out.println("Counts of codons between 1 and 5 inclusive are: ");
        printCodonCounts(4,4);
        System.out.println();
    
    }
      
  }
       
}
