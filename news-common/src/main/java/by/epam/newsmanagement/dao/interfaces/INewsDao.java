package by.epam.newsmanagement.dao.interfaces;

import java.util.ArrayList;

import by.epam.newsmanagement.entity.Comment;
import by.epam.newsmanagement.entity.News;
import by.epam.newsmanagement.entity.Tag;
import by.epam.newsmanagement.exception.dao.DaoException;

public interface INewsDao {
	public void addNews(News news) throws DaoException;

	public void deleteNewsById(int id) throws DaoException;

	public void editNews(int id, News updatedNews) throws DaoException;

	public void addComment(int id, Comment comment) throws DaoException;

	public void deleteComment(int id) throws DaoException;

	public ArrayList<News> getAllNews() throws DaoException;

	public void addTagToNews(int newsId, Tag tag) throws DaoException;

	public void deleteTagFromNews(int newsId, String tag) throws DaoException;
	
	public ArrayList<String> getCommentsToNews(int id) throws DaoException;
	
	public News getNewsById (int newsIs) throws DaoException;
	
	public ArrayList<News> getTheMostPopularNews (int newsQuantity) throws DaoException;
}
