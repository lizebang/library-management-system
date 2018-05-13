package com.library.server.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String isbn;

    private String mark;

    private String name;

    private String tag;

    private String author;

    private String introduction;

    private Integer amount;

    private Integer inventory;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

    public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
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

    public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
    }

    public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
    }

    public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
    }
}
