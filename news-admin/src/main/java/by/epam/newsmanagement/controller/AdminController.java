package by.epam.newsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.epam.newsmanagement.entity.Tag;
import by.epam.newsmanagement.entity.author.Author;
import by.epam.newsmanagement.entity.author.AuthorState;
import by.epam.newsmanagement.exception.service.ServiceException;
import by.epam.newsmanagement.service.impl.AuthorService;
import by.epam.newsmanagement.service.impl.NewsService;

@RestController
@RequestMapping("/admin")
public class AdminController {
  
  @Autowired
  NewsService newsService;

  @Autowired
  AuthorService authorService;
  
  @RequestMapping(value="/news/{id}", method = RequestMethod.DELETE)
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
  
  @RequestMapping(value="/author/{id}", method = RequestMethod.POST)
  public ResponseEntity<String> addAuthor(@RequestBody String authorName, AuthorState authorState){
    try {
      authorService.addAuthor(authorName, authorState);
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.valueOf(500)).body("Something went wrong");
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
  
  @RequestMapping(value="/author/{id}", method = RequestMethod.GET)
  public ResponseEntity<String> addAuthor(@PathVariable int authorId){
    try {
      authorService.getAuthorById(authorId);
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.valueOf(500)).body("Something went wrong");
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
  
  //TODO author update
  
  @RequestMapping(value="/author/{id}/logically.do", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAuthorLogicallyById(@PathVariable ("id") int authorId){
    try {
      authorService.deleteAuthorLogically(authorId);
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.valueOf(500)).body("Something went wrong");
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
  
  @RequestMapping(value="/author/{id}/phisically.do", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAuthorPhisicallyById(@PathVariable ("id") int authorId){
    try {
      authorService.deleteAuthorPhisically(authorId);
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.valueOf(500)).body("Something went wrong");
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
  
  @RequestMapping(value="/tag.do", method = RequestMethod.POST)
  public ResponseEntity<String> addTag(Tag tag){
    
  }
  
}
