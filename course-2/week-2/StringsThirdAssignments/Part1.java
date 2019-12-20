
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import edu.duke.*;
import java.io.*;

public class Part1 {

        public static void main(String[] args) {

        //testFindStopCodon();
        //testgetAllGenes();

    }
    
    
    
    public static int findStopCodon(String dna, int startIndex, String stopCodon){
    dna = dna.toUpperCase();
    int currIndex = dna.indexOf(stopCodon, startIndex+1);
    while(currIndex != -1){
    
        if((currIndex - startIndex) % 3 == 0){return currIndex;}
        currIndex = dna.indexOf(stopCodon, currIndex+stopCodon.length());    
    }
    
    return dna.length();
    
    }
    
    public static String findGene(String dna, int where){
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){ return "0";}
        
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        
        int len = dna.length();
  
        int minIndex = 0;
        
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
        
            minIndex = tgaIndex;
            
        }
         else { minIndex = taaIndex; }

        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }

        if (minIndex == -1){ return "0"; }
        
        
        //if(minIndex)
        return dna.substring(startIndex,minIndex+3);
    }
        
     public static StorageResource getAllGenes(String dna){
        
        dna = dna.toUpperCase();
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        
        
        while(true){
            
            String gene = findGene(dna, startIndex);
            if(gene == "0"){break; }
            geneList.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        
        return geneList;
        
        }
        

    

   
    public static float cgRatio(String dna){
        dna = dna.toUpperCase();
        int CGcounter = 0;
        String CG = "CG";
        
        for(int i=0; i<dna.length(); i++){
         
            if(dna.charAt(i)==CG.charAt(0) || dna.charAt(i)==CG.charAt(1)){
            
               CGcounter++;
            }
        
        }
        return (float)CGcounter/dna.length();
    }
    
    public static int countCTG(String dna){
        dna = dna.toUpperCase();
        int counter = 0, start = 0;
        
        while(true){
        int startIndex = dna.indexOf("CTG",start);
        if(startIndex == -1){break;}
        counter++;
        start = startIndex + 2;
        }
        return counter;
    
    }
    

    
        public static void processGenes(StorageResource sr){

        int nineplusGENE = 0;
        int cgratioCounter = 0;
        int longestGene = 0;
        int totalGenes = 0;

        for(String s: sr.data()){


            if(!findGene(s, 0).isEmpty()){
                totalGenes++;

                if(findGene(s,0).length() > 60){
                    System.out.println("More than 9 char in Gene: " + s);
                    nineplusGENE++;
                }

                if(cgRatio(s) > 0.35){
                    System.out.println("Ratio bigger than 0.35: " + s);
                    cgratioCounter++;
                }

                if(s.length() > longestGene){
                    longestGene = s.length();
                }
            }
        }

        System.out.println("CG Ratio: " + cgratioCounter);
        System.out.println("Genes that are 60+ chars: " + nineplusGENE);
        System.out.println("The length of the longest gene: " + longestGene);
        System.out.println("Total Genes: " + totalGenes);
    }


    public static void testProcesswithRealDNA(){

        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        //System.out.println("DNA: " + dna.toUpperCase());
        System.out.println("Total CTGs: " + countCTG(dna));
        
        
        
        StorageResource geneList = getAllGenes(dna);
        
        System.out.println("genlist " + geneList);
        
        //processGenes(geneList);

    }
    
    
    
    
  
}



    /*public static void testFindStopCodon(){
        //012345678901234567890123456
        String dna = "xxxyyyxxxATGxxxTAAATGTAATAG";

        int stopCodon = findStopCodon(dna, 9, "TAA");
        if(stopCodon != 15){ System.out.println("error on 15"); }

        stopCodon = findStopCodon(dna,18,"TAA");
        if(stopCodon != 21){ System.out.println("error on 21" + stopCodon); }

        stopCodon = findStopCodon(dna,19,"TAA");
        if(stopCodon != dna.length()){ System.out.println("error on dna.length 21"); }

        stopCodon = findStopCodon(dna,9,"TAG");
        if(stopCodon != 24){ System.out.println("error on TAG 24" + stopCodon); }

        stopCodon = findStopCodon(dna, 1, "TAA");
        if(stopCodon != dna.length()){ System.out.println("error on dna.length 1"); }

        System.out.println("tests finished (findStopCodon)");
    }

}*/

     /*public static void testgetAllGenes(){
        String dna = "ATGxxxyyyTAAyyyATGAxxxTAA";
        StorageResource genes = getAllGenes(dna);
        for(String s: genes.data()){
            System.out.println(s);
        }
    }*/
   
        /*public static void testProcessGenes(){

        String nineLong = "ATGxxxTAAyyyATGxxxTAG";
        StorageResource geneList = getAllGenes(nineLong);
        processGenes(geneList);

        /*String dna1 = "ATGxxxyyyTAAyyyATGxxxyyyxxxyyyxxxTAG";  //2 genes longer than 9
        geneList = getAllGenes(dna1);
        processGenes(geneList);

        String dna2 = "ATGCGCCyyTAAyyyATGxxxyyyCGGGGCxxxTAG";  //genes with 0.35+ CG ratio
        geneList = getAllGenes(dna2);
        processGenes(geneList);

        String dna3 = "ATGxxxyyyxxxyyyTAG";                    //genes with 0.35- CG ratio
        geneList = getAllGenes(dna3);
        processGenes(geneList);

    }*/
    
          /* public static void testCTG(){
        System.out.println("Number of CTGs: " + countCTG("CTGxxxCTGxxxCTGyyyCTGxCTG"));      //5
    }*/
