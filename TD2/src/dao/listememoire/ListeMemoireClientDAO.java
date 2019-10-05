package dao.listememoire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import dao.ClientDAO;
import metier.*;
import tables.Client;

public class ListeMemoireClientDAO implements ClientDAO {
	
	private static final int KeySize = 1;
	private static final int TableSize=8;
	private static ListeMemoireClientDAO instance;
	private List<ClientPOJO> donnees;


	public static ListeMemoireClientDAO getInstance() {
		if (instance == null) {
			instance = new ListeMemoireClientDAO();
		}
		return instance;
	}
	
	private ListeMemoireClientDAO() {
		this.donnees = new ArrayList<ClientPOJO>();
	}

	@Override
	public boolean create(ClientPOJO objet) {
		objet.setId_client(1);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {
			objet.setId_client(objet.getId_client() + 1);
		}
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	@Override
	public boolean update(ClientPOJO objet) {
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.donnees.set(idx, objet);
		}
		return true;
	}

	@Override
	public boolean delete(ClientPOJO objet) {
		ClientPOJO supprime;
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public ClientPOJO getById(int id) {
		for (int i =0; i< this.donnees.size(); i++) {
			if (this.donnees.get(i).getId_client()==id){
				return this.donnees.get(i);
			}
		}
		System.out.println("Aucune résultats en concordance avec l'ID Client "+id );
		return null;
	}
	
	public List<ClientPOJO> findAll() {
		return  this.donnees;
	}
	@Override
	public List<ClientPOJO> findbyNom(String Nom) {
		List<ClientPOJO> res = new ArrayList<ClientPOJO>();
		for (int i =0; i< this.donnees.size(); i++) {
			if (this.donnees.get(i).getNom().equalsIgnoreCase(Nom)){
				res.add( this.donnees.get(i));
			}
		}
		return res;
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
		switch (choixoperation) {					// Selon l'opération choisie, seules certains champs sont necessaire, ceux-ci seront donc les seuls lus. 
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