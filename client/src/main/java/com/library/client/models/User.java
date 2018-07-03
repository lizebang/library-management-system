package com.library.client.models;

import java.util.HashMap;
import java.util.Map;
import com.library.client.Constant;

public class User {

    private Integer totalPages;

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

    public User() {}

    public User(Long id, String name, Integer sex, String phone, Integer isAdmin, Integer amount) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.isAdmin = isAdmin;
        this.amount = amount;
    }

    public Object[] toObject() {
        return new Object[] {this.id, this.name, getSex(this.sex), this.phone,
                getIsAdmin(this.isAdmin), this.amount, this.phone};
    }

    public final static String getSex(Integer sex) {
        if (sex.equals(Constant.SexMale)) {
            return "男";
        }
        if (sex.equals(Constant.SexFemale)) {
            return "女";
        }
        return "保密";
    }

    public final static String getIsAdmin(Integer isAdmin) {
        if (isAdmin.equals(Constant.AdminUser)) {
            return "管理员";
        }
        return "普通用户";
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
