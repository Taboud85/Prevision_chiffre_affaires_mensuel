import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class GestionDate {
    
    
    public String theMonth(int month){
    String[] monthNames = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", 
    "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
    return monthNames[month];
    }
    
    public Date toDate(String dateString) throws ParseException{
    Date date = new SimpleDateFormat("dd.MM.yy").parse(dateString);
    return date;
    }
    
    public String getMonthName(String dateString) throws ParseException{
    
    Date date = toDate(dateString);    
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int monthNumber = cal.get(Calendar.MONTH);
    
    return theMonth(monthNumber);
    
    }
    
    public int getIntMonth(String dateString) throws ParseException{
    
    Date date = toDate(dateString);    
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int monthNumber = cal.get(Calendar.MONTH);
    
    return monthNumber;
    
    }
    
    
    
    
}
