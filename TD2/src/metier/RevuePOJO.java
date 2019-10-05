package metier;


public class RevuePOJO {
    private int id_revue;
    private String titre;
    private String description;
    private double tarif_numero;
    private String visuel;
    private PeriodicitePOJO periodicite;
    
    
    public RevuePOJO(int id_revue, String titre, String description, double tarif_numero, String visuel,
            PeriodicitePOJO periodicite) {
        super();
        this.id_revue = id_revue;
        this.titre = titre;
        this.description = description;
        this.tarif_numero = tarif_numero;
        this.visuel = visuel;
        this.periodicite = periodicite;
    }
    public int getId_revue() {
        return id_revue;
    }
    public void setId_revue(int id_revue) {
        this.id_revue = id_revue;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getTarif_numero() {
        return tarif_numero;
    }
    public void setTarif_numero(double tarif_numero) {
        this.tarif_numero = tarif_numero;
    }
    public String getVisuel() {
        return visuel;
    }
    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }
    public PeriodicitePOJO getPeriodicite() {
        return periodicite;
    }
    public void setPeriodicite(PeriodicitePOJO periodicite) {
        this.periodicite = periodicite;
    }
    @Override
    public String toString() {
    	String newLine = System.getProperty("line.separator");
        return "RevuePOJO [id_revue=" + id_revue + ", titre=" + titre + ", description=" + description
                + ", tarif_numero=" + tarif_numero + ", visuel=" + visuel + "  Periodicite =" + periodicite +"]"+newLine;
    }
    
}