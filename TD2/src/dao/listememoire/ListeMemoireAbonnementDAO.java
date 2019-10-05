package dao.listememoire;


import dao.*;
import metier.*;
import tables.Abonnement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class ListeMemoireAbonnementDAO implements AbonnementDAO{
	
	private static final int KeySize = 2;
	private static final int TableSize = 4;
	private static ListeMemoireAbonnementDAO instance;
	private List<AbonnementPOJO> donnees;


	public static ListeMemoireAbonnementDAO getInstance() {
		if (instance == null) {
			instance = new ListeMemoireAbonnementDAO();
		}
		return instance;
	}
	
	private ListeMemoireAbonnementDAO() {
		this.donnees = new ArrayList<AbonnementPOJO>();

	}
	
	
	@Override
	public boolean create(AbonnementPOJO objet) {
		
		for(int i=0; i<this.donnees.size();i++) {
			if (this.donnees.get(i).getClient().getId_client()==objet.getClient().getId_client() 
					&&
				this.donnees.get(i).getRevue().getId_revue()==objet.getRevue().getId_revue()){
				System.out.println("L'abonnement existe deja ");
				return false;
			}
		}
		this.donnees.add(objet);
		System.out.println("Abonnement créé");
		return true;
	}

	@Override
	public boolean update(AbonnementPOJO objet) {
		
		for(int i=0; i<this.donnees.size();i++) {
			if (this.donnees.get(i).getClient().getId_client()==objet.getClient().getId_client() 
					&&
				this.donnees.get(i).getRevue().getId_revue()==objet.getRevue().getId_revue()) {
				
				System.out.println("L'abonnement du client n°"+objet.getClient().getId_client()+" à la revue n°"+objet.getRevue().getId_revue()+" a été mis à jour avec succès");
				this.donnees.set(i, objet);
				return true;
			}
		}
		System.out.println("L'abonnement n'existe pas ");
		return false;
		
	}

	@Override
	public boolean delete(AbonnementPOJO objet) {
		for(int i=0; i<this.donnees.size();i++) {
			if (this.donnees.get(i).getClient().getId_client()==objet.getClient().getId_client() 
					&&
				this.donnees.get(i).getRevue().getId_revue()==objet.getRevue().getId_revue()) {
				this.donnees.remove(i);
				System.out.println("L'abonnement du client n°"+objet.getClient().getId_client()+" à la revue n°"+objet.getRevue().getId_revue()+" a été supprimé avec succès");
				return true;
			}
		}
		System.out.println("L'abonnement n'existe pas ");
		return false;
		
	}
	
	
	public int GetNumeroAbonnementbyIdClientIdRevue(int Id_client, int Id_revue) {
		for(int i=0; i<this.donnees.size();i++) {
			if (this.donnees.get(i).getClient().getId_client()==Id_client
					&&
				this.donnees.get(i).getRevue().getId_revue()==Id_revue) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public List<AbonnementPOJO> findAll() {
		return this.donnees;
	}

	@Override
	public List<AbonnementPOJO> findbyIDClient(int id) {
		List<AbonnementPOJO> res = new ArrayList<AbonnementPOJO>();
		for (int i=0;i<this.donnees.size();i++) {
			if (this.donnees.get(i).getClient().getId_client()==id) {
				res.add(this.donnees.get(i));
			}
		}
		return res;
	}

	@Override
	public List<AbonnementPOJO> findbyIDRevue(int id) {
		List<AbonnementPOJO> res = new ArrayList<AbonnementPOJO>();
		for (int i=0;i<this.donnees.size();i++) {
			if (this.donnees.get(i).getRevue().getId_revue()==id) {
				res.add(this.donnees.get(i));
			}
		}
		return res;
	}

	@Override
	public List<AbonnementPOJO> findbyTitreRevue(String Titre) {
		List<AbonnementPOJO> res = new ArrayList<AbonnementPOJO>();
		for (int i=0;i<this.donnees.size();i++) {
			if (this.donnees.get(i).getRevue().getTitre().equalsIgnoreCase(Titre)) {
				res.add(this.donnees.get(i));
			}
		}
		return res;
	}

	@Override
	public List<AbonnementPOJO> findbyNomClient(String Nom) {
		List<AbonnementPOJO> res = new ArrayList<AbonnementPOJO>();
		for (int i=0;i<this.donnees.size();i++) {
			if (this.donnees.get(i).getClient().getNom().equalsIgnoreCase(Nom)) {
				res.add(this.donnees.get(i));
			}
		}
		return res;
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
			System.out.println("Inserez le(la) "+Abonnement.Table.get(i));		// Table contient les noms des collones
			RES.put(i,sc.nextLine());
		}
		
		return new AbonnementPOJO(
					ListeMemoireClientDAO.getInstance().getById(Integer.parseInt(RES.get(1))),
					ListeMemoireRevueDAO.getInstance().getById(Integer.parseInt(RES.get(2))),
					(RES.get(3)),
					(RES.get(4))
				);
			// On renvoie alors un tout nouvel objet correspondant à ce dont on a besoin pour executer une modification de la base de donnée
		}
		
	}


