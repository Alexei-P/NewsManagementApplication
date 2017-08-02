package by.epam.newsmanagement.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.epam.newsmanagement.dao.impl.AuthorDao;
import by.epam.newsmanagement.entity.Comment;
import by.epam.newsmanagement.entity.News;
import by.epam.newsmanagement.entity.Tag;
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
  public ResponseEntity<News> getNewsById(@PathVariable("id") int newsId) {
    News news = null;
    try {
      news = newsService.getNews(newsId);
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (news == null) {
      return new ResponseEntity<News>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<News>(news, HttpStatus.OK);
  }
  
  @RequestMapping(value="/news/most_popular/{quantity}", method = RequestMethod.GET)
  public ResponseEntity<ArrayList<News>> getTheMostPopularNews(@PathVariable("quantity") int newsQuantity){
    ArrayList<News> theMostPopularNews = null;
    try {
      theMostPopularNews = newsService.getTheMostPopularNews(newsQuantity);
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (theMostPopularNews.isEmpty()) {
      return new ResponseEntity<ArrayList<News>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<ArrayList<News>>(theMostPopularNews, HttpStatus.OK);
  }
  
  @RequestMapping(value="/authorized/news/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteNewsById(@PathVariable int newsId){
    try {
      newsService.deleteNews(newsId);
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.valueOf(500)).body("Something went wrong");
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
  
  @RequestMapping(value = "/authorized/news.do")
  public ResponseEntity<String> addNews(@RequestBody News news){
    try {
      newsService.addNews(news);
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.valueOf(500)).body("Something went wrong");
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
  
  @RequestMapping (value = "/authorized/news/{id}", method = RequestMethod.PUT)
  public ResponseEntity<String> editNews(@RequestBody int newsId, @RequestBody News updatedNews){
    try {
      newsService.editNews(newsId, updatedNews);
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.valueOf(500)).body("Something went wrong");
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @RequestMapping(value = "/getAuthor/{id}", method = RequestMethod.GET)
  public ResponseEntity<Author> getAuthorById(@PathVariable("id") int authorId) {
    Author author = null;
    try {
      author = authorService.getAuthorById(authorId);
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (author == null)
      return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);

    return new ResponseEntity<Author>(author, HttpStatus.OK);
  }
  
  @RequestMapping(value = "/authorized/news/{id}/comment.do", method = RequestMethod.POST)
  public ResponseEntity<String> addCommentToNews(@RequestBody int newsId, @RequestBody Comment comment){
     try {
      newsService.addComment(newsId, comment);
    } catch (ServiceException e) {
   // TODO Auto-generated catch block
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.valueOf(500)).body("Something went wrong");
    }
     return ResponseEntity.status(HttpStatus.OK).body(null);
  }  
  
  @RequestMapping(value = "/authorized/news/{id}/comment/{comment_id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteCommentById(@PathVariable("comment_id") int commentId){
    try {
      newsService.deleteComment(commentId);
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.valueOf(500)).body("Something went wrong");
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
  
  
 
  @RequestMapping(value = "/authorized/news/{id}/tag.do", method = RequestMethod.POST)
  public ResponseEntity<String> addTagToNews(@RequestBody int newsId, @RequestBody String tag){
    try{
    newsService.addTagToNews(newsId, tag);
    }catch(ServiceException e){
   // TODO Auto-generated catch block
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.valueOf(500)).body("Something went wrong");
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
  

  @RequestMapping(value = "/authorized/news/{id}/tag/{tag_id}", method = RequestMethod.POST)
  public ResponseEntity<String> deleteTagFromNews(@RequestBody int newsId, @RequestBody String tag){
    try{
    newsService.deleteTagFromNews(newsId, tag);
    }catch(ServiceException e){
   // TODO Auto-generated catch block
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.valueOf(500)).body("Something went wrong");
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
