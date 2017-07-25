package by.epam.newsmanagement.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.epam.newsmanagement.dao.impl.AuthorDao;
import by.epam.newsmanagement.entity.News;
import by.epam.newsmanagement.entity.author.Author;
import by.epam.newsmanagement.exception.service.ServiceException;
import by.epam.newsmanagement.service.impl.AuthorService;
import by.epam.newsmanagement.service.impl.NewsService;


@RestController
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	NewsService newsService;
	
	@Autowired
	AuthorService authorService;

	public HomeController() {
		System.out.println("HOME CONTROLLER");
	}

	@RequestMapping(value = "/getNews", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<News>> getAllNews() {
		ArrayList<News> newsList = null;
		try {
			newsList = newsService.getAllNews();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (newsList.isEmpty()) {
			return new ResponseEntity<ArrayList<News>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ArrayList<News>>(newsList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getNews/{id}", method = RequestMethod.GET)
	public ResponseEntity<News> getNewsById(@PathVariable("id") int newsId){
		News news = null;
		try {
			news = newsService.getNews(newsId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (news == null){
			return new ResponseEntity<News>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<News>(news, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAuthor/{id}", method = RequestMethod.GET)
	public ResponseEntity<Author> getAuthorById(@PathVariable("id") int authorId){
		Author author = null;
			try {
				author = authorService.getAuthorById(authorId);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (author == null) return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<Author>(author, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	public ResponseEntity<String> getTag() {
		return new ResponseEntity<String>("TEST", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/redir", method = RequestMethod.GET)
	public String redirectExample(){
		return "index_2";
	}
}
