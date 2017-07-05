package by.epam.newsmanagement.service.interfaces;

import by.epam.newsmanagement.entity.author.Author;
import by.epam.newsmanagement.entity.author.AuthorState;
import by.epam.newsmanagement.exception.service.ServiceException;

public interface IAuthorService {
	public void addAuthor(String authorName, AuthorState authorState) throws ServiceException;

	public void deleteAuthorLogically(String authorName) throws ServiceException;

	public void deleteAuthorPhisically(String authorName) throws ServiceException;

	public void updateAuthorState(String authorName, AuthorState authorState) throws ServiceException;
	
	public void getAuthorIdByName(String authorName) throws ServiceException;
	
	public Author getAuthorById (int authorId) throws ServiceException;
}
