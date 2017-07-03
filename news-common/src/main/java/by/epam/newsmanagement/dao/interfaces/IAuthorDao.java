package by.epam.newsmanagement.dao.interfaces;

import by.epam.newsmanagement.entity.author.Author;
import by.epam.newsmanagement.entity.author.AuthorState;
import by.epam.newsmanagement.exception.dao.DaoException;

public interface IAuthorDao {
	public void addAuthor(String authorName, AuthorState authorState) throws DaoException;

	public void deleteAuthorLogically(String authorName) throws DaoException;

	public void deleteAuthorPhisically(String authorName) throws DaoException;

	public void updateAuthorState(String authorName, AuthorState authorState) throws DaoException;
	
	public void getAuthorIdByName(String authorName) throws DaoException;
	
	public Author getAuthorById (int authorId) throws DaoException;
}
