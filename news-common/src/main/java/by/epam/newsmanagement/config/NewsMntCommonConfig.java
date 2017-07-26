package by.epam.newsmanagement.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import by.epam.newsmanagement.dao.impl.*;
import by.epam.newsmanagement.service.impl.NewsService;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan
public class NewsMntCommonConfig {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Bean
	public AdminDao adminDao(){
		return new AdminDao();
	}
	
	@Bean
	public AuthorDao authorDao(){
		return new AuthorDao();
	}
	
	@Bean
	public CommentDao commentDao(){
		return new CommentDao();
	}
	
	@Bean
	public NewsDao newsDao(){
		return new NewsDao();
	}
	
	@Bean
	public TagDao tagDao(){
		return new TagDao();
	}
	
	@Bean
	public UserDao userDao(){
		return new UserDao();
	}
	
	@Bean
	public NewsService newsService(){
		System.out.println("NewsService ================================================");
		return new NewsService();
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(){
/*		Properties prop = new Properties();
		prop.put("eclipselink.persistencexml", "/news-common/src/main/resources/META-INF/persistence.xml");*/
		System.out.println("EntityManagerFactory ================================================");
		return Persistence.createEntityManagerFactory( "FirstJPAPU");
	}
	
	@Bean
	public EntityManager entityManager(){
		System.out.println("EntityManager!! ================================================");
		return entityManagerFactory.createEntityManager();
	}
	
}
