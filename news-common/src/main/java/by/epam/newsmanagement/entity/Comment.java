package by.epam.newsmanagement.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import by.epam.newsmanagement.entity.author.Author;

@Entity
@Table(name = "COMMENT_ENTITY")
public class Comment implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "COMMENT_ID")
  int id;

  @Column(name = "PUBLICATION_DATE")
  LocalDate publicationDate;

  @ManyToOne
  @JoinColumn(name = "AUTHOR_ID")
  Author author;

  @Column(name = "TEXT")
  String text;

  public Comment() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(LocalDate publicationDate) {
    this.publicationDate = publicationDate;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + id;
    result = prime * result + ((publicationDate == null) ? 0 : publicationDate.hashCode());
    result = prime * result + ((text == null) ? 0 : text.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Comment other = (Comment) obj;
    if (author == null) {
      if (other.author != null)
        return false;
    } else if (!author.equals(other.author))
      return false;
    if (id != other.id)
      return false;
    if (publicationDate == null) {
      if (other.publicationDate != null)
        return false;
    } else if (!publicationDate.equals(other.publicationDate))
      return false;
    if (text == null) {
      if (other.text != null)
        return false;
    } else if (!text.equals(other.text))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Comment [id=" + id + ", publicationDate=" + publicationDate + ", author=" + author
        + ", text=" + text + "]";
  }



}
