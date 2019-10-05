package dao;

import java.util.List;

import metier.*;

public interface AbonnementDAO extends DAO<AbonnementPOJO> {
	
	public abstract List<AbonnementPOJO> findbyIDClient(int id);
	public abstract List<AbonnementPOJO> findbyIDRevue(int id);
	public abstract List<AbonnementPOJO> findbyTitreRevue(String Titre);
	public abstract List<AbonnementPOJO> findbyNomClient(String Nom);




}
