package by.epam.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.logging.log4j.Logger;
import by.epam.newsmanagement.dao.interfaces.ITagDao;
import by.epam.newsmanagement.entity.Tag;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.utils.ConnectorDb;

public class TagDao implements ITagDao {
	public static void main(String[] args) { // TEST
		ITagDao tagDao = new TagDao();
		try {
			tagDao.createTag("economics");
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Logger logger = org.apache.logging.log4j.LogManager.getLogger("logger");

	public void createTag(String tag) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectorDb.getConnection();
			ps = connection.prepareStatement("INSERT INTO tag (tag) VALUES(?)");
			ps.setString(1, tag);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			} else {
				logger.info("Connection was not created");
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			} else {
				logger.info("Prepared Statement was not created");
			}
		}

	}

	public void changeTag(String oldTag, String newTag) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectorDb.getConnection();
			ps = connection.prepareStatement("UPDATE tag SET tag = ? WHERE tag = ?");
			ps.setString(1, newTag);
			ps.setString(2, oldTag);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.info("Exception while connection close");
					throw new DaoException(e);
				}
			} else {
				logger.info("Connection was not created");
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			}
		}

	}

	public void deleteTag(String tag) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("DELETE FROM tag WHERE tag = ?")) {
			ps.setString(1, tag);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Connection/Statement was not established");
			e.printStackTrace();
		}
	}

	public ArrayList<Tag> getAllTags() throws DaoException {
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM tag");
				ResultSet rs = ps.executeQuery()) {
				while (rs.next()){
					int id = rs.getInt(1);
					String tag_name = rs.getString(2);
					tagList.add(new Tag(id, tag_name));
				}
		} catch (SQLException e) {
			logger.info("Connection/Statement was not established");
			e.printStackTrace();
		}
		return tagList;
	}

	@Override
	public ArrayList<String> getTagsByNewsId(int newsId) throws DaoException {
		ResultSet rs = null;
		ArrayList<String> tagList = new  ArrayList<String>();
		try(Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT tag FROM TAG INNER JOIN news_tag "
						+ "ON NEWS_TAG.FK_TAG = TAG.TAG_ID WHERE NEWS_TAG.FK_NEWS = ?")){
			ps.setInt(1, newsId);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.info("Connection/Statement was not established");
			throw new DaoException(e);
		}
		
		try {
			while (rs.next()){
				//Tag tag = new Tag();
				//tag.setId(rs.getInt(1));
				//tag.setTag(rs.getString(2));
				String tag = rs.getString(2);		
				tagList.add(tag);
			}
		} catch (SQLException e) {
			logger.info("SQLException during result set parsing");
			throw new DaoException(e);
		}
		return tagList;
	}

}
