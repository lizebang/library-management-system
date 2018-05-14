package com.library.server.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String phone;

	private String isbn;

	public Event() {
	}

	public Event(String phone, String isbn) {
		this.phone = phone;
		this.isbn = isbn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
    }
    
    public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
