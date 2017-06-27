package by.epam.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

import by.epam.newsmanagement.dao.interfaces.IUserDao;
import by.epam.newsmanagement.entity.User;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.utils.ConnectorDb;

public class UserDao implements IUserDao {
	public static void main(String[] args) {
		IUserDao userDao = new UserDao();
		try {
			userDao.signUp("Alexei", "1234", "Alexei", "alexei_parkhomchyk@epam.com");
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	public static Logger logger = org.apache.logging.log4j.LogManager.getLogger("logger");

	@Override
	public User signIn(String login, String password) throws DaoException {
		ResultSet rs = null;
		User user = null;
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("SELECT u_name FROM news_user where u_login = ? and u_password = ?")) {
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString(1));
			}
		} catch (SQLException e) {
			logger.info("Connection/Statement was not created");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void signUp(String login, String password, String name, String email) throws DaoException {
		boolean isUserExist = isUserExist(login);
		if (isUserExist == false) {
			try (Connection connection = ConnectorDb.getConnection();
					PreparedStatement ps = connection.prepareStatement(
							"INSERT INTO news_user (u_login, u_password, u_name, u_email) VALUES (?,?,?,?)")) {
				ps.setString(1, login);
				ps.setString(2, password);
				ps.setString(3, name);
				ps.setString(4, email);
				ps.executeUpdate();
			} catch (SQLException e) {
				logger.info("Connection/Statement was not created");
				e.printStackTrace();
			}
		}
	}


	@Override
	public void changePassword(String login, String oldPassword, String newPassword) throws DaoException {
		boolean areCredentialsCorrect = areCredentialsCorrect(login, oldPassword);
		if (areCredentialsCorrect) {
			try (Connection connection = ConnectorDb.getConnection();
					PreparedStatement ps = connection
							.prepareStatement("UPDATE news_user SET u_password = ? WHERE u_password = ?")) {
				ps.setString(1, login);
				ps.setString(2, oldPassword);
				ps.setString(3, newPassword);
			} catch (SQLException e) {
				logger.info("Connection/Statement was not created");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteUser(String login) throws DaoException {
		boolean isUserExist = isUserExist(login);
		if (isUserExist == true) {
			try (Connection connection = ConnectorDb.getConnection();
					PreparedStatement ps = connection.prepareStatement("DELETE FROM news_user WHERE u_login = ?")) {
				ps.setString(1, login);
				ps.executeUpdate();
			} catch (SQLException e) {
				logger.info("Connection/Statement was not created");
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isUserExist(String login) throws DaoException {
		boolean isExist = false;
		ResultSet rs = null;
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT u_name FROM news_user where u_login = ?")) {
			ps.setString(1, login);
			rs = ps.executeQuery();
			if (rs.next()) {
				isExist = true;
			}
		} catch (SQLException e) {
			logger.info("Connection/Statement was not created");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.info("Result set closing exception");
				e.printStackTrace();
			}
		}
		return isExist;
	}

	@Override
	public boolean areCredentialsCorrect(String login, String password) throws DaoException {
		boolean areCorrect = false;
		ResultSet rs = null;
		try (Connection connection = ConnectorDb.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("SELECT u_name FROM news_user where u_login = ? and u_password = ?")) {
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				areCorrect = true;
			}
		} catch (SQLException e) {
			logger.info("Connection/Statement was not created");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.info("Result set closing exception");
				e.printStackTrace();
			}
		}
		return areCorrect;
	}

}
