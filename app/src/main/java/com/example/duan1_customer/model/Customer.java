package com.example.duan1_customer.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private String phoneNumber;
    private String passWord;
    private String name;
    private int age;
    private String gender;
    private int totalSpend;
    private String address;

    public Customer(String phoneNumber, String passWord, String name, int age, String gender, int totalSpend, String address) {
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.totalSpend = totalSpend;
        this.address = address;
    }

    public Customer(String phoneNumber, String passWord, String name) {
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(int totalSpend) {
        this.totalSpend = totalSpend;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
