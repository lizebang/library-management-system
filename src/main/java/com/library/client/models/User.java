package com.library.client.models;

public class User {

	private Long id;
	public final static String UserId = "id";

	private String name;
	public final static String UserName = "name";

	private Integer sex;
	public final static String UserSex = "sex";

	private String phone;
	public final static String UserPhone = "phone";

	public final static String UserPassword = "password";

	private Integer isAdmin;
	public final static String UserIsAdmin = "isAdmin";

	private Integer amount;
	public final static String UserAmount = "amount";

	public User() {
	}

	public User(Long id, String name, Integer sex, String phone, Integer isAdmin, Integer amount) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.isAdmin = isAdmin;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
