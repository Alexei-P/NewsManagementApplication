package by.epam.newsmanagement.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import by.epam.newsmanagement.dao.impl.*;
import by.epam.newsmanagement.service.impl.NewsService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan
public class NewsMntCommonConfig {
	
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
		return new NewsService();
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(){
		return Persistence.createEntityManagerFactory( "FirstJPAPU" );
	}
	// Add data source
}
