package com.library.server.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

	private String name;

	private Short sex;

	private String phone;

	private String password;

	private Short isAdmin;

	public User() {
	}

	public User(String name, Short sex, String phone, String password, Short isAdmin) {
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.password = password;
		this.isAdmin = isAdmin;
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

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Short getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Short isAdmin) {
		this.isAdmin = isAdmin;
	}
}
