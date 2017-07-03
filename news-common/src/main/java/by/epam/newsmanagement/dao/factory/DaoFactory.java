package by.epam.newsmanagement.dao.factory;

import by.epam.newsmanagement.dao.impl.AdminDao;
import by.epam.newsmanagement.dao.impl.AuthorDao;
import by.epam.newsmanagement.dao.impl.NewsDao;
import by.epam.newsmanagement.dao.impl.TagDao;
import by.epam.newsmanagement.dao.impl.UserDao;

public class DaoFactory {
	private DaoFactory() {
	}

	private static DaoFactory daoFactory;

	private AdminDao adminDao = new AdminDao();
	private AuthorDao authorDao = new AuthorDao();
	private NewsDao newsDao = new NewsDao();
	private TagDao tagDao = new TagDao();
	private UserDao userDao = new UserDao();

	public static DaoFactory getDaoFactory() {
		if (daoFactory == null) {
			daoFactory = new DaoFactory();
		} 
		return daoFactory;
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public AuthorDao getAuthorDao() {
		return authorDao;
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public TagDao getTagDao() {
		return tagDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}
}
