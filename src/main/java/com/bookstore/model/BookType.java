package com.bookstore.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "bookType")
public class BookType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_type_id")
	private Long bookTypeId;
	
	@Column(name = "type_code")
	private String typeCode;
	
	@Column(name = "display_name")
	private String displayName;
	 
	@ManyToMany(cascade = {
	        CascadeType.ALL
	    })
	    @JoinTable(
	        name = "book_type_promotions",
	        joinColumns = {
	            @JoinColumn(name = "book_type_id")
	        },
	        inverseJoinColumns = {
	            @JoinColumn(name = "promotion_id")
	        }
	    )
	private List<Promotion> promotions;

	public Long getBookTypeId() {
		return bookTypeId;
	}

	public void setBookTypeId(Long bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
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
		BookType other = (BookType) obj;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookType [bookTypeId=" + bookTypeId + ", typeCode=" + typeCode + ", displayName=" + displayName + "]";
	}
	
	
}
