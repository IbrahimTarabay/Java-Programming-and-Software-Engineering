
/**
 * Write a description of EarthQuakeClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import java.util.ArrayList;


public class EarthQuakeClient {

   public static void main(String[] args){
    
    
    
    
    } 
    
    
   public EarthQuakeClient(){
     //auto-generated constructor stub
    } 
    
    
   public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
   double magMin){
    
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    
    for(QuakeEntry qe: quakeData){
    
     if(qe.getMagnitude() >= magMin){
           
         answer.add(qe); 
        }
     }
     return answer;
    } 
    
    
    
   
   public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
   double distMax, Location from){
    
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    
    for(QuakeEntry qe : quakeData){
    
     if(qe.getLocation().distanceTo(from) <= distMax){
         
         answer.add(qe);
        }
    
    }
    
    return answer;
    }
    
    
    
   public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
   double minDepth, double maxDepth){
    
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    
    for(QuakeEntry qe : quakeData){
    
     if(minDepth <= qe.getDepth() && qe.getDepth() <= maxDepth){
        
         answer.add(qe);
        
        }
   
    }
    
    return answer;  
  } 
    
    
    
  public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
  String where,String phrase){
    
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    
     for(QuakeEntry qe: quakeData){
        
         String title = qe.getInfo();
         if(where.equals("start") && title.startsWith(phrase)){ 
              answer.add(qe);
            }
            
         else if(where.equals("end") && title.endsWith(phrase)){
              answer.add(qe);
            }
            
         else if(where.equals("any") &&  title.contains(phrase)){
              answer.add(qe);
            }
            
        }
        return answer;
         
     }
    
    
  public void quakesByPhrase(){
    
    int count = 0;
    
    EarthQuakeParser parser = new EarthQuakeParser();
    
    String source = "data.txt";
    
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for "+list.size()+" quakes");
    
    
    ArrayList<QuakeEntry> listByTitle = filterByPhrase(list,"any","Creek");
    
    
    for(QuakeEntry qe : listByTitle){
      System.out.println(qe);
      count = count+1;
    }
    
    System.out.println(count + " item");  
  }
     
     
  public void quakesOfDepth(){
     EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
     String source = "data.txt";
     ArrayList<QuakeEntry> list  = parser.read(source);
     System.out.println("read data for "+list.size()+" quakes");
     int count = 0;
     ArrayList<QuakeEntry> listDepth = filterByDepth(list, -8000.0, -5000.0);
      for(QuakeEntry qe:listDepth){
        System.out.println(qe);
        count++;
      }
      System.out.println("count is:"+count);
    }   
     
  
    
  public void bigQuakes() {
     EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
     String source = "data.txt";
     ArrayList<QuakeEntry> list  = parser.read(source);
     System.out.println("read data for "+list.size()+" quakes");

    ArrayList<QuakeEntry> filteredList = filterByMagnitude(list, 5.5);
    for(QuakeEntry qe: filteredList){
       System.out.println(qe);
      }
   }
    
    
    
    
    
    
    
    
  
   public void dumpCSV(ArrayList<QuakeEntry> list){
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
    
    
    
    
    
    
    
    
    
    
    

