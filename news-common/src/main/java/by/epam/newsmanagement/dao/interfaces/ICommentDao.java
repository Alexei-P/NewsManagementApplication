package by.epam.newsmanagement.dao.interfaces;

import by.epam.newsmanagement.entity.Comment;
import by.epam.newsmanagement.exception.dao.DaoException;

public interface ICommentDao {
  public void createComment(int newsId, Comment newComment) throws DaoException;

  public void deleteComment(int id) throws DaoException;

  public void changeComment(int id, Comment newComment) throws DaoException;

  public Comment getComment(int id) throws DaoException;
}
