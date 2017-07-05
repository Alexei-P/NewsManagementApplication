package by.epam.newsmanagement.service.interfaces;

import by.epam.newsmanagement.entity.Comment;
import by.epam.newsmanagement.exception.service.ServiceException;

public interface ICommentService {
	public void createCommentToNews(int newsId, Comment comment) throws ServiceException;
	
	public void deleteCommentFromNews(Comment comment) throws ServiceException;
	
	public void updateCommentToNews(int newsId, Comment comment) throws ServiceException;
	
	public Comment getComment(int commentId) throws ServiceException;
	
}
