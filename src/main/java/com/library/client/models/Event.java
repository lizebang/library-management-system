package com.library.client.models;

public class Event {

	private Long id;
	public final static String EventId = "id";

	private String phone;
	public final static String EventPhone = "phone";

	private String isbn;
	public final static String EventIsbn = "isbn";

	public Event() {
	}

	public Event(Long id, String phone, String isbn) {
		this.id = id;
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
