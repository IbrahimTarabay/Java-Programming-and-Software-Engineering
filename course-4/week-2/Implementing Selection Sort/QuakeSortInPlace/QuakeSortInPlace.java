
/**
 * Write a description of QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    
   public QuakeSortInPlace(){
      //auto-generated constructor stub
    } 
    
   public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from){
    
     int minIndex = from;
     for(int i = from+1;i<quakes.size();i++){
        
        if(quakes.get(i).getMagnitude() < quakes.get(minIndex).getMagnitude()){
        
         minIndex =i;
        
        }  
      }
      
      return minIndex;
    } 
    
    
   
   public void sortByMagnitude(ArrayList<QuakeEntry> in){
    
    for(int i=0; i<in.size();i++){
    
     int minIndex = getSmallestMagnitude(in,i);
     
     QuakeEntry qi = in.get(i);
     QuakeEntry qmin = in.get(minIndex);
    
     in.set(i,qmin);
     in.set(minIndex,qi);
    
    }
    
   } 
    
   
   
   public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
    
       for(int i=0;i<quakeData.size()-1-numSorted;i++){
        
        QuakeEntry currQe = quakeData.get(i);
        QuakeEntry nextQe = quakeData.get(i+1);
        
        if(currQe.getMagnitude() > nextQe.getMagnitude()){
        
         quakeData.set(i+1,currQe);
         quakeData.set(i, nextQe);
        }  
      }
    }
    
   
    
    
   public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
    
    for(int i=0;i<in.size()-1;i++){
     
        onePassBubbleSort(in, i);
        //System.out.println("Printing quakes after pass "+i);
    }    
    for(QuakeEntry qe : in){
        
      System.out.println(qe);
        
    }  
  }
    
    
  public boolean checkInMagSortedOrder(ArrayList<QuakeEntry> quakes){
    
    for(int i=0; i<quakes.size()-1; i++){
    
     QuakeEntry currQe = quakes.get(i);
     QuakeEntry nextQe = quakes.get(i+1);
    
     if(currQe.getMagnitude() > nextQe.getMagnitude()){
        
         return false;
        
        }
     
    }
    return true;
   }  
    
    
    
  public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
    
    for(int i = 0; i<in.size()-1;i++){
    
     onePassBubbleSort(in,i);
     if(checkInMagSortedOrder(in)){
        System.out.println("Sorted with "+(i+1)+" passes");
        break;
       }
    }
  
  }  
    
    
  public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
    
    //worst case O(n^3)  
    for(int i = 0; i<in.size(); i++){
    
     int minIndex = getSmallestMagnitude(in,i);
     
     QuakeEntry qi = in.get(i);
     QuakeEntry qmin = in.get(minIndex);
     
     in.set(i,qmin);
     in.set(minIndex,qi);
    
     
     if(checkInMagSortedOrder(in)){
        
         System.out.println("Sorted with "+(i+1)+" passes");
         break;
        }
     }
   }
  
   
  public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
    
    int maxIndex = from;
    
    for(int i = from+1; i<quakeData.size(); i++){
    
       if(quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth()){
        
         maxIndex = i;
         
        }
    } 
    return maxIndex;  
  } 
   
  public boolean checkInDepthSortedOrder(ArrayList<QuakeEntry> quakes){
    
    for(int i=0; i<quakes.size()-1; i++){
    
     QuakeEntry currQe = quakes.get(i);
     QuakeEntry nextQe = quakes.get(i+1);
    
     if(currQe.getDepth() < nextQe.getDepth()){
         return false;
        }
    }
     return true;
   } 
   
  
  public void sortByLargestDepth(ArrayList<QuakeEntry> in){
    
    // i<70 means to 70 passes  
    for(int i =0;i<70; i++){
     
        int maxIndex = getLargestDepth(in,i);
        QuakeEntry qi = in.get(i);
        QuakeEntry qmax = in.get(maxIndex);
        
        in.set(i,qmax);
        in.set(maxIndex,qi);
        
        if(checkInDepthSortedOrder(in)){
         System.out.println("in checkInSortedOrder "+i);
         System.out.println("Sorted with "+(i+1)+" passes");
         break;
        }
      System.out.println("after checkInSortedOrder "+i);
    }
      
  }
  
  
  
  
  
  
   
   
   
   
   
   
   
   
  
      public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        // String source = "data/earthQuakeDataDec6sample2.atom";
        String source = "earthQuakeDataDec6sample2.txt";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        // sortByMagnitudeWithCheck(list);
        sortByLargestDepth(list);
      
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        //int count = 0;
        for (QuakeEntry qe: list) { 
            //count++;
            System.out.println(qe);
            /*if(count == 70){
                break;
            }*/
        } 
        
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "earthQuakeDataDec6sample1.txt";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
  
  
  
  
  
  
  
  
    
    
    
    

}
