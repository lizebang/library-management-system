package com.library.server.models;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	public final static String EventId = "id";

	private String phone;
	public final static String EventPhone = "phone";

	private String isbn;
	public final static String EventIsbn = "isbn";

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

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<>();
		map.put(EventId, id);
		map.put(EventPhone, phone);
		map.put(EventIsbn, isbn);
		return map;
	}
}
