package dao;

import metier.*;
public interface RevueDAO extends EntiteDAO<RevuePOJO> {
	
	public abstract RevuePOJO getbyTitre(String Titre);

}
