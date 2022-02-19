package com.example.listview;

public class User {
    private String name;
    private String address;
    private String phone;
    private int img;


    public User(String name, String address, String phone, int img) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.img = img;
    }

    public User(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
