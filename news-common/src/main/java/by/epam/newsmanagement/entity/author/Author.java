package by.epam.newsmanagement.entity.author;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Author")
@NamedQueries({
	@NamedQuery(name = "getAuthorIdByName", query = "SELECT author FROM Author author WHERE author.id = :id"),
})
public class Author implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "A_ID")
	int id;
	
	@Column(name = "AUTHOR")
	String name;
	
	@Column(name = "STATE")
	@Enumerated(EnumType.STRING)
	AuthorState authorState;
	
	public Author(String name, AuthorState authorState) {
		this.name = name;
		this.authorState = authorState;
	}
	
	public Author(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AuthorState getAuthorState() {
		return authorState;
	}
	public void setAuthorState(AuthorState authorState) {
		this.authorState = authorState;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorState == null) ? 0 : authorState.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Author other = (Author) obj;
		if (authorState != other.authorState)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
