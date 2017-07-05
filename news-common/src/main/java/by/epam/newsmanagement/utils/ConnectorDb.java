package by.epam.newsmanagement.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectorDb {
	private static HikariDataSource ds;
	static {
//		Properties properties = new Properties();
//		InputStream is = null;
		/*String url = null;
		String user = null;
		String password = null;*/
		/*try {
			is = new FileInputStream(
					"D:\\lab_workspace\\newsmnt\\news-common\\src\\main\\resources\\by.epam.database\\db.properties");
			properties.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}*/
		/*url = properties.getProperty("db.url");
		user = properties.getProperty("db.user");
		password = properties.getProperty("db.password");*/

		/*HikariConfig config = new HikariConfig();
		ds.set*/
		/*config.setJdbcUrl(url);
		config.setUsername(user);
		config.setPassword(password);
		config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");*/
		//ds = new HikariDataSource(config);
	}

	/*public static void main(String[] args) throws SQLException {
		Connection connection = ConnectorDb.getConnection();
	}
*/
	/*public static ConnectorDb getInstance() {
		if (connector != null) {
			return new ConnectorDb();
		} else
			return connector;
	}*/

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}
