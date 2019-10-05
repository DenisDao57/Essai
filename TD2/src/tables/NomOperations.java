package tables;

import java.util.HashMap;

public class NomOperations {
	public static final int  NombreOperations =4;													// Le nombre d'opérations prévues
	public static HashMap<Integer,String> ListeOperations = new HashMap<Integer,String>(){{	// Tableau Associatif contenant
		put(1,"Delete");																	// le noms des opérations pour
		put(2,"Insert");																	// faciliter l'affichage des opérations
		put(3,"Update");
		put(4,"Select");
	
	}};
}


