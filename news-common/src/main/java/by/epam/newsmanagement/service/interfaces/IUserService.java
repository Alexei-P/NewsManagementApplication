package by.epam.newsmanagement.service.interfaces;

import by.epam.newsmanagement.entity.User;
import by.epam.newsmanagement.exception.service.ServiceException;

public interface IUserService {
	public User signIn(String login, String password) throws ServiceException;

	public void signUp(String login, String password, String name, String email) throws ServiceException;

	public void changePassword(String login, String oldPassword, String newPassword) throws ServiceException;

	public void deleteUser(String login) throws ServiceException;

	public boolean isUserExist(String login) throws ServiceException;

	public boolean areCredentialsCorrect(String login, String password) throws ServiceException;
}
