package by.epam.newsmanagement.dao.impl;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import by.epam.newsmanagement.dao.factory.DaoFactory;
import by.epam.newsmanagement.dao.interfaces.IAuthorDao;
import by.epam.newsmanagement.dao.interfaces.INewsDao;
import by.epam.newsmanagement.dao.interfaces.ITagDao;
import by.epam.newsmanagement.entity.News;
import by.epam.newsmanagement.entity.Tag;
import by.epam.newsmanagement.entity.author.Author;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.utils.ConnectorDb;

@Repository
@Component
public class NewsDao implements INewsDao {
	public static org.apache.logging.log4j.Logger Logger = org.apache.logging.log4j.LogManager.getLogger("logger");
	
	ITagDao tagDao;
	IAuthorDao authorDao;

	public NewsDao() {
	}

	public ITagDao getTagDao() {
		return tagDao;
	}

	public void setTagDao(ITagDao tagDao) {
		this.tagDao = tagDao;
	}

	public IAuthorDao getAuthorDao() {
		return authorDao;
	}

	public void setAuthorDao(IAuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	public static void main(String[] args) {
		NewsDao newsDao = new NewsDao();
		try {
			ArrayList<News> newsList = newsDao.getAllNews();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addNews(News news) throws DaoException {

		java.sql.Date date = Date.valueOf(news.getPublicationDate());

		try (Connection connection = ConnectorDb.getConnection(); // news
																	// creation
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO news (main_title, short_title, n_content, author_id, "
								+ "n_date, path_to_photo) VALUES(?,?,?,?,?,?)")) {
			ps.setString(1, news.getMainTitle());
			ps.setString(2, news.getShortTitle());
			ps.setString(3, news.getContent());
			ps.setInt(4, news.getAuthor().getId());
			ps.setDate(5, date);
			ps.setString(6, news.getPathToPhoto());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.info("SQLException during news adding");
			e.printStackTrace();
		}

		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		ITagDao tagDao = daoFactory.getTagDao();
		ArrayList<String> tagList = tagDao.getTagsByNewsId(news.getId());

		try (Connection connection = ConnectorDb.getConnection(); // adding tags
																	// to news
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO news_tag (fk_news, fk_tag) VALUES(?,?)")) {
			for (int i = 0; i < tagList.size(); i++) {
				ps.setInt(1, news.getId());
				ps.setString(2, tagList.get(i));
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			Logger.info("SQLException during news adding");
			e.printStackTrace();
		}

	}

	@Override
	public void deleteNewsById(int id) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("DELETE FROM news WHERE id = ?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.info("SQLException during the deletion");
			e.printStackTrace();
		}

	}

	@Override
	public void updateNews(int id, String content) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("UPDATE news SET n_content = ? WHERE id = ?")) {
			ps.setString(1, content);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.info("SQLException during news updating");
			e.printStackTrace();
		}

	}

	@Override
	public void addComment(int id, String comment) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO news_comment (nc_comment, news_id) VALUES(?,?)")) {
			ps.setString(1, comment);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.info("SQLException during adding comment");
			e.printStackTrace();
		}
	}

	@Override
	public void deleteComment(int id) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("DELETE FROM news_comment WHERE c_id = ?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.info("SQLException during comment deletion");
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<News> getAllNews() throws DaoException {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IAuthorDao authorDao = daoFactory.getAuthorDao();
		ITagDao tagDao = daoFactory.getTagDao();
		ResultSet rs = null;
		ArrayList<News> newsList = new ArrayList<News>();
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("SELECT id, main_title, short_title, n_content, author_id,"
								+ "n_date , path_to_photo FROM news")) {
			rs = ps.executeQuery();
			while (rs.next()) {
				News news = new News();
				int id = rs.getInt(1);
				String mainTitle = rs.getString(2);
				String shortTitle = rs.getString(3);
				Clob content_clob = rs.getClob(4);
				int author_id = rs.getInt(5);
				Date publicationDate_old = rs.getDate(6);
				LocalDate publicationDate = publicationDate_old.toLocalDate();
				String pathToPhoto = rs.getString(7);
				Author author = authorDao.getAuthorById(author_id);
				news.setId(id);
				news.setMainTitle(mainTitle);
				news.setShortTitle(shortTitle);
				// TODO add transformation CLOB to Text
				news.setAuthor(author);
				news.setPublicationDate(publicationDate);
				news.setPathToPhoto(pathToPhoto);
				ArrayList<String> comments = getCommentsToNews(id);
				news.setCommentsList(comments);
				ArrayList<String> tagList = tagDao.getTagsByNewsId(id);
				news.setTagList(tagList);
				newsList.add(news);
			}
		} catch (SQLException e) {
			Logger.info("SQLException: getAllNews");
			e.printStackTrace();
		}
		return newsList;
	}

	@Override
	public void addTagToNews(int newsId, String tag) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO news_tag (fk_news, fk_tag)" + "VALUES (?,?)")) {
			ps.setInt(1, newsId);
			ps.setString(2, tag);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.info("SQLException during comment deletion (ResultSet closing)");
			throw new DaoException(e);
		}
	}

	@Override
	public void deleteTagFromNews(int newsId, String tag) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("DELETE FROM news_tag" + "WHERE fk_news = ? AND fk_tag = ?")) {
			ps.setInt(1, newsId);
			ps.setString(2, tag);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.info("SQLException during tag deletion (ResultSet closing)");
			throw new DaoException(e);
		}

	}

	@Override
	public ArrayList<String> getCommentsToNews(int newsId) throws DaoException {
		ResultSet rs = null;
		ArrayList<String> newsList = new ArrayList<String>();
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("SELECT text FROM comment_entity INNER JOIN news_comment "
								+ "ON comment_entity.comment_id = news_comment.comment_id " + "WHERE news_id = ?")) {
			ps.setInt(1, newsId);
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					newsList.add(rs.getString(1));
				}
			}
		} catch (SQLException e) {
			Logger.info("SQLException during getting comments to news");
			throw new DaoException(e);
		} finally {
			try {
				if (rs!=null){
				rs.close();
				}
			} catch (SQLException e) {
				Logger.info("SQLException during getting comments to news");
				throw new DaoException(e);
			}
		}
		return newsList;
	}

	@Override
	public News getNewsById(int newsId) throws DaoException {
		ResultSet rs = null;
		News news = new News();
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("SELECT id, main_title, short_title, n_content, author_id,"
								+ "n_date , path_to_photo FROM news WHERE id = ?")) {
			ps.setInt(1,newsId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String mainTitle = rs.getString(2);
				String shortTitle = rs.getString(3);
				Clob content_clob = rs.getClob(4);
				int author_id = rs.getInt(5);
				Date publicationDate_old = rs.getDate(6);
				LocalDate publicationDate = publicationDate_old.toLocalDate();
				String pathToPhoto = rs.getString(7);
				Author author = authorDao.getAuthorById(author_id);
				news.setId(id);
				news.setMainTitle(mainTitle);
				news.setShortTitle(shortTitle);
				// TODO add transformation CLOB to Text
				news.setAuthor(author);
				news.setPublicationDate(publicationDate);
				news.setPathToPhoto(pathToPhoto);
				ArrayList<String> comments = getCommentsToNews(id);
				news.setCommentsList(comments);
				ArrayList<String> tagList = tagDao.getTagsByNewsId(id);
				news.setTagList(tagList);
			}
		} catch (SQLException e) {
			Logger.info("SQLException: while getting news by Id");
			e.printStackTrace();
		}
		return news;
	}

}
