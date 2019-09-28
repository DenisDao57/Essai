
public class CD extends Disque implements Prix{
	private int datesortie;

	public int getDatesortie() {
		return datesortie;
	}

	public void setDatesortie(int datesortie) {
		this.datesortie = datesortie;
	}

	public CD(String titre, int tarif, int datesortie) {
		super(titre, tarif);
		this.datesortie = datesortie;
	}

	@Override
	public String toString() {
		return "CD [datesortie=" + datesortie + ", Titre=" + getTitre() + ", Tarif=" + getTarif() + "]";
	}
	
	public double prixLocation (int nbreJours) {
		double valeur = nbreJours*this.getTarif();
		return valeur;
	}
}
