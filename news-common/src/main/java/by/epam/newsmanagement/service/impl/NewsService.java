package by.epam.newsmanagement.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import by.epam.newsmanagement.dao.impl.CommentDao;
import by.epam.newsmanagement.dao.impl.NewsDao;
import by.epam.newsmanagement.dao.interfaces.ICommentDao;
import by.epam.newsmanagement.dao.interfaces.INewsDao;
import by.epam.newsmanagement.entity.Comment;
import by.epam.newsmanagement.entity.News;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.exception.service.ServiceException;
import by.epam.newsmanagement.service.interfaces.INewsService;

@Service
public class NewsService implements INewsService {

  public static org.apache.logging.log4j.Logger Logger =
      org.apache.logging.log4j.LogManager.getLogger("logger");

  @Autowired
  private INewsDao newsDao;
  @Autowired
  private ICommentDao commentDao;

  public NewsService() {
    super();
  }

  public INewsDao getNewsDao() {
    return newsDao;
  }

  public void setNewsDao(NewsDao newsDao) {
    this.newsDao = newsDao;
  }

  public ICommentDao getCommentDao() {
    return commentDao;
  }

  public void setCommentDao(CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  // TODO delete main for test
  public static void main(String[] args) {
    ApplicationContext ac = new ClassPathXmlApplicationContext("Beans.xml");
    NewsService newsService = (NewsService) ac.getBean("newsService");
    ArrayList<News> newsList = null;
    try {
      newsList = newsService.getAllNews();
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(newsList);
  }

  @Override
  public ArrayList<News> getAllNews() throws ServiceException {
    ArrayList<News> newsList = null;
    try {
      newsList = newsDao.getAllNews();
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
    return newsList;
  }

  @Override
  public ArrayList<News> getTheMostPopularNews(int newsQuantity) throws ServiceException {
    ArrayList<News> newsList = null;
    try {
      newsList = newsDao.getTheMostPopularNews(newsQuantity);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
    return newsList;
  }


  @Override
  public News getNews(int newsId) throws ServiceException {
    News news = null;
    try {
      news = newsDao.getNewsById(newsId);
    } catch (DaoException e) {
      Logger.info("SQLException during news getting");
      throw new ServiceException(e);
    }

    return news;
  }

  @Override
  public void addNews(News news) throws ServiceException {
    try {
      newsDao.addNews(news);
    } catch (DaoException e) {
      Logger.info("SQLException during news adding");
      throw new ServiceException(e);
    }
  }

  @Override
  public void editNews(int newsId, News updatedNews) throws ServiceException {
    try {
      newsDao.editNews(newsId, updatedNews);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }

  }

  @Override
  public void deleteNews(int newsId) throws ServiceException {
    try {
      newsDao.deleteNewsById(newsId);
    } catch (DaoException e) {
      Logger.info("SQLException during comment deletion");
      throw new ServiceException(e);
    }
  }

  @Override
  public void addComment(int newsId, Comment comment) throws ServiceException {
    try {
      commentDao.createComment(newsId, comment);
    } catch (DaoException e) {
      Logger.info("SQLException during comment adding");
      throw new ServiceException(e);
    }

  }

  @Override
  public void deleteComment(int commentId) throws ServiceException {
    try {
      newsDao.deleteComment(commentId);
    } catch (DaoException e) {
      Logger.info("SQLException during comment deletion");
      throw new ServiceException(e);
    }
  }
}
