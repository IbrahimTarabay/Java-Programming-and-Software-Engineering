
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;



public class BabyNames {

    
    public int getNumberOfGenderNames(int year, String gender){
    
        String fileName = ("us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(fileName);
        
        String searchGender = null;
 
        int rank = 0;
        
        
        for(CSVRecord rec : fr.getCSVParser(false)){//false because we don't want to read the header 
          
            searchGender = rec.get(1);
            
            if(searchGender.equals(gender)){
               rank++;
               
            }
    
    }
    return rank;
}
   
  
    public int getRank(int year, String name, String gender){
    
        String fileName = ("us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(fileName);
        
        
        String searchName = null;
        String searchGender = null;
        int searchYear = 0;
        
        int rankF = 0;
        int rankM = 0;
        int rank = 0;
        
        
        for(CSVRecord rec : fr.getCSVParser(false)){//false because we don't want to read the header 
          
            searchName = rec.get(0);
            searchGender = rec.get(1);
            
            if(searchGender.equals("F")){
               rankF++;
               
                     if(searchName.equals(name) && searchGender.equals(gender)){
                     rank = rankF;
                     break;
                 }
                 
             } 
             else if (searchGender.equals("M")){
                 rankM++;
                  
                 if(searchName.equals(name) && searchGender.equals(gender)){
                     rank = rankM;
                     break;
             }
             
             else {rank = -1;}
           }
            
          } 
            return rank;
       }
            
       
       
       
       
       
       public String getName(int year, int rank, String gender){
           
        String fileName = ("us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(fileName);
        
        
        String searchName = null;
        String searchGender = null;
        int rankF = 0;
        int rankM = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            searchGender = rec.get(1);
            
            if(searchGender.equals("F")){
                rankF++;
                 if(rankF == rank && searchGender.equals(gender)){
                     searchName = rec.get(0);
                     break;
                 }
                 
             } 
             else if (searchGender.equals("M")){
                 rankM++;
                 if(rankM == rank && searchGender.equals(gender)){
                     searchName = rec.get(0);
                     break;
             }
             else {searchName = "NO NAME";}
           }
            
          } 
        return searchName;
    }
       
       
       
    
    
    
    
    
        public void whatIsNameInYear(String name, int year, int newYear, String gender){
            
        String fileName = ("us_babynames_by_year/yob" + newYear + ".csv");
        FileResource frNew = new FileResource(fileName);
        
        String searchGender = null;
        String searchName = null;
        
        int Rank_Of_year = getRank(year, name, gender);
        int rankF_new_year = 0;
        int rankM_new_year = 0;

        for (CSVRecord rec : frNew.getCSVParser(false)) {
            searchGender = rec.get(1);
            
            if(searchGender.equals("F")){
                rankF_new_year++;
                
                 if(rankF_new_year == Rank_Of_year && searchGender.equals(gender)){
                     searchName = rec.get(0);
                     break;
                 }
                 
             } 
             else if (searchGender.equals("M")){
                 rankM_new_year++;
                 
                 if(rankM_new_year == Rank_Of_year && searchGender.equals(gender)){
                     searchName = rec.get(0);
                     break;
             }
             else {searchName = "NO NAME";}
           }
            
          } 
        System.out.println(name + " born in " + year + " would be " + searchName + " if she was born in " + newYear );
    }
       
       
       
       
       
       
    
    
    
    
    
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        
        int smallestYear = 0;
        int sendYear = 0;
        int count = 0;
        int smallestRank=0;
        int rank[] = new int[10000];
        
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            sendYear = Integer.parseInt(fileName.substring(3,7));//example fileName is yob1992 so we want year 1992 
            rank[count] = getRank(sendYear, name, gender);
            
           
             
                if((rank[count] < smallestRank) && rank[count] != -1){
                    smallestRank = rank[count];
                    smallestYear = sendYear;
                 
              }
             
                else if(count == 0){
                
                 smallestYear = sendYear;
                 smallestRank = rank[0];//smalestRank is the highest rank like 1's rank is high than 33's rank
                
                
                }
                  
             /*System.out.println("");
             for(int i=0; i<=count; i++){
             System.out.print("Rank is : " + rank[i]);
             }*/
             count++;
        }
        
        /*int countNegetive = 0;
        for(int i =0; i<count; i++){
            if(rank[i] == -1) {countNegetive++;}
        }
        if(countNegetive == count) {smallestYear = -1;}*/
       
        return smallestYear;
    }
       
       
    
    
    
    
    
