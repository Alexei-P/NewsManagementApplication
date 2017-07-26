package by.epam.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.rowset.CachedRowSet;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.epam.newsmanagement.dao.interfaces.IAuthorDao;
import by.epam.newsmanagement.entity.author.Author;
import by.epam.newsmanagement.entity.author.AuthorState;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.utils.ConnectorDb;

@Component
public class AuthorDao implements IAuthorDao {

	public static Logger logger = org.apache.logging.log4j.LogManager.getLogger("logger");

	@Autowired
	private EntityManager entityManager;
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void addAuthor(String authorName, AuthorState authorState) throws DaoException {
		/*try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("INSERT INTO author (author, state) VALUES(?, ?)")) {
			ps.setString(1, authorName);
			ps.setString(2, authorState.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("SQL Exception");
			e.printStackTrace();
		}*/
		Author author = new Author(authorName, authorState);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(author);
		entityTransaction.commit();
		
		entityManager.close();
	}

	@Override
	public void deleteAuthorLogically(Author author) throws DaoException {
/*		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("UPDATE author SET state = D WHERE author = ?")) {
			ps.setString(1, authorName);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("SQL Exception");
			e.printStackTrace();
		}*/
		author.setAuthorState(AuthorState.DELETED);
		EntityTransaction transcation = entityManager.getTransaction();
		transcation.begin();
		entityManager.merge(author);
		transcation.commit();
		
		entityManager.close();
	}

	@Override
	public void deleteAuthorPhisically(Author author) throws DaoException {
		/*try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("DELETE FROM author WHERE author = ?")) {
			ps.setString(1, authorName);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("SQL Exception");
			e.printStackTrace();
		}*/
		EntityTransaction transcation = entityManager.getTransaction();
		transcation.begin();
		entityManager.remove(author);
		transcation.commit();
		
		entityManager.close();
	}

	@Override
	public void updateAuthorState(Author author, AuthorState authorState) throws DaoException {
		/*try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("UPDATE author SET state = ? WHERE author = ?")) {
			ps.setString(1, authorState.toString());
			ps.setString(2, authorName);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("SQL Exception");
			e.printStackTrace();
		}*/
		author.setAuthorState(authorState);
		EntityTransaction transcation = entityManager.getTransaction();
		transcation.begin();
		entityManager.merge(author);
		transcation.commit();
		
		entityManager.close();
	}

	/*@Override
	public void getAuthorIdByName(String authorName) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT id FROM author WHERE author = ?")){
			ps.setString(1, authorName);
			ps.executeQuery();
		} catch (SQLException e) {
			logger.info("SQL Exception");
			e.printStackTrace();
		}
	}*/

	@Override
	public Author getAuthorById(int authorId) throws DaoException {
		/*ResultSet rs = null;
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
		}*/
		Author author = entityManager.find(Author.class, authorId);
		return author;
	}

}
