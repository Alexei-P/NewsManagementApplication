package by.epam.newsmanagement.service.interfaces;

import java.util.ArrayList;

import by.epam.newsmanagement.entity.News;
import by.epam.newsmanagement.exception.service.ServiceException;

public interface INewsService {
	public ArrayList<News> getAllNews() throws ServiceException;
	
	public ArrayList<News> getTheMostPopularNews (int numberOfComments) throws ServiceException;
	
	public News getNews (int newsId) throws ServiceException;
	
	public void addNews (News news) throws ServiceException;
	
	public void editNews(int newsId) throws ServiceException;
	
	public void deleteNews(int newsId) throws ServiceException;
	
	public void addComment() throws ServiceException;
	
	
}
