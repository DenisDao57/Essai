package dao;


import metier.*;

public interface PeriodiciteDAO extends EntiteDAO<PeriodicitePOJO>{
	
	public abstract PeriodicitePOJO getbyLibelle(String Libelle);

}
