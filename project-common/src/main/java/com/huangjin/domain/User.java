package com.huangjin.domain;

import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private List<String> phoneNum;
    private long money;
    private Date date;
    private Integer num;
    private boolean man;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username == null?null:username.trim();
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password == null?null:password.trim();
    }

    public List<String> getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(List<String> phoneNum) {
        this.phoneNum = phoneNum;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);

    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum=" + phoneNum +
                ", money=" + money +
                '}';
    }
}