package dao;


public interface EntiteDAO<T> extends DAO<T>{

	public abstract T getById(int id); 
	
}
