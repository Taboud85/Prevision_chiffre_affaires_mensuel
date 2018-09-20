import java.text.ParseException;
import java.util.Scanner;


public class GestionStat {
    
    
    public Stat loadStat(Scanner sc) throws ParseException{
    GestionDate gd = new GestionDate();
    Stat stat = null;
    sc.useDelimiter(";|\\r\\n");
    if(sc.hasNext()){
        String nomClient = sc.next();
        int monthInt = gd.getIntMonth(sc.next());
        int freqFacturation = setFrequence(sc.next());
        double montant = sc.nextDouble();
        
        //SET THE DEPARTURE MONTH TO GENERATE THE STAT
        //MONTHLY
        if(freqFacturation==1){
          monthInt = 0;  
        }
        
        //QUARTER
        if(freqFacturation==3){
        
            if(monthInt ==2||monthInt ==5||monthInt ==8||monthInt ==11){
            
            monthInt =2;
            }
            if(monthInt ==0||monthInt ==3||monthInt ==6||monthInt ==9){
            
            monthInt =0;
            }
            if(monthInt ==1||monthInt ==4||monthInt ==7||monthInt ==10){
            monthInt =1;
            }
            
        }
        
        //SEMESTER
        if(freqFacturation == 6){
        if(monthInt>5){
        monthInt= monthInt-6;
        }
        
        }
        
        stat = new Stat(nomClient,monthInt,freqFacturation,montant);
    }
       
   return stat; 
   }
    
    public int setFrequence(String freq){
    
        
    Scanner scBase = null;
    Scanner scMonth = null;
    String annee = null;
    int freqFacturation = 0;
     
     try{
         
        scBase = new Scanner(freq);
        annee = scBase.findInLine("année");
        
        if(annee!=null){
            freqFacturation = 12;
        }else{
                scMonth = new Scanner(freq);
                 if(scMonth.hasNextInt()){
                    freqFacturation = scMonth.nextInt();
                }else{
                    freqFacturation = 1;
                }
        }
     }finally{
     
        if(scBase!=null){
            scBase.close();
        }
        if(scMonth!=null){
             scMonth.close();
        }
     }
    return freqFacturation;
    }
}
