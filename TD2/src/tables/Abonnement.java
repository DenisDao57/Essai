package tables;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
public class Abonnement {
	private static int TableSize = 4; 	// Nombre de columns de la Table
	private static int KeySize =2; 		// Taille de la clé primaire
	public static HashMap<Integer,String> Table = new HashMap <Integer,String>(){{	// Tableau associatif contenant les noms 
		put(1,"id_client");															// des columns de la Table
		put(2,"id_revue");
		put(3,"date_debut");
		put(4,"date_fin");
	}};
	
	public static HashMap<Integer,String> LireValeurs(int j, Scanner sc) {
		sc.nextLine();
		int debut,fin;
		switch (j) {
			case 1 : debut = 1; fin = KeySize; break;
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
                "DELETE from Abonnement  WHERE "+ Table.get(1)+"  = ? AND "+ Table.get(2) +" = ?");
        	for (int i=1;i<=KeySize;i++) {
        		requete.setString(i, Datas.get(i)); }
        	RES = (requete.executeUpdate() +" ligne(s) supprimée(s)");
        	laConnexion.close();
        }catch
         (SQLException sqle) {
        	RES=("Pb delete "+ sqle.getMessage());
        } 
        return RES;
    }
	private static String Insert(HashMap<Integer,String> Datas) {
		String RES;
		try {
		 Connection laConnexion = Connexion.creeConnexion();
	        PreparedStatement requete = laConnexion.prepareStatement(
	                "INSERT INTO Abonnement Values(?,?, STR_TO_DATE(?,'%d/%m/%Y'),STR_TO_DATE(?,'%d/%m/%Y'))");
	        for (int i=1;i<=TableSize;i++) requete.setString(i, Datas.get(i));
            RES=(requete.executeUpdate()+ " ligne(s) crée(s)");
            
            laConnexion.close();
		}catch
		(SQLException sqle) {
	        RES=("Pb Insert "+ sqle.getMessage());
	        }  
		return RES;
	}
	 private static String Update(HashMap<Integer,String> Datas) {
		 String RES;
	        try {
	             Connection laConnexion = Connexion.creeConnexion();
	             PreparedStatement requete = laConnexion.prepareStatement(
	                     "UPDATE Abonnement SET "+Table.get(3)+"=STR_TO_DATE(?,'%d/%m/%Y'), "+Table.get(4)+"=STR_TO_DATE(?,'%d/%m/%Y') WHERE "+Table.get(1)+"=? and "+Table.get(2)+"=?");
	             for (int i=1;i<=KeySize;i++) {
	                 requete.setString((TableSize-KeySize+i), Datas.get(i)); 
	             }
	             for (int i=KeySize+1;i<=TableSize;i++) {
	                 requete.setString(i-KeySize, Datas.get(i));
	              }
	             RES = (requete.executeUpdate() + " ligne(s) modifiée(s)");
	             if (laConnexion != null )
	             	laConnexion.close();
	        }
	        catch
	        (SQLException sqle) {
	      RES =("Pb Update "+ sqle.getMessage());
	    }
	       return RES;
}
	public static void Modifier(int j,HashMap<Integer,String> Datas) {
		switch (j) {
			case 1: System.out.println(Delete(Datas)); break;
			case 2: System.out.println(Insert(Datas)); break;
			case 3:	System.out.println(Update(Datas)); break;
		}
		
	}
}
