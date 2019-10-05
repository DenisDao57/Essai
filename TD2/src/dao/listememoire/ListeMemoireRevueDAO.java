package dao.listememoire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import dao.RevueDAO;
import metier.*;
import tables.Revue;

public class ListeMemoireRevueDAO implements RevueDAO {

	private static final int KeySize = 1;
	private static final int TableSize = 6;
	private static ListeMemoireRevueDAO instance;
	private List<RevuePOJO> donnees;


	public static ListeMemoireRevueDAO getInstance() {
		if (instance == null) {
			instance = new ListeMemoireRevueDAO();
		}
		return instance;
		
	}
	private ListeMemoireRevueDAO() {
		this.donnees = new ArrayList<RevuePOJO>();
		this.donnees.add(new RevuePOJO(1, "Le monde", "journal du soir",1.5,"lemondesoir.jpg",ListeMemoirePeriodiciteDAO.getInstance().getById(1)));
		this.donnees.add(new RevuePOJO(2, "Le monde", "journal du midi",1.5,"lemondemidi.jpg",ListeMemoirePeriodiciteDAO.getInstance().getById(1)));
		this.donnees.add(new RevuePOJO(3, "Le monde", "journal du matin",1.5,"lemondematin.jpg",ListeMemoirePeriodiciteDAO.getInstance().getById(1)));
		this.donnees.add(new RevuePOJO(4, "Le Republicain lorrain", "Journal lorrain",1.5,"repu.jpg",ListeMemoirePeriodiciteDAO.getInstance().getById(1)));

	}

	@Override
	public boolean create(RevuePOJO objet) {
		objet.setId_revue(5);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {
			objet.setId_revue(objet.getId_revue() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(RevuePOJO objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.donnees.set(idx, objet);
		}
		return true;
	}

	@Override
	public boolean delete(RevuePOJO objet) {
		RevuePOJO supprime;
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	public RevuePOJO getById(int id) {
		for (int i =0; i< this.donnees.size(); i++) {
			if (this.donnees.get(i).getId_revue()==id){
				return this.donnees.get(i);
			}
		}
		System.out.println("Aucune résultats en concordance avec l'ID Revue "+id );
			return null;
	}

	 
	public List<RevuePOJO> findAll() {
		return  this.donnees;
	}
	
	@Override
	public RevuePOJO getbyTitre(String Titre) {
		for (int i =0; i< this.donnees.size(); i++) {
			if (this.donnees.get(i).getTitre().equalsIgnoreCase(Titre)){
				return(this.donnees.get(i));
			}
		}
		return null;
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
					ListeMemoirePeriodiciteDAO.getInstance().getById(Integer.parseInt(RES.get(6)))
				);
	}
	
}