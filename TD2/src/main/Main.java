package main;
import java.util.Scanner;
import dao.factory.*;
import tables.*;
public class Main {	
public static void main(String[] args) {
	
	// déclarations des variables
	DAOFactory daos = null;
	int continuerouquitter;
	boolean CONTINUER;
	Scanner sc= new Scanner(System.in);
	int critereInt;
	String critereString;
	int choixcritere=0,choixpersistance=0,choixtable=0,choixoperation=0;	// Une ribambelle d'entier pour gérer toutes les interactions avec l'utilisateur
	System.out.println("Bienvenue dans votre gestion de BDD par ligne de commande !");
	
	// La quasi totalité du main est dans ce do ... while que l'on quittera si on le souhaite apres avoir executé une requete
	
	do {
		
		// Choix de la Persistance
		
		do { 
			System.out.println("Quelle Persistance souhaitez-vous utiliser ?");
			System.out.println("1 : MySQL");
			System.out.println("2 : Liste Memoire");
			choixpersistance=sc.nextInt();
		}while (choixpersistance!=1 && choixpersistance!=2);
		
		switch(choixpersistance) {
			case 1 : 	
				daos = DAOFactory.getDAOFactory(Persistance.MySQL);
				System.out.println("Vous avez choisi la persitance "+ choixpersistance +" : MySQL");
			break;
			
			default : 
				daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire); 	
				System.out.println("Vous avez choisi la persitance "+ choixpersistance +" : ListeMemoire");
			break;
		}
		
		// Fin Choix Persistance
		
		// Choix de la table
		
			/* Afin de Faciliter leur ecriture et leur lecture, les noms des tables sont	*
			 * stockées dans un tableau assiociatif dans la classe table.NomTables			*/	
		
		do { 
			System.out.println("Sur quelle Table souhaitez-vous travailler ?");
			for (int n=1;n<=NomTables.NombreTable;n++) System.out.println(n + " : " + NomTables.ListeTable.get(n));
			choixtable=sc.nextInt();
		}while (choixtable<1 || choixtable>NomTables.NombreTable);
		System.out.println("Vous avez choisi la table " + choixtable + " : " + NomTables.ListeTable.get(choixtable));
		
		/* Comme pour les noms de tables, les noms d'opérations sont	*
		 * eux aussi stockés de la meme façon dans tables.NomOperations	*/
		
	do {	
		System.out.println("Quelle opération  souhaitez vous effectuer sur cette table ?");	
		for (int n=1;n<=NomOperations.NombreOperations;n++) System.out.println(n + " : " + NomOperations.ListeOperations.get(n));
		choixoperation=sc.nextInt(); 
	}while (choixoperation<1 || choixoperation>NomOperations.NombreOperations);
		
		switch(choixoperation) {
// ---------------------------------------------------------------------------------------------------------------------------------------------------------- //			
// ---------------------------------------------------------------------------------------------------------------------------------------------------------- //				
		case 4 : 		// Correspond au SELECT
				switch(choixtable) {
// ---------------------------------------------------------------------------------------------------------------------------------------------------------- //			
						case 1: // Abonnement
							
							do { 
								System.out.println("D'après quel critere souhaitez-vous selectionner les abonnements ?");
								System.out.println(" 1 : Tout selectionner");
								System.out.println(" 2 : Par ID Client");
								System.out.println(" 3 : Par Nom de Client");
								System.out.println(" 4 : Par Id Revue");
								System.out.println(" 5 : Par Titre de Revue");
								choixcritere=sc.nextInt();
							}while (choixcritere<1 || choixcritere>5);
							
							switch(choixcritere) {
								case 1 : 
									System.out.println(daos.getAbonnementDAO().findAll()); 
								break;
								
								case 2 : 
									do {
										System.out.println("entrez l'ID Client souhaité");
										critereInt=sc.nextInt();
									}while (daos.getClientDAO().getById(critereInt)== null);
									
									System.out.println(daos.getAbonnementDAO().findbyIDClient(critereInt));
								break;	
								
								case 3 :
									do {
										sc.nextLine();
										System.out.println("entrez le nom de Client souhaité");
										critereString=sc.nextLine();
									}while (daos.getClientDAO().findbyNom(critereString)== null);
										
									System.out.println(daos.getAbonnementDAO().findbyNomClient(critereString));
								break;	
								
								case 4 :
									do {
										System.out.println("entrez l'ID Revue souhaité");
										critereInt=sc.nextInt();
									}while (daos.getRevueDAO().getById(critereInt)== null);
										
									System.out.println(daos.getAbonnementDAO().findbyIDRevue(critereInt));
								break;	
								
								case 5 :
									do {
										sc.nextLine();
										System.out.println("entrez le Titre de Revue souhaité");
										critereString=sc.nextLine();
									}while (daos.getRevueDAO().getbyTitre(critereString)== null);
										
									System.out.println(daos.getAbonnementDAO().findbyNomClient(critereString));
								break;	
							}
						break;	
// ---------------------------------------------------------------------------------------------------------------------------------------------------------- //			
						case 2: // Client
							
							do { 
								System.out.println("D'après quel critere souhaitez-vous selectionner les Clients ?");
								System.out.println(" 1 : Tout selectionner");
								System.out.println(" 2 : Par ID Client");
								System.out.println(" 3 : Par Nom de Client");
								choixcritere=sc.nextInt();
							}while (choixcritere<1 || choixcritere>3);
							switch(choixcritere) {
								case 1 : 
									System.out.println(daos.getClientDAO().findAll()); 
								break;
								
								case 2 : 
									do {
										System.out.println("entrez l'ID Client souhaité");
										critereInt=sc.nextInt();
									}while (daos.getClientDAO().getById(critereInt)== null);
									
									System.out.println(daos.getClientDAO().getById(critereInt));
								break;	
								
								case 3 :
									do {
										sc.nextLine();
										System.out.println("entrez le nom de Client souhaité");
										critereString=sc.nextLine();
									}while (daos.getClientDAO().findbyNom(critereString)== null);
										
									System.out.println(daos.getClientDAO().findbyNom(critereString));
								break;	
							}
						break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------- //									
						case 3: // Periodicite
							
							do { 
								System.out.println("D'après quel critere souhaitez-vous selectionner les Periodicités ?");
								System.out.println(" 1 : Tout selectionner");
								System.out.println(" 2 : Par ID Periodicite");
								System.out.println(" 3 : Par Libelle");
								choixcritere=sc.nextInt();
							}while (choixcritere<1 || choixcritere>3);
							
							switch(choixcritere) {
								case 1 : 
									System.out.println(daos.getPeriodiciteDAO().findAll()); 
								break;
								
								case 2 : 
									do {
										System.out.println("entrez l'ID Periodicite");
										critereInt=sc.nextInt();
									}while (daos.getPeriodiciteDAO().getById(critereInt)== null);
									
									System.out.println(daos.getPeriodiciteDAO().getById(critereInt));
								break;	
								
								case 3 :
									do {
										sc.nextLine();
										System.out.println("entrez le Libelle");
										critereString=sc.nextLine();
									}while (daos.getPeriodiciteDAO().getbyLibelle(critereString)== null);
										
									System.out.println(daos.getPeriodiciteDAO().getbyLibelle(critereString));
								break;		
							}
						break;	
// ---------------------------------------------------------------------------------------------------------------------------------------------------------- //					
						case 4: // Revue
							
							do { 
								System.out.println("D'après quel critere souhaitez-vous selectionner les Revues ?");
								System.out.println(" 1 : Tout selectionner");
								System.out.println(" 2 : Par ID Revue");
								System.out.println(" 3 : Par Titre");
								choixcritere=sc.nextInt();
							}while (choixcritere<1 || choixcritere>3);
							
							switch(choixcritere) {
								case 1 : 
									System.out.println(daos.getPeriodiciteDAO().findAll()); 
								break;
								
								case 2 : 
									do {
										System.out.println("entrez l'ID Revue");
										critereInt=sc.nextInt();
									}while (daos.getPeriodiciteDAO().getById(critereInt)== null);
									
									System.out.println(daos.getRevueDAO().getById(critereInt));
								break;	
								
								case 3 :
									do {
										sc.nextLine();
										System.out.println("entrez le Titre Revue");
										critereString=sc.nextLine();
									}while (daos.getRevueDAO().getbyTitre(critereString)== null);
										
									System.out.println(daos.getRevueDAO().getbyTitre(critereString));
								break;		
						}
						break;	
				}
			
			do {
				// ...
			}while(false);
			break;
// ---------------------------------------------------------------------------------------------------------------------------------------------------------- //			
// ---------------------------------------------------------------------------------------------------------------------------------------------------------- //			
		default :		// correspond aux modifications
				
						System.out.println("Vous avez choisi l'Opération " + choixoperation + " : " + NomOperations.ListeOperations.get(choixoperation));
					
								/* L'appel à la fonction Modifier permet de gérer en dehors du main l'appel a update, create ou delete 	*/
						
								/* De meme, LireValeurs permet de gérer en dehors du main la Lecture des données 	*
								 * Pour permettre à l'utilisateur de n'entrer que les valeurs pertinentes			*
								 * en fonction de l'opération choisie                                               */
								
						switch(choixtable) {
							case 1: daos.getAbonnementDAO().modifier(	choixoperation,	daos.getAbonnementDAO().LireValeurs(	choixoperation, sc	));		break;
							case 2: daos.getClientDAO().modifier(		choixoperation,	daos.getClientDAO().LireValeurs(		choixoperation, sc	));		break;
							case 3: daos.getPeriodiciteDAO().modifier(	choixoperation,	daos.getPeriodiciteDAO().LireValeurs(	choixoperation, sc	));		break;
							case 4:	daos.getRevueDAO().modifier(		choixoperation,	daos.getRevueDAO().LireValeurs(			choixoperation, sc	));		break;
						}
						break;
						
			
			}
		
		
		
		// Continuer ou Quitter
		do {
			System.out.println("Souhaitez vous continuer de travailler sur votre base de données ou quitter ? ");
			System.out.println(" 1 : CONTINUER ");
			System.out.println(" 2 : QUITTER");
			continuerouquitter = sc.nextInt();
			CONTINUER= continuerouquitter==1;
		}while(continuerouquitter != 1 && continuerouquitter!=2);
		
	}while(CONTINUER);
	// Si l'utilisateur a décidé de quitter, fin de l'execution, sinon on recommence autant de fois que souhaité
	sc.close();
	
	System.out.println(daos.getRevueDAO().findAll());
	
}
}
