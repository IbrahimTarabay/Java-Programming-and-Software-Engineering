
/**
 * Write a description of LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
 
  private ArrayList<logEntry> records;  
    
    public LogAnalyzer(){
    
     //complete constructor
     //initialize records to an empty ArrayList
     
     records = new ArrayList<logEntry>();
    
    }
    
    public void readFile(String filename){
    
     //create a FileResource for fileName
     //iterate over its lines for each one
     //use webLogParser.parseEntry to records
    
     FileResource fr = new FileResource(filename);
     
        /*String ip;
         Date time;
         int status;
         int bytes;*/
     
     
     
     
     for(String line: fr.lines()){
      //create a new logEntry object by parsing the file with WebLogParser  
       logEntry le = WebLogParser.parseEntry(line);
      //store logEntry object in the records ArrayList
        records.add(le);
        
        }
     
    }
    
    
    
    public int countUniqueIPs(){
    
     //uniqueIPs starts as an empty list
     ArrayList<String> uniqueIPs = new ArrayList<String>();     
     //for each element le in records
     for(logEntry le: records){
        
         //ipAddr is le's ipAddress
         String ipAddr = le.getIpAddress();
         //if ipAddr is not uniqueIPs
         if(!uniqueIPs.contains(ipAddr)){
            //add ipAddr to uniqueIPs
            
            uniqueIPs.add(ipAddr);
            }
        }
     // return the size of uniqueIPs
     return uniqueIPs.size();
    }
    
    public void printAll(){
     //implicitly use .toString() in logEntry
     for(logEntry le : records){
        
        System.out.println(le);
        
        }
    }
    
    
    
    public void printAllHigherThanNum(int num){
    
     for(logEntry le : records){
        if(le.getStatusCode() > num){
        
          System.out.println(le);
        
        }
        
      }
   
    }
    
    
    public ArrayList<String> UniqueIPVisitsOnDay(String someday){
    
    
     ArrayList<String> addresses = new ArrayList<String>();
     int count=0;
     
     for(logEntry le : records){
        
        String d = le.getAccessTime().toString();
        String ip = le.getIpAddress();
        
        if(d.contains(someday) && !addresses.contains(ip)){
        
         addresses.add(ip);
         //count++;
        }
        
      }
      //System.out.println("count is : " + count);
      return addresses;
    }
     
    

    
    
    
   public int countUniqueIPsInRange(int low, int high){
    
     HashMap<String,Integer> uniques = new HashMap<String,Integer>();
    
     //for every record
     for(logEntry le: records){
        //obtain ip address and status code
         
        String ip = le.getIpAddress();
        int status = le.getStatusCode();
        //if key does not already exist in map and status code is within range
        if(!uniques.containsKey(ip) && (status >= low) && (status <= high)){
         
           //add unique ip address to map 
           uniques.put(ip,status);
        }
      }
    
     System.out.println("Unique ips in range are" + uniques);
    
     return uniques.size();
    }
    
    
     /*public int countUniqueIPsInRange(int low, int high){
        int numOfIPs = 0;
        ArrayList<String> IPs = new ArrayList <String>();
        for(logEntry le: records){
            if(le.getStatusCode()>=low && le.getStatusCode()<=high && !IPs.contains(le.getIpAddress())){
                IPs.add(le.getIpAddress());
                numOfIPs++;
            }
        }return numOfIPs;
     }*/
    
    
   // The following section is about counting website visits
    
    
   public HashMap<String,Integer> countVisitsPerIP(){
    
     //make an empty HashMap<String,Integer> counts
    
     HashMap<String,Integer> counts = new HashMap<String, Integer>();
     //for each le's ipAddress
     for(logEntry le: records){
        //check if ip is in counts
        String ip = le.getIpAddress();
        if(!counts.containsKey(ip)){
        
         counts.put(ip,1);
        
        }
        
        else{
          counts.put(ip,counts.get(ip)+1);
        }
        
      }
    
     return counts;
   }
    
   public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
    
    int max = 0;
    //for every ip address
    for(String ip: counts.keySet()){
     //obtain its number of counts
     int curr = counts.get(ip);
     if(curr>max){
         max = curr;
        }
     }
     return max;
   }
    
   
   
   public ArrayList iPsMostVisits(HashMap<String,Integer> counts){
    
       ArrayList<String> maxIPs = new ArrayList<String>();
       int max = mostNumberVisitsByIP(counts);
       for(String ip: counts.keySet()){
        
         if(max == counts.get(ip)){
            
            maxIPs.add(ip);
            
            }
        
        }
    
       return maxIPs;
    }
   
   
   
   public HashMap<String, ArrayList<String>> iPsForDays(){
    
    HashMap<String, ArrayList<String>> dateMap = new HashMap<String, ArrayList<String>>();
    
    
    
    
     for(logEntry le: records){
        //obtain date and ipAddr
         
        String date = le.getAccessTime().toString().substring(4,10);
        String ipAddr = le.getIpAddress();
        //if dateMap does not contain date yet
        
        if(!dateMap.containsKey(date)){
         
         ArrayList<String> ipArray = new ArrayList<String>();
         
         ipArray.add(ipAddr);
         dateMap.put(date,ipArray);
        
        }
        
        else{
        
         //obtain ArrayList of IP address
         ArrayList<String> ipArray = dateMap.get(date);
         ipArray.add(ipAddr);
         dateMap.put(date,ipArray);
        
        }
          
     }
    
    return dateMap;
    
    }
   
   
    
    
    
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dateMap){
    
     String maxDay = ""; 
     
     for(String date : dateMap.keySet()){
        
        ArrayList<String> ips = dateMap.get(date);
        if(maxDay.equals("") || ips.size() > dateMap.get(maxDay).size()){
             
         maxDay = date;
        
        }
        
      }
    
     return maxDay;
   
    }
    
    
   public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dateMap,String date){
    
    ArrayList<String> ips = dateMap.get(date);
    HashMap<String, Integer> visits = new HashMap<String, Integer>();
    
    for(String ip : ips){
    
     if(visits.containsKey(ip)){
        
         int num = visits.get(ip);
         visits.put(ip, num+1);
         
        }
      else{
        
         visits.put(ip,1);
        
        }
    
    }
    
    return iPsMostVisits(visits);
  }
      
 }
    
    
    
    
   
   
   
   
    

