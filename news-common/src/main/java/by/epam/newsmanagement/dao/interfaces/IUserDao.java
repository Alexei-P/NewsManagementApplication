package by.epam.newsmanagement.dao.interfaces;

import by.epam.newsmanagement.exception.dao.DaoException;

public interface IUserDao {
	public void signIn(String login, String password) throws DaoException;

	public void signUp(String login, String password, String email) throws DaoException;

	public void changePassword(String login, String oldPassword, String newPassword) throws DaoException;

	public void deleteUser(String login, String role) throws DaoException;
}
