package by.epam.newsmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TAG")
@NamedQueries({
		@NamedQuery(name = "deleteTag", query = "DELETE FROM tag where tag.tag = :tag"),
		@NamedQuery(name = "getAllTags", query = "SELECT tag FROM Tag tag")
})
public class Tag {
	
	@Id
	int id;
	
	@Column(name = "TAG")
	@ManyToMany(mappedBy = "tagList")
	String tag;
	
	public Tag(int id, String tag) {
		super();
		this.id = id;
		this.tag = tag;
	}
	
	public Tag(){
	}
	
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
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}
	
}
