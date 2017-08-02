package by.epam.newsmanagement.service.interfaces;

import by.epam.newsmanagement.entity.author.Author;
import by.epam.newsmanagement.entity.author.AuthorState;
import by.epam.newsmanagement.exception.service.ServiceException;

public interface IAuthorService {
  public void addAuthor(String authorName, AuthorState authorState) throws ServiceException;

  public void deleteAuthorLogically(int authorId) throws ServiceException;

  public void deleteAuthorPhisically(int authorId) throws ServiceException;

  public void updateAuthorState(Author author, AuthorState authorState) throws ServiceException;

  public Author getAuthorById(int authorId) throws ServiceException;
}
