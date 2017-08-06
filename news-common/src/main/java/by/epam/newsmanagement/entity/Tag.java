package by.epam.newsmanagement.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TAG")
@NamedQueries({@NamedQuery(name = "deleteTag", query = "DELETE FROM Tag tag where tag.tag = :tag"),
    @NamedQuery(name = "getAllTags", query = "SELECT tag FROM Tag tag"),
    @NamedQuery(name = "updateTag", query = "UPDATE Tag tag SET tag.tag = :newTag WHERE tag = :oldTag")})
public class Tag implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "TAG_ID")
  private int id;

  @Column(name = "TAG")
  private String tag;

  @ManyToMany(mappedBy = "tagList")
  @JsonIgnore
  private List<News> newsList;

  public List<News> getNewsList() {
    return newsList;
  }

  public void setNewsList(List<News> newsList) {
    this.newsList = newsList;
  }

  public Tag(int id, String tag) {
    super();
    this.id = id;
    this.tag = tag;
  }

  public Tag() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((newsList == null) ? 0 : newsList.hashCode());
    result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
    Tag other = (Tag) obj;
    if (id != other.id)
      return false;
    if (newsList == null) {
      if (other.newsList != null)
        return false;
    } else if (!newsList.equals(other.newsList))
      return false;
    if (tag == null) {
      if (other.tag != null)
        return false;
    } else if (!tag.equals(other.tag))
      return false;
    return true;
  }

}
