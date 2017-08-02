package by.epam.newsmanagement.dao.interfaces;

import by.epam.newsmanagement.entity.author.Author;
import by.epam.newsmanagement.entity.author.AuthorState;
import by.epam.newsmanagement.exception.dao.DaoException;

public interface IAuthorDao {
  public void addAuthor(String authorName, AuthorState authorState) throws DaoException;

  public void deleteAuthorLogically(int authorId) throws DaoException;

  public void deleteAuthorPhisically(int authorId) throws DaoException;

  public void updateAuthorState(Author author, AuthorState authorState) throws DaoException;

  public Author getAuthorById(int authorId) throws DaoException;
}
