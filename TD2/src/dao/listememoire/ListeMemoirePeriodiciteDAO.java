package dao.listememoire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import dao.PeriodiciteDAO;
import metier.PeriodicitePOJO;
import tables.Periodicite;

public class ListeMemoirePeriodiciteDAO implements PeriodiciteDAO {

	private static final int KeySize = 1;
	private static final int TableSize = 2;
	private static ListeMemoirePeriodiciteDAO instance;
	private List<PeriodicitePOJO> donnees;


	public static ListeMemoirePeriodiciteDAO getInstance() {
		if (instance == null) {
			instance = new ListeMemoirePeriodiciteDAO();
		}
		return instance;
		
	}
	private ListeMemoirePeriodiciteDAO() {
		this.donnees = new ArrayList<PeriodicitePOJO>();
		this.donnees.add(new PeriodicitePOJO(1, "Mensuel"));
		this.donnees.add(new PeriodicitePOJO(2, "Quotidien"));
	}

	@Override
	public boolean create(PeriodicitePOJO objet) {

		objet.setId_periodicite(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {
			objet.setId_periodicite(objet.getId_periodicite() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(PeriodicitePOJO objet) {
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
	public boolean delete(PeriodicitePOJO objet) {
		PeriodicitePOJO supprime;
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
	public PeriodicitePOJO getById(int id) {
		for (int i =0; i< this.donnees.size(); i++) {
			if (this.donnees.get(i).getId_periodicite()==id){
				return this.donnees.get(i);
			}
		}
		System.out.println("Aucune résultats en concordance avec l'ID Periodicite "+id );
			return null;
		}
	
	@Override
	public List<PeriodicitePOJO> findAll() {
		return this.donnees;
	}
	
	@Override
	public PeriodicitePOJO getbyLibelle(String Libelle) {
		for (int i =0; i< this.donnees.size(); i++) {
			if (this.donnees.get(i).getLibelle().equalsIgnoreCase(Libelle)){
				return ( this.donnees.get(i));
			}
		}
		System.out.println("Aucune concordance pour Libelle = " + Libelle);
		return null;
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