package by.epam.newsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.newsmanagement.dao.interfaces.IAuthorDao;
import by.epam.newsmanagement.entity.author.Author;
import by.epam.newsmanagement.entity.author.AuthorState;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.exception.service.ServiceException;
import by.epam.newsmanagement.service.interfaces.IAuthorService;

@Service
public class AuthorService implements IAuthorService {
	
	@Autowired
	private IAuthorDao authorDao;
	
	public IAuthorDao getAuthorDao() {
		return authorDao;
	}

	public void setAuthorDao(IAuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	@Override
	public void addAuthor(String authorName, AuthorState authorState) throws ServiceException {
		try {
			authorDao.addAuthor(authorName, authorState);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void deleteAuthorLogically(String authorName) throws ServiceException {
		try {
			authorDao.deleteAuthorLogically(authorName);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void deleteAuthorPhisically(String authorName) throws ServiceException {
		try {
			authorDao.deleteAuthorPhisically(authorName);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void updateAuthorState(String authorName, AuthorState authorState) throws ServiceException {
		try {
			authorDao.updateAuthorState(authorName, authorState);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void getAuthorIdByName(String authorName) throws ServiceException {
		try {
			authorDao.getAuthorIdByName(authorName);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public Author getAuthorById(int authorId) throws ServiceException {
		Author author = null;
		try {
			author = authorDao.getAuthorById(authorId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return author;
	}

}
