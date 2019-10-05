package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import dao.RevueDAO;
import metier.RevuePOJO;
import tables.Connexion;
import tables.Revue;

public class MySQLRevueDAO implements RevueDAO{

	private static final int KeySize = 1;
	private static final int TableSize = 6;
	private static MySQLRevueDAO instance;
	private MySQLRevueDAO() {}
	public static MySQLRevueDAO getInstance() {
		if(instance==null) {
			instance= new MySQLRevueDAO();
		}
		return instance;
	}
	
	@Override
	public boolean create(RevuePOJO objet) {
		 try {
	            Connection laConnexion = Connexion.creeConnexion();
	            PreparedStatement requete = laConnexion.prepareStatement(
	                    "INSERT INTO Revue Values(?,?,?,?,?,?)");
	            requete.setInt(1, objet.getId_revue());
	            requete.setString(2, objet.getTitre());     
	            requete.setString(3, objet.getDescription());
	            requete.setDouble(4, objet.getTarif_numero());    
	            requete.setString(5, objet.getVisuel());
	            requete.setInt(6, objet.getPeriodicite().getId_periodicite());    
	            boolean bool =(requete.executeUpdate()==1);
	            laConnexion.close();
	            return bool;  
	        }catch
	            (SQLException sqle) {
	            System.out.println("Pb create "+ sqle.getMessage());
	            return false;
	        } 
	}

	@Override
	public boolean update(RevuePOJO objet) {
		try {
            Connection laConnexion = Connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement(
                    "UPDATE Revue SET titre=?,description=?,tarif_numero=?,visuel=?,id_periodicite=? WHERE id_revue=?");
            requete.setString(1, objet.getTitre());     
            requete.setString(2, objet.getDescription());
            requete.setDouble(3, objet.getTarif_numero());    
            requete.setString(4, objet.getVisuel());
            requete.setInt(5, objet.getPeriodicite().getId_periodicite()); 
            requete.setInt(6, objet.getId_revue());     
            boolean bool =(requete.executeUpdate()==1);
            laConnexion.close();
            return bool;  
       }catch
			(SQLException sqle) {
    	   	System.out.println("Pb update "+ sqle.getMessage());
    	   	return false;	
      }
	}
	
	@Override
	public boolean delete(RevuePOJO objet) {
		        try {
		        Connection laConnexion = Connexion.creeConnexion();
		        PreparedStatement requete = laConnexion.prepareStatement(
		                "DELETE from Revue WHERE id_revue = ?");
		        requete.setInt(1, objet.getId_revue());
		        boolean bool =(requete.executeUpdate()==1);
	            laConnexion.close();
	            return bool;  
		        }catch
		        (SQLException sqle) {
		        System.out.println(("Pb delete "+ sqle.getMessage()));
		        return false;
		        } 
		        
		    }
	@Override
	public RevuePOJO getById(int id) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"SELECT * FROM Revue WHERE id_revue=?"
					);
			requete.setInt(1, id);
			ResultSet res= requete.executeQuery();
			
			if (res.next()) {
				return new RevuePOJO(
						res.getInt("id_revue"),
						res.getString("titre"),
						res.getString("description"),
						res.getDouble("tarif_numero"),
						res.getString("visuel"),
						MySQLPeriodiciteDAO.getInstance().getById(res.getInt("id_periodicite"))
						);
			}else {
				System.out.println("Aucune concordance pour id_revue = " + id);
				return null;
			}
		}catch
			(SQLException sqle) {
			System.out.println(("Pb select "+ sqle.getMessage()));
			return null;	
		}			
	}
	@Override
	public List<RevuePOJO> findAll() {
		try {
			List<RevuePOJO> liste = new ArrayList<RevuePOJO>();
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"SELECT * FROM Revue"
					);
			ResultSet res= requete.executeQuery();
			
			while (res.next()) {
				liste.add( new RevuePOJO(
								res.getInt("id_revue"),
								res.getString("titre"),
								res.getString("description"),
								res.getDouble("tarif_numero"),
								res.getString("visuel"),
								MySQLPeriodiciteDAO.getInstance().getById(res.getInt("id_periodicite"))
							));
			}
			return liste;
			}
		catch
			(SQLException sqle) {
			System.out.println(("Pb select "+ sqle.getMessage()));
			return null;	
		}			
	}
	
	@Override
	public RevuePOJO getbyTitre(String Titre) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"SELECT * FROM Revue WHERE titre=?"
					);
			requete.setString(1, Titre);
			ResultSet res= requete.executeQuery();
			
			if (res.next()) {
				return new RevuePOJO(
						res.getInt("id_revue"),
						res.getString("titre"),
						res.getString("description"),
						res.getDouble("tarif_numero"),
						res.getString("visuel"),
						MySQLPeriodiciteDAO.getInstance().getById(res.getInt("id_periodicite"))
						);
			}else {
				System.out.println("Aucune concordance avec Titre = " + Titre);
				return null;
			}
		}catch
			(SQLException sqle) {
			System.out.println(("Pb select "+ sqle.getMessage()));
			return null;	
		}			
	}
	@Override
	public boolean modifier(int choixoperation, RevuePOJO objet) {
		switch (choixoperation) {
		case 1 : return this.delete(objet); 
		case 2 : return this.create(objet);	
		case 3 : return this.update(objet);
		default : return false;
		}
	}
	@Override
	public RevuePOJO LireValeurs(int choixoperation, Scanner sc) {
		HashMap<Integer,String> RES = new HashMap<Integer,String>();
		sc.nextLine();
		int debut,fin;
		switch (choixoperation) {
									// Selon l'opération choisie, seules certains champs sont necessaire, ceux-ci seront donc les seuls lus. 
									// Les autres sont initialisés à  "0" pour eviter les erreurs de type "null pointer" ou les formats invalides
									// Lors de l'appel au constructeur a la fin de la fonction
			case 1 : 
				debut = 1; 
				fin = KeySize; 
				for (int j=KeySize+1;j<=TableSize;j++) RES.put(j,"0");
			break;
					 
			case 2 : 
				debut = KeySize+1; 
				fin= TableSize; 
				for (int j=1;j<=KeySize;j++) RES.put(j, "0");
			break;
			
			default : 
				debut = 1; 
				fin= TableSize; 
			break;
		}
		
		for (int i=debut;i<=fin;i++) {
			System.out.println("Inserez le(la) "+Revue.Table.get(i));
			RES.put(i,sc.nextLine());
		}
		return new RevuePOJO(
					Integer.parseInt(RES.get(1)),
					RES.get(2),
					RES.get(3),
					Double.parseDouble(RES.get(4)),
					RES.get(5),
					MySQLPeriodiciteDAO.getInstance().getById(Integer.parseInt(RES.get(6)))
				);
	}
}
