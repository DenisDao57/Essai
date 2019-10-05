package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import dao.PeriodiciteDAO;
import metier.*;
import tables.*;

public class MySQLPeriodiciteDAO implements PeriodiciteDAO{
	
	private static final int KeySize = 1;
	private static final int TableSize = 2;
	private static MySQLPeriodiciteDAO instance;
	private MySQLPeriodiciteDAO() {}
	public static MySQLPeriodiciteDAO getInstance() {
		if(instance==null) {
			instance= new MySQLPeriodiciteDAO();
		}
		return instance;
	}

	@Override
	public boolean create(PeriodicitePOJO objet) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"INSERT INTO Periodicite  Values(?,?)");
	        requete.setInt(1,objet.getId_periodicite());
	        requete.setString(2,objet.getLibelle());
	        boolean bool =(requete.executeUpdate()==1);
            laConnexion.close();
            return bool;      
			} 
			catch
			 (SQLException sqle) {
			System.out.println(("Pb insert : "+ sqle.getMessage()));
			return false;
			} 
	}

	@Override
	public boolean update(PeriodicitePOJO objet) {
		 try {
             Connection laConnexion = Connexion.creeConnexion();
             PreparedStatement requete = laConnexion.prepareStatement(
                     "UPDATE Periodicite SET libelle =? WHERE id_periodicite =?");
                 requete.setString(1, objet.getLibelle()); 
                 requete.setInt(2, objet.getId_periodicite());
                 boolean bool =(requete.executeUpdate()==1);
                 laConnexion.close();
                 return bool;
        }
        catch
        (SQLException sqle) {
       System.out.println("Pb Update "+ sqle.getMessage());
       return false;
        }	
	}

	@Override
	public boolean delete(PeriodicitePOJO objet) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"DELETE from Periodicite WHERE id_periodicite = ?");
			requete.setInt(1, objet.getId_periodicite());
			boolean bool =(requete.executeUpdate()==1);
            laConnexion.close();
            return bool;
			} 
			catch
			 (SQLException sqle) {
			System.out.println(("Pb delete "+ sqle.getMessage()));
			return false;
			} 
	}
	@Override
	public PeriodicitePOJO getById(int id) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"SELECT * FROM Periodicite WHERE id_periodicite=?"
					);
			requete.setInt(1, id);
			ResultSet res= requete.executeQuery();
			
			if (res.next()) {
				return new PeriodicitePOJO(
						res.getInt("id_periodicite"),
						res.getString("Libelle")
						);
			}else {
				System.out.println("Aucune concordance pour id_periodicite = " + id);
				return null;
			}
		}catch
			(SQLException sqle) {
			System.out.println(("Pb select "+ sqle.getMessage()));
			return null;	
		}			
	}
	
	@Override
	public List<PeriodicitePOJO> findAll() {
			List<PeriodicitePOJO> liste = new ArrayList<PeriodicitePOJO>();
			try {
				Connection laConnexion = Connexion.creeConnexion();
				PreparedStatement requete = laConnexion.prepareStatement(
						"SELECT * FROM Periodicite "
						);
				ResultSet res= requete.executeQuery();
				while (res.next()) {
					liste.add( new PeriodicitePOJO(
							res.getInt("id_periodicite"),
							res.getString("Libelle")
							));
				}			
				return liste;
			}catch
				(SQLException sqle) {
				System.out.println(("Pb select "+ sqle.getMessage()));
				return null;	
			}
	}
	@Override
	public PeriodicitePOJO getbyLibelle(String Libelle) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"SELECT * FROM Periodicite WHERE Libelle=?"
					);
			requete.setString(1, Libelle);
			ResultSet res= requete.executeQuery();
			
			if (res.next()) {
				return new PeriodicitePOJO(
						res.getInt("id_periodicite"),
						res.getString("Libelle")
						);
			}else {
				System.out.println("Aucune concordance pour Libelle = " + Libelle);
				return null;
			}
		}catch
			(SQLException sqle) {
			System.out.println(("Pb select "+ sqle.getMessage()));
			return null;	
		}
	}
	@Override
	public boolean modifier(int choixoperation, PeriodicitePOJO objet) {
		switch (choixoperation) {
		case 1 : return this.delete(objet); 
		case 2 : return this.create(objet);	
		case 3 : return this.update(objet);
		default : return false;
		}
	}
	@Override
	public PeriodicitePOJO LireValeurs(int choixoperation, Scanner sc) {
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
			System.out.println("Inserez le(la) "+Periodicite.Table.get(i));
			RES.put(i,sc.nextLine());
		}
		
		return new PeriodicitePOJO(
					Integer.parseInt(RES.get(1)),
					RES.get(2)
				);
	}

}
