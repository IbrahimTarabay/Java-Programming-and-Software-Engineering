
/**
 * Write a description of FindGeneSimpleAndTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindGeneSimpleAndTest {
    public String findGeneSimple(String dna){
    //start codon is 'ATG'
    //stop codon is 'TAA'
    String result = "";
    int startIndex = dna.toUpperCase().indexOf("ATG");
    int stopIndex = dna.toUpperCase().indexOf("TAA", startIndex+3);
    
    if(startIndex == -1 || stopIndex == -1){
    
      return "no gene found!1";
    
    }
    
    result = dna.substring(startIndex, stopIndex+3);
    
    if(result.length() % 3 == 0){
    
     return result;
    
    }else{
      return "no gene found!2";
    }
    
 }
 
 public void testFindGeneSimple(){
    
    String dna = "AATGCGTAATATGGT";
    System.out.println("DNA strand is " + dna);
    String gene = findGeneSimple(dna);
    System.out.println("Gene is " + gene);
    
    dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
    System.out.println("DNA strand is " + dna);
    gene = findGeneSimple(dna);
    System.out.println("Gene is " + gene);
    
    dna = "ATGTAA";
    System.out.println("DNA strand is " + dna);
    gene = findGeneSimple(dna);
    System.out.println("Gene is " + gene);
    }
  
}
