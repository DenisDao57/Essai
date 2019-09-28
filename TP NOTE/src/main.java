import java.util.ArrayList;
import java.util.Iterator;

public class main {

	public static void main(String[] args) {
		CD cd1 = new CD("Denis et le mysterieux moine Shaolin",50,2019);
		DVD dvd1 = new DVD("Comment lancer des couteaux",25,"Michel Dumas");
		System.out.println(cd1.prixLocation(5) + " / " + dvd1.prixLocation(5));
		ArrayList<Disque> loue = new ArrayList<>();
		ArrayList<Disque> test = new ArrayList<>();
		Occasionnel oca = new Occasionnel("Naruto",loue);
		Regulier reg = new Regulier("KONO DIO DA",loue);
		oca.Ajouter(cd1);
		oca.Ajouter(dvd1);
		reg.Ajouter(cd1);
		reg.Ajouter(dvd1);
		System.out.println(oca.factureDetaillee(5));
		System.out.println(reg.factureDetaillee(5));
	}

}
