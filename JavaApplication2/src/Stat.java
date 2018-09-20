public class Stat {
    
    private final String nomClient;
    private final int monthInt;
    private final int freqFacturation;
    private final Double montant;

    public Stat(String nomClient, int monthInt, int freqFacturation, Double montant) {
        this.nomClient = nomClient;
        this.monthInt = monthInt;
        this.freqFacturation = freqFacturation;
        this.montant = montant;
    }

    public String getNomClient() {
        return nomClient;
    }

    public int getMonthInt() {
        return monthInt;
    }

    public int getFreqFacturation() {
        return freqFacturation;
    }

    public Double getMontant() {
        return montant;
    }

   
    
    
    
}
