package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import dao.ClientDAO;
import metier.ClientPOJO;
import tables.Client;
import tables.Connexion;

public class MySQLClientDAO implements ClientDAO{

	private static final int TableSize = 8;
	private static final int KeySize = 1;
	private static MySQLClientDAO instance;
	private MySQLClientDAO() {}
	public static MySQLClientDAO getInstance() {
		if(instance==null) {
			instance= new MySQLClientDAO();
		}
		return instance;
	}
	
	@Override
	public boolean create(ClientPOJO objet) {
		 try {
             Connection laConnexion = Connexion.creeConnexion();
                PreparedStatement requete = laConnexion.prepareStatement(
                        "INSERT INTO Client Values(?,?,?,?,?,?,?,?)");
                requete.setInt(1, objet.getId_client());
                requete.setString(2, objet.getNom());     
                requete.setString(3, objet.getPrenom());
                requete.setString(4, objet.getNo_rue());
                requete.setString(5, objet.getVoie());
                requete.setString(6, objet.getCode_postal());     
                requete.setString(7, objet.getVille());
                requete.setString(8, objet.getPays());
                boolean bool =(requete.executeUpdate()==1);
                laConnexion.close();
                return bool;
            }catch
            (SQLException sqle) {
                System.out.println("Pb Insert "+ sqle.getMessage());
                return false;
                }	}
	@Override
	public boolean update(ClientPOJO objet) {
		try {
            Connection laConnexion = Connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement(
                    "UPDATE Client SET nom=?,prenom=?,no_rue=?,voie=?,code_postal=?,ville=?,pays=? WHERE id_client=?");
            requete.setString(1, objet.getNom());     
            requete.setString(2, objet.getPrenom());
            requete.setString(3, objet.getNo_rue());
            requete.setString(4, objet.getVoie());
            requete.setString(5, objet.getCode_postal());     
            requete.setString(6, objet.getVille());
            requete.setString(7, objet.getPays());
            requete.setInt(8, objet.getId_client());
            
            boolean bool =(requete.executeUpdate()==1);
            laConnexion.close();
            return bool;
         } catch
			(SQLException sqle) {
      System.out.println("Pb update "+ sqle.getMessage());
      return false;	
       }
	}

	@Override
	public boolean delete(ClientPOJO objet) {
		  try {
	            Connection laConnexion = Connexion.creeConnexion();
	            PreparedStatement requete = laConnexion.prepareStatement(
	                    "DELETE from Client WHERE id_client= ?");
	            requete.setInt(1, objet.getId_client());
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
	public ClientPOJO getById(int id) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"SELECT * FROM Client WHERE id_client=?"
					);
			requete.setInt(1, id);
			ResultSet res= requete.executeQuery();
			
			if (res.next()) {
				return new ClientPOJO(
						res.getInt("id_client"),
						res.getString("nom"),
						res.getString("prenom"),
						res.getString("no_rue"),
						res.getString("voie"),
						res.getString("code_postal"),
						res.getString("ville"),
						res.getString("pays")

						);
			}else {
				System.out.println("Aucune concordance pour id_client = " + id);
				return null;
			}
		}catch
			(SQLException sqle) {
			System.out.println(("Pb select "+ sqle.getMessage()));
			return null;	
		}				}
	@Override
	public List<ClientPOJO> findAll() {
		List<ClientPOJO> liste = new ArrayList<ClientPOJO>();
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"SELECT * FROM Client "
					);
			ResultSet res= requete.executeQuery();
			while (res.next()) {
				liste.add(new ClientPOJO(
							res.getInt("id_client"),
							res.getString("nom"),
							res.getString("prenom"),
							res.getString("no_rue"),
							res.getString("voie"),
							res.getString("code_postal"),
							res.getString("ville"),
							res.getString("pays")

						));
			}
			return liste;
		}catch (SQLException sqle) {
			System.out.println(("Pb select "+ sqle.getMessage()));
			return null;	
		}
	}
	@Override
	public List<ClientPOJO> findbyNom(String Nom) {
		List<ClientPOJO> liste = new ArrayList<ClientPOJO>();
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"SELECT * FROM Client WHERE Nom=?"
					);
			requete.setString(1, Nom);
			ResultSet res= requete.executeQuery();
			while (res.next()) {
				liste.add(new ClientPOJO(
							res.getInt("id_client"),
							res.getString("nom"),
							res.getString("prenom"),
							res.getString("no_rue"),
							res.getString("voie"),
							res.getString("code_postal"),
							res.getString("ville"),
							res.getString("pays")

						));
			}
			return liste;
		}catch (SQLException sqle) {
			System.out.println(("Pb select "+ sqle.getMessage()));
			return null;	
		}
	}
	@Override
	public boolean modifier(int choixoperation, ClientPOJO objet) {
		switch (choixoperation) {
		case 1 : return this.delete(objet); 
		case 2 : return this.create(objet);	
		case 3 : return this.update(objet);
		default : return false;
		}
	}
	@Override
	public ClientPOJO LireValeurs(int choixoperation, Scanner sc) {
		HashMap<Integer,String> RES = new HashMap<Integer,String>();
		sc.nextLine();
		int debut,fin;
		switch (choixoperation) {					// Selon l'opération choisie, seules certains champs sont necessaires, ceux-ci seront donc les seuls lus. 
													// Les autres sont initialisés à  "0" pour eviter les erreurs de type "null pointer" ou les formats invalides
													// lors de l'appel au constructeur a la fin de la fonction

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
			System.out.println("Inserez le(la) "+Client.Table.get(i));		// Table contient les noms des collones
			RES.put(i,sc.nextLine());
		}
		
		return new ClientPOJO(
						Integer.parseInt(RES.get(1)),
						(RES.get(2)),
						(RES.get(3)),
						(RES.get(4)),
						(RES.get(5)),
						(RES.get(6)),
						(RES.get(7)),
						(RES.get(8))
				);
			// On renvoie alors un tout nouvel objet correspondant à ce dont on a besoin pour executer une modification de la base de donnée
	}
}
