
public abstract class Disque implements Prix {
private String titre;
private int tarif;
public String getTitre() {
	return titre;
}
public void setTitre(String titre) {
	this.titre = titre;
}
public int getTarif() {
	return tarif;
}
public void setTarif(int tarif) {
	this.tarif = tarif;
}
public Disque(String titre, int tarif) {
	super();
	this.titre = titre;
	this.tarif = tarif;
}





}
