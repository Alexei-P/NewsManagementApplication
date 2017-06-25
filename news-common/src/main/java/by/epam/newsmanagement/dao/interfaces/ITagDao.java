package by.epam.newsmanagement.dao.interfaces;

import by.epam.newsmanagement.exception.dao.DaoException;

public interface ITagDao {
	public void createTag(String tag) throws DaoException;

	public void changeTag(String oldTag, String newTag) throws DaoException;

	public void deleteTag(String tag) throws DaoException;
}
