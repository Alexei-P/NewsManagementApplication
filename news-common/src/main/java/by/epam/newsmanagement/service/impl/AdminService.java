package by.epam.newsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.newsmanagement.dao.interfaces.IAdminDao;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.exception.service.ServiceException;
import by.epam.newsmanagement.service.interfaces.IAdminService;

@Service
public class AdminService implements IAdminService {

  @Autowired
  private IAdminDao adminDao;

  public IAdminDao getAdminDao() {
    return adminDao;
  }

  public void setAdminDao(IAdminDao adminDao) {
    this.adminDao = adminDao;
  }

  @Override
  public void assignAdminRole(int userId) throws ServiceException {
    try {
      adminDao.assignAdminRole(userId);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public void disableAdminRole(int userId) throws ServiceException {
    try {
      adminDao.disableAdminRole(userId);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }

  }

}
