package dao;

import java.util.List;

import metier.*;

public interface ClientDAO extends EntiteDAO<ClientPOJO>{
	
	
	public abstract List<ClientPOJO> findbyNom(String Nom);

}
