package by.epam.newsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.newsmanagement.dao.interfaces.IUserDao;
import by.epam.newsmanagement.entity.user.User;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.exception.service.ServiceException;
import by.epam.newsmanagement.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {
  @Autowired
  private IUserDao userDao;

  public IUserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(IUserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public User signIn(String login, String password) throws ServiceException {
    User user = null;
    try {
      user = userDao.signIn(login, password);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
    return user;
  }

  @Override
  public void signUp(String login, String password, String name, String email)
      throws ServiceException {
    try {
      userDao.signUp(login, password, name, email);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }

  }

  @Override
  public void changePassword(String login, String oldPassword, String newPassword)
      throws ServiceException {
    try {
      userDao.changePassword(login, oldPassword, newPassword);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }

  }

  @Override
  public void deleteUser(String login) throws ServiceException {
    try {
      userDao.deleteUser(login);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean isUserExist(String login) throws ServiceException {
    boolean isExist = false;
    try {
      isExist = userDao.isUserExist(login);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
    return isExist;
  }

  @Override
  public boolean areCredentialsCorrect(String login, String password) throws ServiceException {
    boolean areCorrect = false;
    try {
      areCorrect = userDao.areCredentialsCorrect(login, password);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
    return areCorrect;
  }


}
