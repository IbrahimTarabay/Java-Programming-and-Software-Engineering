
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
     for(logEntry le : records){
        
        String d = le.getAccessTime().toString();
        String ip = le.getIpAddress();
        
        if(d.contains(someday) && !addresses.contains(ip)){
        
         addresses.add(ip);
         
        }
        
        }
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
    
    
    
    
    
    
    
    
    
    
    
    
    
}
