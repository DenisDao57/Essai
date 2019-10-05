package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import dao.AbonnementDAO;
import metier.AbonnementPOJO;
import tables.Abonnement;
import tables.Connexion;

public class MySQLAbonnementDAO implements AbonnementDAO{
	
	private static final int KeySize = 2;
	private static final int TableSize = 4;
	private static MySQLAbonnementDAO instance;
	private MySQLAbonnementDAO() {}
	public static MySQLAbonnementDAO getInstance() {
		if(instance==null) {
			instance= new MySQLAbonnementDAO();
		}
		return instance;
	}
	
	
	@Override
	public  boolean create(AbonnementPOJO objet) {
        try {
         Connection laConnexion = Connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement(
                    "INSERT INTO Abonnement Values(?,?, STR_TO_DATE(?,'%d/%m/%Y'),STR_TO_DATE(?,'%d/%m/%Y'))");
            requete.setInt(1, objet.getClient().getId_client());
            requete.setInt(2, objet.getRevue().getId_revue());     
            requete.setString(3, objet.getDate_debut());
            requete.setString(4, objet.getDate_fin());
            boolean bool =(requete.executeUpdate()==1);
            laConnexion.close();
            return bool;
        }catch
        (SQLException sqle) {
            System.out.println("Pb delete "+ sqle.getMessage());
            return false;
            }   
    }
    @Override
     public  boolean update(AbonnementPOJO objet) {
            try {
                 Connection laConnexion = Connexion.creeConnexion();
                 PreparedStatement requete = laConnexion.prepareStatement(
                         "UPDATE Abonnement SET date_debut=STR_TO_DATE(?,'%d/%m/%Y'),date_fin=STR_TO_DATE(?,'%d/%m/%Y') WHERE id_revue=? and id_client=?");
                 requete.setString(1, objet.getDate_debut());
                 requete.setString(2, objet.getDate_fin());  
                 requete.setInt(4, objet.getClient().getId_client());
                 requete.setInt(3, objet.getRevue().getId_revue());
                  boolean bool =(requete.executeUpdate()==1);
                  laConnexion.close();
                  return bool;
            }
            catch
            (SQLException sqle) {
           System.out.println("Pb delete "+ sqle.getMessage());
           return false;
        }
            
            
}
@Override
public  boolean delete(AbonnementPOJO objet){
        try {
        Connection laConnexion = Connexion.creeConnexion();
        PreparedStatement requete = laConnexion.prepareStatement(
                "DELETE from Abonnement  WHERE id_revue = ? AND id_client= ?");
        requete.setInt(1, objet.getRevue().getId_revue());
        requete.setInt(2, objet.getClient().getId_client());
        boolean bool =(requete.executeUpdate()==1);
        laConnexion.close();
        return bool;
        }catch
         (SQLException sqle) {
        System.out.println("Pb delete "+ sqle.getMessage());
        return false;
        } 
    }
@Override
public List<AbonnementPOJO> findAll() {
	List<AbonnementPOJO> liste = new ArrayList<AbonnementPOJO>();
	try {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"SELECT id_client,id_revue, DATE_FORMAT(date_debut,'%d/%m/%Y') as date_debut,DATE_FORMAT(date_fin,'%d/%m/%Y') as date_fin  FROM Abonnement"
				);
		ResultSet res= requete.executeQuery();
		while (res.next()) {
			liste.add(new AbonnementPOJO(
						MySQLClientDAO.getInstance().getById(res.getInt("id_client")),
						MySQLRevueDAO.getInstance().getById(res.getInt("id_revue")),
						res.getString("date_debut"),
						res.getString("date_fin")
					));
		}
		return liste;
	}catch (SQLException sqle) {
		System.out.println(("Pb select "+ sqle.getMessage()));
		return null;	
	}
}

