package by.epam.test.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.unitils.dbunit.util.DbUnitDatabaseConnection;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

import by.epam.newsmanagement.dao.impl.AdminDao;
import by.epam.newsmanagement.dao.impl.AuthorDao;
import by.epam.newsmanagement.dao.impl.CommentDao;
import by.epam.newsmanagement.dao.impl.NewsDao;
import by.epam.newsmanagement.dao.impl.TagDao;
import by.epam.newsmanagement.dao.impl.UserDao;


@Configuration
@ComponentScan
public class SpringTestConfig {
  
  @Autowired
  private EntityManagerFactory entityManagerFactory;
  
  @Autowired
  private DataSource dataSource;
  
  @Bean
  public EntityManagerFactory entityManagerFactory() {
    System.out.println("EntityManagerFactory!! ================================================");
    return Persistence.createEntityManagerFactory("UnitTestPU");
  }

  @Bean
  public EntityManager entityManager() {
    System.out.println("EntityManager!! ================================================");
    return entityManagerFactory.createEntityManager();
  }
  
  @Bean
  public AdminDao adminDao() {
    return new AdminDao();
  }

  @Bean
  public AuthorDao authorDao() {
    return new AuthorDao();
  }

  @Bean
  public CommentDao commentDao() {
    return new CommentDao();
  }

  @Bean
  public NewsDao newsDao() {
    return new NewsDao();
  }

  @Bean
  public TagDao tagDao() {
    return new TagDao();
  }

  @Bean
  public UserDao userDao() {
    return new UserDao();
  }
  
  @Bean
  public DataSource dataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/newsmnt_copy.minsk.epam.com");
    dataSource.setUsername("unit_test2");
    dataSource.setPassword("Oracle_1");
    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    return dataSource;
  }
  
  @Bean
  public DatabaseConfigBean dbUnitDatabaseConfig() {
      DatabaseConfigBean dbConfig = new com.github.springtestdbunit.bean.DatabaseConfigBean();
      dbConfig.setDatatypeFactory(new org.dbunit.ext.oracle.Oracle10DataTypeFactory());
      return dbConfig;
  }

  @Bean
  public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() {
      DatabaseDataSourceConnectionFactoryBean dbConnection = new com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean(dataSource);
      dbConnection.setDatabaseConfig(dbUnitDatabaseConfig());
      return dbConnection;
  }
}
