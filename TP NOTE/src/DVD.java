
public class DVD extends Disque{
	private String realisateur;

	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}

	public DVD(String titre, int prixloca, String realisateur) {
		super(titre, prixloca);
		this.realisateur = realisateur;
	}

	@Override
	public String toString() {
		return "DVD [realisateur=" + realisateur + ", Titre="
				+ getTitre() + ", Tarif=" + getTarif() + "]" ;
	}

	public double prixLocation(int nbreJours) {
		int i;
		double valeur=0;
		for (i=0;i<nbreJours;i++)
		{
			if (i<3) {
				valeur+= this.getTarif();
			}
			else valeur+= this.getTarif()/2;
		}
		return valeur;
	}
	
}
