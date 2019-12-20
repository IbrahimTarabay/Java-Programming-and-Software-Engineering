
/**
 * Write a description of ClosestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

/*The class Location , from the Android platform and revised for this course, a data class
representing a geographic location. One of the constructors has parameters latitude and
longitude, and one of the public methods is distanceTo*/


public class ClosestQuakes {

  public static void main(String[] args){
    
        ClosestQuakes cq = new ClosestQuakes();
        cq.findClosestQuakes();
    
    }  
    
    
    
   public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany){
    
    ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
    ArrayList<QuakeEntry> copy = new ArrayList <QuakeEntry>(quakeData);
    
    for(int i =0; i<howMany;i++){
    
     int minIndex = 0;
     int counter = 0;
     
     for(QuakeEntry qe: copy){
         
        double minDistance = copy.get(minIndex).getLocation().distanceTo(current);
         
        if(qe.getLocation().distanceTo(current) < minDistance){
        
           minIndex = counter;
        
        }
        counter++;  
      }
      
      ret.add(copy.get(minIndex));
      copy.remove(minIndex);
     
    }
    
      return ret;
   }
   
   
   
   
   public void findClosestQuakes(){
    
     EarthQuakeParser parser = new EarthQuakeParser();
     String source = "data.txt";
     
     ArrayList<QuakeEntry> list = parser.read(source);
     System.out.println("read data for "+list.size());
     
     Location city = new Location(-6.211,106.845);
     
     ArrayList<QuakeEntry> close = getClosest(list,city,3);
     for(int k = 0;k<close.size();k++){
         
         QuakeEntry entry = close.get(k);
         double distanceInMeters = city.distanceTo(entry.getLocation());
         System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        
        }
     System.out.println("number found: "+close.size());
    }
   
     
}