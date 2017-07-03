package by.epam.newsmanagement.dao.interfaces;

import by.epam.newsmanagement.exception.dao.DaoException;

public interface IAdminDao {
	public void bannUser(String user) throws DaoException;

	public void unbannUser(String user) throws DaoException;

	public void assignAdminRole(String user) throws DaoException;

	public void disableAdminRole(String user) throws DaoException;
	
	public void deleteComment(String newsTitle, String commenId) throws DaoException;
	
}
