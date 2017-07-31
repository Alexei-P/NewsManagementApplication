package by.epam.newsmanagement.dao.interfaces;

import by.epam.newsmanagement.entity.user.User;
import by.epam.newsmanagement.exception.dao.DaoException;

public interface IUserDao {
  public User signIn(String login, String password) throws DaoException;

  public void signUp(String login, String password, String name, String email) throws DaoException;

  public void changePassword(String login, String oldPassword, String newPassword)
      throws DaoException;

  public void deleteUser(String login) throws DaoException;

  public boolean isUserExist(String login) throws DaoException;

  public boolean areCredentialsCorrect(String login, String password) throws DaoException;
}
