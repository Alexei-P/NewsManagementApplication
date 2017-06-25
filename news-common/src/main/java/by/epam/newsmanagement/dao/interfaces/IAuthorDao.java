package by.epam.newsmanagement.dao.interfaces;

import by.epam.newsmanagement.entity.author.AuthorState;
import by.epam.newsmanagement.exception.dao.DaoException;

public interface IAuthorDao {
	public void addAuthor(String authorName, AuthorState authorState, String email) throws DaoException;

	public void deleteAuthorLogically(String authorName) throws DaoException;

	public void deleteAuthorPhisically(String authorName) throws DaoException;

	public void updateAutorState(String authorName) throws DaoException;

	public void updateAuthorEmail(String email) throws DaoException;
}
