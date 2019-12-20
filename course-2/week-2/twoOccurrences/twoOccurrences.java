
/**
 * Write a description of twoOccurrences here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class twoOccurrences {
    
    public static void main(String[] args){
    
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));                  
        System.out.println(twoOccurrences("atg", "ctgtatgta"));             
        System.out.println(lastPart("an", "banana"));                       
        System.out.println(lastPart("zoo", "forest"));                      
                             
    }

    
    public static boolean twoOccurrences(String a, String b){
    
    int startIndex = 0;
    int count = 0;
    
    while(startIndex != -1){
      
        startIndex = b.indexOf(a, startIndex);
        if(startIndex != -1){
          count++;
          startIndex += a.length();
        }if(count>=2){
            return true;
        }
    
    }
    
    return false;
    
    }
    
    
    
    public static String lastPart(String a, String b){
    
     int startIndex = 0;
    
     while(startIndex != -1){
        
         startIndex = b.indexOf(a, startIndex);
         if(startIndex != -1){
            
            return b.substring(startIndex+a.length());
            }
         
        }
        
        return b;
   
    }
    
}
