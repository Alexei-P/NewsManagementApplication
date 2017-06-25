package by.epam.newsmanagement.dao.interfaces;

import java.awt.List;
import java.time.LocalDate;
import java.util.ArrayList;

import by.epam.newsmanagement.entity.News;
import by.epam.newsmanagement.exception.dao.DaoException;

public interface INewsDao {
	public void addNews(String title, LocalDate publicationDate, String author, String contact, String tag,
			String content) throws DaoException;

	public void deleteNews(String title) throws DaoException;

	public void updateNews(String title, String content) throws DaoException;

	public void addComment(String title, String comment) throws DaoException;

	public void deleteComment(String title, int order) throws DaoException;

	public ArrayList<News> getAllNews() throws DaoException;

	public void changeTag(String tag) throws DaoException;

	public void addTag(String tag) throws DaoException;

	public void deleteTag(String tag) throws DaoException;
}
