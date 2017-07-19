package by.epam.newsmanagement.service.interfaces;

import by.epam.newsmanagement.exception.service.ServiceException;

public interface IAdminService {

	public void assignAdminRole(int userId) throws ServiceException;

	public void disableAdminRole(int userId) throws ServiceException;
}
