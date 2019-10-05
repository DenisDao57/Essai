package tables;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
public class Revue {
	private static int TableSize = 6; 	// Nombre de colones de la Table
	private static int KeySize =1; 		// Taille de la clé primaire
	public static HashMap<Integer,String> Table = new HashMap <Integer,String>(){{	// Tableau associatif contenant les noms 
		put(1, "id_revue");															// des columns de la table
        put(2, "titre");     
        put(3, "description");
        put(4, "tarif_numero");    
        put(5, "visuel");
        put(6, "id_periodicite"); 
	}};
	
	public static HashMap<Integer,String> LireValeurs(int j, Scanner sc) {
		sc.nextLine();
		int debut,fin;
		switch (j) {
		case 1 : debut = 1; fin = KeySize; break;
		case 2 : debut = KeySize +1 ; fin= TableSize; break;
		default : debut = 1; fin= TableSize; break;
		}
		HashMap<Integer,String> RES = new HashMap<Integer,String>();
		for (int i=debut;i<=fin;i++) {
			System.out.println("Inserez le(la) "+Table.get(i));
			RES.put(i,sc.nextLine());
		}
		return RES;
	}
	
    private static String Delete(HashMap<Integer,String> Datas){
    	String RES;
        try {
        Connection laConnexion = Connexion.creeConnexion();
        PreparedStatement requete = laConnexion.prepareStatement(
                "DELETE from Revue WHERE "+ Table.get(1) +" = ?");
        for (int i=1;i<=KeySize;i++) {
            requete.setString(i, Datas.get(i));
            }       
        RES = (requete.executeUpdate() + " ligne(s) supprimée(s)") ;
        laConnexion.close();
        }catch
         (SQLException sqle) {
        RES = ("Pb delete "+ sqle.getMessage());
        } 
        return RES;
    }
    
    private static String Insert(HashMap<Integer,String> Datas) {        	
    	String RESUL;
        try {
            Connection laConnexion = Connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement(
                    "INSERT INTO Revue ("+ Table.get(2) +", "+ Table.get(3) +", "+ Table.get(4) +", "+ Table.get(5) +", "+ Table.get(6) +")  Values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	        for (int i=KeySize +1 ;i<=TableSize;i++) {requete.setString(i-KeySize, Datas.get(i));}
            requete.executeUpdate();
            
            ResultSet res = requete.getGeneratedKeys();
            if (res.next()) {int cle = res.getInt(1);
            		RESUL = ("Ligne crée avec "+ Table.get(1) + " = " + cle); } else {RESUL ="";}
            
            laConnexion.close();

        }catch
             (SQLException sqle) {
            RESUL = ("Pb Insert : "+ sqle.getMessage());
            
            } 
        return RESUL;
    }
    
    private static String Update(HashMap<Integer,String> Datas) {
    	String RES;
        try {
             Connection laConnexion = Connexion.creeConnexion();
             PreparedStatement requete = laConnexion.prepareStatement(
                     "UPDATE Revue SET "+ Table.get(2) +" =?, "+ Table.get(3) +" =?, "+ Table.get(4) +" =?, "+ Table.get(5) +" =?, "+ Table.get(6) +" = ? WHERE "+ Table.get(1) +" = ?");          
             for (int i=1;i<=KeySize;i++) {
                 requete.setString((TableSize-KeySize+i), Datas.get(i)); 
             }
             for (int i=KeySize+1;i<=TableSize;i++) {
                 requete.setString(i-KeySize, Datas.get(i));
              }
             RES = (requete.executeUpdate() + " ligne(s) modifiée(s)") ;
             laConnexion.close();
        }catch
        (SQLException sqle) {
       RES = ("Pb Update "+ sqle.getMessage());
        } return RES;
    }
    
    public static void Modifier(int j,HashMap<Integer,String> Datas) {
		switch (j) {
		case 1: System.out.println(Delete(Datas));break;
		case 2: System.out.println(Insert(Datas));break;
		case 3:	System.out.println(Update(Datas));break;
		}
    }
}