
/**
 * Write a description of EarthQuakeClient2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    
  public EarthQuakeClient2(){
     //Auto-generated constructor stub
    }  
    
  public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f){
    
      ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    
      for(QuakeEntry qe : quakeData){
        if(f.satisfies(qe)){
         
         answer.add(qe);
        }
        
       }
    
      return answer;
    }  
    
    
  public void quakesWithFilter(){
    int count = 0;
    EarthQuakeParser parser = new EarthQuakeParser(); 
 
    String source = "data.txt";
    ArrayList<QuakeEntry> list  = parser.read(source);         
    System.out.println("read data for "+list.size()+" quakes");
    
    Filter f = new MagnitudeFilter(4.0, 5.0);
    ArrayList<QuakeEntry> answer = filter(list, f);
    f = new DepthFilter(-35000.0, -12000.0);
    answer = filter(answer, f);
    for(QuakeEntry qe : answer) {
        System.out.println(qe);
        count++;
     }
     
     System.out.println("count is :"+count);
}

    public void testMatchAllFilter(){
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data.txt";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f = new MagnitudeFilter(0.0, 2.0);
        maf.addFilter(f);
        f= new DepthFilter(-100000.0, -10000.0);
        maf.addFilter(f);
        f = new PhraseFilter("any", "a");
        maf.addFilter(f);
        
        ArrayList<QuakeEntry> answer = filter(list, maf);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
        
        System.out.println("Found " + answer.size() + " earthquakes ");
        System.out.println("Filters used are: " + maf.getName());



        

    }
    
    public void testMatchAllFilter2(){
        // String source = "data/nov20quakedatasmall.atom";
        String source = "data.txt";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f = new MagnitudeFilter(0.0, 3.0);
        maf.addFilter(f);
        Location city = new Location(36.1314, -95.9372);
        f = new DistanceFilter(city,  10000000);
        maf.addFilter(f);
        f = new PhraseFilter("any", "Ca");
        maf.addFilter(f);
        
        ArrayList<QuakeEntry> answer = filter(list, maf);
        for(QuakeEntry qe:answer){
            System.out.println(qe);
        }
        
        System.out.println("Found " + answer.size() + " earthquakes ");
        System.out.println("Filters used are: " + maf.getName());

        
        


        
    } 
    
    
    
    
    

}
