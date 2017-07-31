package by.epam.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import by.epam.newsmanagement.dao.interfaces.IAdminDao;
import by.epam.newsmanagement.exception.dao.DaoException;
import by.epam.newsmanagement.utils.ConnectorDb;

@Component
public class AdminDao implements IAdminDao {

  @Override
  public void assignAdminRole(int userId) throws DaoException {
    try (Connection connection = ConnectorDb.getConnection();
        PreparedStatement ps =
            connection.prepareStatement("UPDATE user SET u_role = ADMIN " + "WHERE user_id = ?")) {
      ps.setInt(1, userId);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    }

  }

  @Override
  public void disableAdminRole(int userId) throws DaoException {
    try (Connection connection = ConnectorDb.getConnection();
        PreparedStatement ps =
            connection.prepareStatement("UPDATE user SET u_role = USER " + "WHERE user_id = ?")) {
      ps.setInt(1, userId);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

}
