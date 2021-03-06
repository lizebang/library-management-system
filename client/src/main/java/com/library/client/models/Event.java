package com.library.client.models;

import java.util.HashMap;
import java.util.Map;

public class Event {

    private Integer totalPages;

    private Long id;
    public final static String EventId = "id";

    private String phone;
    public final static String EventPhone = "phone";

    private String isbn;
    public final static String EventIsbn = "isbn";

    private String name;
    public final static String EventName = "name";

    private String tag;
    public final static String EventTag = "tag";

    private String author;
    public final static String EventAuthor = "author";

    public Event() {}

    public Event(Long id, String phone, String isbn, String name, String tag, String author) {
        this.id = id;
        this.phone = phone;
        this.isbn = isbn;
        this.name = name;
        this.tag = tag;
        this.author = author;
    }

    public Object[] toObject() {
        return new Object[] {this.id, this.isbn, this.name, this.tag, this.author, this.isbn};
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(EventId, id);
        map.put(EventPhone, phone);
        map.put(EventIsbn, isbn);
        map.put(EventName, name);
        map.put(EventTag, tag);
        map.put(EventAuthor, author);
        return map;
    }
}
