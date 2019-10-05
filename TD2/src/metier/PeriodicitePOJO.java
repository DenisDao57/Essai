package metier;

public class PeriodicitePOJO {
    private int id_periodicite;
    private String libelle;
    
    // constructeur
    
    public PeriodicitePOJO(int id_periodicite, String libelle) {
        super();
        this.id_periodicite = id_periodicite;
        this.libelle = libelle;
    }
    
    // getters & setters
    
    public int getId_periodicite() {
        return id_periodicite;
    }
    public void setId_periodicite(int id_periodicite) {
        this.id_periodicite = id_periodicite;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    //ToString
    
    @Override
    public String toString() {
    	String newLine = System.getProperty("line.separator");
        return "PeriodicitePOJO [id_periodicite=" + id_periodicite + ", Libelle=" + libelle + "]"+newLine;
    }
    
    
}
