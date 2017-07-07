package by.epam.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import org.apache.logging.log4j.Logger;

import by.epam.newsmanagement.dao.interfaces.IAuthorDao;
import by.epam.newsmanagement.entity.author.Author;
import by.epam.newsmanagement.entity.author.AuthorState;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.utils.ConnectorDb;

public class AuthorDao implements IAuthorDao {

	public static Logger logger = org.apache.logging.log4j.LogManager.getLogger("logger");

	@Override
	public void addAuthor(String authorName, AuthorState authorState) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("INSERT INTO author (author, state) VALUES(?, ?)")) {
			ps.setString(1, authorName);
			ps.setString(2, authorState.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("SQL Exception");
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAuthorLogically(String authorName) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("UPDATE author SET state = D WHERE author = ?")) {
			ps.setString(1, authorName);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("SQL Exception");
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAuthorPhisically(String authorName) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("DELETE FROM author WHERE author = ?")) {
			ps.setString(1, authorName);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("SQL Exception");
			e.printStackTrace();
		}
	}

	@Override
	public void updateAuthorState(String authorName, AuthorState authorState) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("UPDATE author SET state = ? WHERE author = ?")) {
			ps.setString(1, authorState.toString());
			ps.setString(2, authorName);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("SQL Exception");
			e.printStackTrace();
		}

	}

	@Override
	public void getAuthorIdByName(String authorName) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT id FROM author WHERE author = ?")){
			ps.setString(1, authorName);
			ps.executeQuery();
		} catch (SQLException e) {
			logger.info("SQL Exception");
			e.printStackTrace();
		}
		
	}

	@Override
	public Author getAuthorById(int authorId) throws DaoException {
		ResultSet rs = null;
		Author author = new Author();
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM author WHERE a_id = ?")){
			ps.setInt(1, authorId);
			rs = ps.executeQuery();
			while (rs.next()){
				int i = rs.getInt(3);
				String name = rs.getString(1);
				String authorState = rs.getString(2);
				author.setId(i);
				author.setName(name);
				author.setAuthorState(AuthorState.valueOf(authorState));
			}
		} catch (SQLException e) {
			logger.info("SQL Exception select author by Id");
			throw new DaoException(e);
		}
		
		/*try {
			while (rs.next()){
				int i = rs.getInt(3);
				String name = rs.getString(1);
				String authorState = rs.getString(2);
				author.setId(i);
				author.setName(name);
				author.setAuthorState(AuthorState.valueOf(authorState));
			}
		} catch (SQLException e) {
			logger.info("SQL Exception while result set parsing");
			throw new DaoException(e);
		}*/
		return author;
	}

}
