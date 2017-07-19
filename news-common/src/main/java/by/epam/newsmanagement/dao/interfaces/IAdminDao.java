package by.epam.newsmanagement.dao.interfaces;

import by.epam.newsmanagement.exception.dao.DaoException;

public interface IAdminDao {

	public void assignAdminRole(int userId) throws DaoException;

	public void disableAdminRole(int userId) throws DaoException;
	
}
