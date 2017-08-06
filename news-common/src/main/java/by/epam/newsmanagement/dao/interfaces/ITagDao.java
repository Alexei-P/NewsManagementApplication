package by.epam.newsmanagement.dao.interfaces;

import java.util.ArrayList;

import by.epam.newsmanagement.entity.Tag;
import by.epam.newsmanagement.exception.dao.DaoException;

public interface ITagDao {
  public void createTag(Tag tag) throws DaoException;

  public void changeTag(String oldTag, String newTag) throws DaoException;

  public void deleteTag(String tag) throws DaoException;

  public ArrayList<Tag> getAllTags() throws DaoException;
  
  public void deleteTagById(int tagId) throws DaoException;
  
  public Tag getTagById(int tagId) throws DaoException;

  /*public ArrayList<String> getTagsByNewsId(int newsId) throws DaoException;*/
}
