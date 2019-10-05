package dao;

import java.util.List;
import java.util.Scanner;


public interface DAO<T> {
	
	public abstract boolean modifier(int choixoperation,T objet); /* La méthode modifier permet d'appeller les methodes 		*
																  *	create update ou delete selon l'argument i et d'après	*
																  * d'apres les donnees contenues dans tables.NomOperations	*
				                                                  * d'éviter d'avoir une main method illisible 				*/
	
	public abstract T LireValeurs(int choixoperation, Scanner sc);		// Fonctionne comme un constructeur mais en demandant les attributs a l'utilisateur via le scanner sc
	
	public abstract List<T> findAll();
	public abstract boolean create(T objet);
	public abstract boolean update(T objet);
	public abstract boolean delete(T objet);
}
