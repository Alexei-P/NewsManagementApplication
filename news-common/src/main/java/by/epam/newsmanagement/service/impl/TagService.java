package by.epam.newsmanagement.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import by.epam.newsmanagement.dao.interfaces.ITagDao;
import by.epam.newsmanagement.entity.News;
import by.epam.newsmanagement.entity.Tag;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.exception.service.ServiceException;
import by.epam.newsmanagement.service.interfaces.ITagService;

public class TagService implements ITagService {

  @Autowired
  ITagDao tagDao;
  
  @Override
  public void attachTagToNews(int newsId, Tag tag) throws ServiceException {
    // TODO Auto-generated method stub

  }

  @Override
  public ArrayList<News> searchNewsByTags(ArrayList<Tag> tagList) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void createTag(Tag tag) throws ServiceException {
    try {
      tagDao.createTag(tag);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }

  }

  @Override
  public void changeTag(String oldTag, String newTag) throws ServiceException {
    try {
      tagDao.changeTag(oldTag, newTag);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }

  }

  @Override
  public void deleteTag(String tag) throws ServiceException {
    try {
      tagDao.deleteTag(tag);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }

  }

  @Override
  public ArrayList<Tag> getAllTags() throws ServiceException {
    ArrayList<Tag> tagList = null;
    try {
      tagList = tagDao.getAllTags();
    } catch (DaoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (tagList == null){
      throw new ServiceException();
    }
    return tagList;
  }

}
