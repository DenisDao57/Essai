package dao.factory;

import dao.*;
import dao.listememoire.*;

public class ListeMemoireDAOFactory extends DAOFactory {

	@Override
	public ClientDAO getClientDAO() {
		return ListeMemoireClientDAO.getInstance();
	}

	@Override
	public AbonnementDAO getAbonnementDAO() {
		return ListeMemoireAbonnementDAO.getInstance();

	}

	@Override
	public RevueDAO getRevueDAO() {
		return ListeMemoireRevueDAO.getInstance();
	}

	@Override
	public PeriodiciteDAO getPeriodiciteDAO() {
		return ListeMemoirePeriodiciteDAO.getInstance();
	}

}