@Override
public List<AbonnementPOJO> findbyIDClient(int id) {
	List<AbonnementPOJO> liste = new ArrayList<AbonnementPOJO>();
	try {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"SELECT id_client,id_revue, DATE_FORMAT(date_debut,'%d/%m/%Y') as date_debut,DATE_FORMAT(date_fin,'%d/%m/%Y') as date_fin  "
				+ "FROM Abonnement "
				+ "WHERE id_client =?"
				);
		requete.setInt(1, id);
		ResultSet res= requete.executeQuery();
		
		while (res.next()) {
			liste.add(new AbonnementPOJO(
						MySQLClientDAO.getInstance().getById(res.getInt("id_client")),
						MySQLRevueDAO.getInstance().getById(res.getInt("id_revue")),
						res.getString("date_debut"),
						res.getString("date_fin")
					));
		}
		return liste;
	}catch (SQLException sqle) {
		System.out.println(("Pb select "+ sqle.getMessage()));
		return null;	
	}
}
@Override
public List<AbonnementPOJO> findbyIDRevue(int id) {
	List<AbonnementPOJO> liste = new ArrayList<AbonnementPOJO>();
	try {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"SELECT id_client,id_revue, DATE_FORMAT(date_debut,'%d/%m/%Y') as date_debut,DATE_FORMAT(date_fin,'%d/%m/%Y') as date_fin  FROM Abonnement WHERE id_revue =?"
				);
		requete.setInt(1, id);
		ResultSet res= requete.executeQuery();
		
		while (res.next()) {
			liste.add(new AbonnementPOJO(
						MySQLClientDAO.getInstance().getById(res.getInt("id_client")),
						MySQLRevueDAO.getInstance().getById(res.getInt("id_revue")),
						res.getString("date_debut"),
						res.getString("date_fin")
					));
		}
		return liste;
	}catch (SQLException sqle) {
		System.out.println(("Pb select "+ sqle.getMessage()));
		return null;	
	}
}
@Override
public List<AbonnementPOJO> findbyTitreRevue(String Titre) {
	List<AbonnementPOJO> liste = new ArrayList<AbonnementPOJO>();
	try {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"SELECT id_client,Revue.id_revue as id_revue, DATE_FORMAT(date_debut,'%d/%m/%Y') as date_debut,DATE_FORMAT(date_fin,'%d/%m/%Y') as date_fin  "
				+ "FROM Abonnement,Revue "
				+ "WHERE Abonnement.id_revue =Revue.id_revue"
				+ "	AND titre = ?"
				);
		requete.setString(1, Titre);
		ResultSet res= requete.executeQuery();
		
		while (res.next()) {
			liste.add(new AbonnementPOJO(
						MySQLClientDAO.getInstance().getById(res.getInt("id_client")),
						MySQLRevueDAO.getInstance().getById(res.getInt("id_revue")),
						res.getString("date_debut"),
						res.getString("date_fin")
					));
		}
		return liste;
	}catch (SQLException sqle) {
		System.out.println(("Pb select "+ sqle.getMessage()));
		return null;	
	}
}
@Override
public List<AbonnementPOJO> findbyNomClient(String Nom) {
	List<AbonnementPOJO> liste = new ArrayList<AbonnementPOJO>();
	try {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"SELECT Client.id_client as id_client,id_revue, DATE_FORMAT(date_debut,'%d/%m/%Y') as date_debut,DATE_FORMAT(date_fin,'%d/%m/%Y') as date_fin  "
				+ "FROM Abonnement,Client "
				+ "WHERE Abonnement.id_client =Client.id_client"
				+ "	AND Nom = ?"
				);
		requete.setString(1, Nom);
		ResultSet res= requete.executeQuery();
		
		while (res.next()) {
			liste.add(new AbonnementPOJO(
						MySQLClientDAO.getInstance().getById(res.getInt("id_client")),
						MySQLRevueDAO.getInstance().getById(res.getInt("id_revue")),
						res.getString("date_debut"),
						res.getString("date_fin")
					));
		}
		return liste;
	}catch (SQLException sqle) {
		System.out.println(("Pb select "+ sqle.getMessage()));
		return null;	
	}
}
@Override
public boolean modifier(int choixoperation, AbonnementPOJO objet) {
	switch (choixoperation) {
	case 1 : return this.delete(objet); 
	case 2 : return this.create(objet);	
	case 3 : return this.update(objet);
	default : return false;
	}
}
@Override
public AbonnementPOJO LireValeurs(int choixoperation, Scanner sc) {
	HashMap<Integer,String> RES = new HashMap<Integer,String>();
	sc.nextLine();
	int debut,fin;
	switch (choixoperation) {					// Selon l'opération choisie, seules certains champs sont necessaire, ceux-ci seront donc les seuls lus. 
												// Les autres sont initialisés à  "0" pour eviter les erreurs de type "null pointer" ou les formats invalides
												// Lors de l'appel au constructeur a la fin de la fonction
		case 1 : 
			debut = 1; 
			fin = KeySize; 
			for (int j=KeySize+1;j<=TableSize;j++) RES.put(j,"0"); 
		break;
				 
		default : 
			debut = 1; 
			fin= TableSize; 
		break;
	}
	
	for (int i=debut;i<=fin;i++) {
		System.out.println("Inserez le(la) "+Abonnement.Table.get(i));		// Table contient les noms des collones
		RES.put(i,sc.nextLine());
	}
	
	return new AbonnementPOJO(
				MySQLClientDAO.getInstance().getById(Integer.parseInt(RES.get(1))),
				MySQLRevueDAO.getInstance().getById(Integer.parseInt(RES.get(2))),
				(RES.get(3)),
				(RES.get(4))
			);
}
}
