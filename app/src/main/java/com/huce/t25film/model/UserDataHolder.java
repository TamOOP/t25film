package com.huce.t25film.model;

public class UserDataHolder {
    private static UserDataHolder instance = null;

    private int id;
    private String email;
    private String name;
    private String phone;
    private String password;

    private UserDataHolder() {
    }

    // Phương thức để lấy đối tượng duy nhất của class
    public static synchronized UserDataHolder getInstance() {
        if (instance == null) {
            instance = new UserDataHolder();
        }
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
