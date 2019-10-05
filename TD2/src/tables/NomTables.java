package tables;

import java.util.HashMap;

public class NomTables {
	public static int NombreTable =4;													// Le nombre de table de la BDD
	public static HashMap<Integer,String> ListeTable = new HashMap<Integer,String>(){{	// Tableau Associatif contenant
		put(1,"Abonnement");															// le noms de tables de la bdd
		put(2,"Client");
		put(3,"Periodicite");
		put(4,"Revue");
	}};
}
