import java.util.ArrayList;
import java.util.Iterator;

public class Regulier extends Client{

	public Regulier(String nom, ArrayList<Disque> loue) {
		super(nom, loue);
	}
	
	public String factureDetaillee(int nbreJours) {
		double prix = 0;
		Disque prop ;
		String Facture = "Facture Client :"+this.getNom()+"\n"+" Nombre d'articles loues : " +this.getLoue().size();
		Iterator<Disque> ic = this.getLoue().iterator();
		while (ic.hasNext())
		{
			prop=ic.next();
			prix+=prop.prixLocation(nbreJours);
			Facture +="\n"+ prop.toString();
			
		}
		prix = prix*0.8;
		Facture  += "\n" + " Nombre de jour de location : " + nbreJours + "\n" + " Montant à payer = " + prix;
		
		return Facture;
	}

}
