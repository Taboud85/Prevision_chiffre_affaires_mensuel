import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Prevision {
    
    private static final String CSVFILE="prevision.csv";
    private static final String TXTFILE="previsions.txt";
    private static final String STATDIR="liste_des_stats";
    private static final double MOYENNECOMMVOIP = 3637.92;
    private static final double MOYENNESPLA = 8554.34;

  
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
       
       GestionDate gd = new GestionDate();
       GestionStat gs = new GestionStat();
       Scanner sc = null;
       Double[] prevision = new Double[12];
       Arrays.fill(prevision, 0.0);
       DecimalFormat f = new DecimalFormat();
       f.setMaximumFractionDigits(2);
       double grandTotal = 0.0;
       PrintWriter pw = null;
       File statDir = new File(STATDIR);
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
       String date = simpleDateFormat.format(new Date());
       
       
       try{
           pw = new PrintWriter(new FileWriter(STATDIR+File.separator+date+"_"+TXTFILE));
           sc = new Scanner(new FileReader(CSVFILE));
           Stat stat = gs.loadStat(sc);
           statDir.mkdir();
           
           while(stat!=null){
                 int monthInt = stat.getMonthInt();

                 while(monthInt<12){
                    prevision[monthInt] = prevision[monthInt]+stat.getMontant();
                    monthInt = monthInt+stat.getFreqFacturation();
                } 
            stat = gs.loadStat(sc); 
           }
         
          for(int i = 0;i<12;i++){
              
            grandTotal = grandTotal+prevision[i]+MOYENNECOMMVOIP+MOYENNESPLA;
              
            //DISPLAY CONSOLE
            System.out.println(gd.theMonth(i));
            System.out.print("Factures périodiques : ");
            System.out.println(f.format(prevision[i]));
            System.out.print("Moyenne des communications VOIP : ");
            System.out.println(f.format(MOYENNECOMMVOIP));
            System.out.print("Moyenne des SPLA : ");
            System.out.println(f.format(MOYENNESPLA));
            System.out.print("TOTAL : ");
            System.out.println(f.format(prevision[i]+MOYENNECOMMVOIP+MOYENNESPLA));
            System.out.println();
            
            //CREATE A TXT FILE WITH THE STATS
            pw.println(gd.theMonth(i));
            pw.print("Factures périodiques : ");
            pw.println(f.format(prevision[i]));
            pw.print("Moyenne des communications VOIP : ");
            pw.println(f.format(MOYENNECOMMVOIP));
            pw.print("Moyenne des SPLA : ");
            pw.println(f.format(MOYENNESPLA));
            pw.print("TOTAL : ");
            pw.println(f.format(prevision[i]+MOYENNECOMMVOIP+MOYENNESPLA));
            pw.println();
            
            
            
          }
          //DISPLAY CONSOLE
          System.out.print("TOTAL ANNUEL : ");
          System.out.println(f.format(grandTotal));
          
          //CREATE A TXT FILE WITH THE STATS
          pw.print("TOTAL ANNUEL : ");
          pw.println(f.format(grandTotal));
          
          

       }finally{
        if(sc!=null){
            sc.close();
        }
        if(pw!=null){
        pw.close();
        }
       
       }

    }
    
}
