package com.library.server.models;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public final static String UserId = "id";

    private String name;
    public final static String UserName = "name";

    private Integer sex;
    public final static String UserSex = "sex";

    private String phone;
    public final static String UserPhone = "phone";

    private String password;
    public final static String UserPassword = "password";

    private Integer isAdmin;
    public final static String UserIsAdmin = "isAdmin";

    private Integer amount;
    public final static String UserAmount = "amount";

    public User() {}

    public User(String name, Integer sex, String phone, String password) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(UserId, id);
        map.put(UserName, name);
        map.put(UserSex, sex);
        map.put(UserPhone, phone);
        map.put(UserIsAdmin, isAdmin);
        map.put(UserAmount, amount);
        return map;
    }
}
