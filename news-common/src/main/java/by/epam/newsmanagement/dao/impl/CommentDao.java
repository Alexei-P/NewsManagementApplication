package by.epam.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.epam.newsmanagement.dao.interfaces.IAuthorDao;
import by.epam.newsmanagement.dao.interfaces.ICommentDao;
import by.epam.newsmanagement.entity.Comment;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.utils.ConnectorDb;

@Component
public class CommentDao implements ICommentDao{

	@Autowired
	EntityManager entityManager;
	
	private IAuthorDao authorDao;
	
	public IAuthorDao getAuthorDao() {
		return authorDao;
	}

	public void setAuthorDao(IAuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	public static org.apache.logging.log4j.Logger Logger = org.apache.logging.log4j.LogManager.getLogger("logger");
	@Override
	public void createComment(int newsId, Comment newComment) throws DaoException {
		ResultSet generatedKeys = null;
		int insertedCommentId = 0;
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO comment_entity (text, author_id, publication_date) VALUES(?,?,?)",
								Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, newComment.getText());
			ps.setInt(2, newComment.getAuthor().getId());
			ps.setDate(3, Date.valueOf(newComment.getPublicationDate()));
			ps.executeUpdate();
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()){
				insertedCommentId = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			Logger.info("SQLException during adding comment");
			throw new DaoException(e);
		}finally{
			if (generatedKeys==null){
				Logger.info("ID for insert was not generated");
				throw new DaoException();
			}
			else {
				try {
					generatedKeys.close();
				} catch (SQLException e) {
					Logger.info("Can't close result set");
					throw new DaoException(e);
				}
			}
		}
		
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("INSERT INTO news_comment (news_id, comment_id) VALUES(?,?)")
				){
			ps.setInt(1, newsId);
			ps.setInt(2, insertedCommentId); //is exception above enough?
			ps.executeUpdate();
		}catch(SQLException e){
			Logger.info("SQL exception in table insertion");
			throw new DaoException(e);
		}
		
	}

	@Override
	public void deleteComment(int commentId) throws DaoException {
		/*try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("DELETE FROM comment_entity WHERE comment_id = ?")
				){
			ps.setInt(1, commentId);
			ps.executeUpdate();
		}catch(SQLException e){
			Logger.info("SQL exception during comment deletion");
			throw new DaoException(e);
		}*/
		Comment comment = entityManager.find(Comment.class, commentId);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(comment);
		transaction.commit();
		
		entityManager.close();		
	}

	@Override
	public void changeComment(int id, Comment newComment) throws DaoException {
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("UPDATE comment_entity SET text = ?"
						+ "WHERE comment_id = ?")
				){
			ps.setString(1, newComment.getText());
			ps.setInt(2, id);
			ps.executeUpdate();
		}catch(SQLException e){
			Logger.info("SQL exception during comment change");
			throw new DaoException(e);
		}
		
	}

	@Override
	public Comment getComment(int id) throws DaoException {
		
		Comment comment = new Comment();
		ResultSet rs = null;
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM comment_entity WHERE comment_id = ?")
				){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs!=null){
				while(rs.next()){
					comment.setId(rs.getInt(1));
					comment.setText(rs.getString(2));
					comment.setAuthor(authorDao.getAuthorById(rs.getInt(3)));
					comment.setPublicationDate(rs.getDate(4).toLocalDate());
				}
			}else {
				Logger.info("Get comment result set - null");
				throw new DaoException();
			}
		}catch(SQLException e){
			Logger.info("SQL exception during comment getting");
			throw new DaoException(e);
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				Logger.info("SQL exception during result set closing");
				throw new DaoException(e);
			}
		}
		return comment;
	}

}
