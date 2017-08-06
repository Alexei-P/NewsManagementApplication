package by.epam.newsmanagement.service.interfaces;

import java.util.ArrayList;

import by.epam.newsmanagement.entity.News;
import by.epam.newsmanagement.entity.Tag;
import by.epam.newsmanagement.exception.service.ServiceException;

public interface ITagService {
  public void attachTagToNews(int newsId, Tag tag) throws ServiceException;

  public ArrayList<News> searchNewsByTags(ArrayList<Tag> tagList) throws ServiceException;

  public void createTag(Tag tag) throws ServiceException;

  public void changeTag(String oldTag, String newTag) throws ServiceException;

  public void deleteTag(String tag) throws ServiceException;

  public ArrayList<Tag> getAllTags() throws ServiceException;
  
  public void deleteTagById(int tagId) throws ServiceException;
  
  public Tag getTagById(int tagId) throws ServiceException;
}
