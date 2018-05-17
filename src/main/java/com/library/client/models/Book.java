package com.library.client.models;

public class Book {

	private Long id;
	public final static String BookId = "id";

	private String isbn;
	public final static String BookIsbn = "isbn";

	private String mark;
	public final static String BookMark = "mark";

	private String name;
	public final static String BookName = "name";

	private String tag;
	public final static String BookTag = "tag";

	private String author;
	public final static String BookAuthor = "author";

	private String introduction;
	public final static String BookIntroduction = "introduction";

	private Integer amount;
	public final static String BookAmount = "amount";

	private Integer inventory;
	public final static String BookInventory = "inventory";
	
	public Book() {
	}

	public Book(Long id, String isbn, String mark, String name, String tag,  String author,  String introduction, Integer amount, Integer inventory) {
		this.id = id;
		this.isbn = isbn;
		this.mark = mark;
		this.name = name;
		this.tag = tag;
		this.author = author;
		this.introduction = introduction;
		this.amount = amount;
		this.inventory = inventory;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public Long getId() {
		return id;
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