    /*This method selects a range of files to process and returns a double representing the average rank of the name and gender over
     * the selected files. It should return -1.0 if the name is not ranked in any of the selected files.
     * For example calling getAverageRank with name Mason and gender ‘M’ and selecting the three test files above results in returning 3.0,
     * as he is rank 2 in the year 2012, rank 4 in 2013 and rank 3 in 2014. As another example,
     * calling getAverageRank with name Jacob and gender ‘M’ and selecting the three test files above results in returning 2.66.
       */
    
    
    
    
     public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        
        int rankOfName=0;
        int sendYear = 0;
        double count = 0;
        double avg = 0;
        double sum = 0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            sendYear = Integer.parseInt(fileName.substring(3,7));
            rankOfName= getRank(sendYear, name, gender);
            //System.out.println("rank is " + rankOfName + "Count is " + count + " year is " + sendYear);
            if(rankOfName != -1) {
            sum += rankOfName;
            
           }
           count++;
           //System.out.println("Sum of rank is " + sum + "Count is " + count);
        }
        
        if(sum == 0) {avg = -1;}
        else {avg = (sum/count);}
        System.out.println(avg);
        return avg;
    }
       
      
    
    
    
    
    
    
    
    
    /*This method returns an integer, the total number of births of those names with the same gender and 
     same year who are ranked higher than name.
     For example, if getTotalBirthsRankedHigher accesses the "yob2012short.csv" file with name set to “Ethan”,
     gender set to “M”, and year set to 2012, then this method should return 15,
     since Jacob has 8 births and Mason has 7 births, and those are the only two ranked higher than Ethan.*/
    
    
    
    
       public int totalBirths(String gender, int rank, int year){
           
        String fileName = ("us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(fileName);
        String searchGender = null;
        
        int totalM = 0;
        int totalF = 0;
        int rankF =0;
        int rankM = 0;
        
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            searchGender = rec.get(1);
            
            if(searchGender.equals("F")){
                rankF++;
                totalF += Integer.parseInt(rec.get(2));
                 if(rankF == (rank-1) && searchGender.equals(gender)){
                     break;
                 }
                 
             } 
             else if (searchGender.equals("M")){
                 rankM++;
                 totalM+= Integer.parseInt(rec.get(2));
                 if(rankM == (rank-1) && searchGender.equals(gender)){
                     break;
             }
             //else {searchName = "NO NAME";}
           }
            
          } 
          
          if(gender.equals("M")) {return totalM;}
          else {return totalF;}
    }
       
       
    
    
    
    
    
      public int getTotalBirthsRankedHigher(int year, String name, String gender){
          
        String fileName = ("us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(fileName);
        
        int RankOf = getRank(year, name, gender);
        int index=RankOf-1;
        int Sum = 0;
        
        
        
        if(index > 0){
            Sum = totalBirths(gender, RankOf, year);
        }
        return Sum;
    }
    
    
    
    
    
        public void getRanktest(){ 
        
        /*int number =  getNumberOfGenderNames(1905, "M");   
        System.out.println("number of names is : " + number);*/  
            
            
            
            
            
        //1st method
        /*int rank = getRank(1971, "Frank", "M");
        System.out.println("Rank is : " + rank);*/
        // End of 1st method
        
        //2nd method
        /*String getNames = getName(1982, 450, "M");
        System.out.println("Name of this Rank is : " + getNames);*/
        // End of 2nd method
        
        //3rd method
        /*String name = "Owen";
        int year1 = 1974;
        int year2 = 2014;
        String gender = "M";
        whatIsNameInYear(name, year1, year2, gender); */
        // End of 3rd method
        
        //4th method
        /*int highestRank = yearOfHighestRank("Mich", "M");
        System.out.println("Most popular rank is in Year " + highestRank);*/
        // End of 4th method
        
        //5th method
        double avgRank = getAverageRank("Robert", "M");
        System.out.println("Avg rank is " + avgRank);
        // End of 5th method
        
        //6th method
        //int birthHigher = getTotalBirthsRankedHigher(1990, "Emily", "F");
        //System.out.println("Total Births Ranked Higher " + birthHigher);
        // End of 6th method
    }
    
    
    
    
    
    
    
    
    
       
       
       
            
    }
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

