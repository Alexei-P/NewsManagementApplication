package by.epam.newsmanagement.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import by.epam.newsmanagement.entity.author.Author;

public class News implements Serializable{
	
	private static final long serialVersionUID = 1L;
	int id;
	String mainTitle;
	String shortTitle;
	String content;
	Author author;
	LocalDate publicationDate;
	LocalDate modificationDate;
	String pathToPhoto;
	int views;
	ArrayList<String> tagList;
	ArrayList<String> commentsList;

	public News() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public LocalDate getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDate modificationDate) {
		this.modificationDate = modificationDate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getPathToPhoto() {
		return pathToPhoto;
	}

	public void setPathToPhoto(String pathToPhoto) {
		this.pathToPhoto = pathToPhoto;
	}

	public ArrayList<String> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<String> tagList) {
		this.tagList = tagList;
	}

	public ArrayList<String> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(ArrayList<String> commentsList) {
		this.commentsList = commentsList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((commentsList == null) ? 0 : commentsList.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result + ((mainTitle == null) ? 0 : mainTitle.hashCode());
		result = prime * result + ((modificationDate == null) ? 0 : modificationDate.hashCode());
		result = prime * result + ((pathToPhoto == null) ? 0 : pathToPhoto.hashCode());
		result = prime * result + ((publicationDate == null) ? 0 : publicationDate.hashCode());
		result = prime * result + ((shortTitle == null) ? 0 : shortTitle.hashCode());
		result = prime * result + ((tagList == null) ? 0 : tagList.hashCode());
		result = prime * result + views;
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
		News other = (News) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (commentsList == null) {
			if (other.commentsList != null)
				return false;
		} else if (!commentsList.equals(other.commentsList))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (mainTitle == null) {
			if (other.mainTitle != null)
				return false;
		} else if (!mainTitle.equals(other.mainTitle))
			return false;
		if (modificationDate == null) {
			if (other.modificationDate != null)
				return false;
		} else if (!modificationDate.equals(other.modificationDate))
			return false;
		if (pathToPhoto == null) {
			if (other.pathToPhoto != null)
				return false;
		} else if (!pathToPhoto.equals(other.pathToPhoto))
			return false;
		if (publicationDate == null) {
			if (other.publicationDate != null)
				return false;
		} else if (!publicationDate.equals(other.publicationDate))
			return false;
		if (shortTitle == null) {
			if (other.shortTitle != null)
				return false;
		} else if (!shortTitle.equals(other.shortTitle))
			return false;
		if (tagList == null) {
			if (other.tagList != null)
				return false;
		} else if (!tagList.equals(other.tagList))
			return false;
		if (views != other.views)
			return false;
		return true;
	}	
	
	@Override
	public String toString() {
		return "News [id=" + id + ", mainTitle=" + mainTitle + ", shortTitle=" + shortTitle + ", content=" + content
				+ ", author=" + author + ", publicationDate=" + publicationDate + ", modificationDate="
				+ modificationDate + ", pathToPhoto=" + pathToPhoto + ", views=" + views + ", tagList=" + tagList
				+ ", commentsList=" + commentsList + "]";
	}
}
