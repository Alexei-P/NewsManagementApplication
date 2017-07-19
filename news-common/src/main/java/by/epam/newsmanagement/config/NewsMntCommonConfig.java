package by.epam.newsmanagement.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import by.epam.newsmanagement.dao.impl.*;
import by.epam.newsmanagement.service.impl.NewsService;

import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan
public class NewsMntCommonConfig {
	
	@Bean
	public AdminDao adminDao(){
		return new AdminDao();
	}
	
	public AuthorDao authorDao(){
		return new AuthorDao();
	}
	
	public CommentDao commentDao(){
		return new CommentDao();
	}
	
	public NewsDao newsDao(){
		return new NewsDao();
	}
	
	public TagDao tagDao(){
		return new TagDao();
	}
	
	public UserDao userDao(){
		return new UserDao();
	}
	
	public NewsService newsService(){
		return new NewsService();
	}
	
	// Add data source
}
