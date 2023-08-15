package com.example.duan1_customer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable {
    @SerializedName("phoneNumberCustomer")
    private String phoneNumber;
    private String passWord;
    private String name;
    private int age;
    private String gender;
    private int totalSpend;
    private String address;
    private String email;
    private String job;
    private String date;
    private String status;


    /*
    {
  "phoneNumberCustomer": "0776616818",
  "name": "Le Van Hoang",
  "age": 23,
  "gender": "Nam",
  "totalSpend": 0,
  "address": "Xuan Son",
  "email": "hoanglv@fpt.edu.vn",
  "job": "Hoc sinh",
  "date": "06/10/2001"
}
     */

    public Customer(String phoneNumber, String passWord, String name, int age, String gender, int totalSpend, String address) {
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.totalSpend = totalSpend;
        this.address = address;
    }

    public Customer(String phoneNumber, String name, int age, String gender, int totalSpend, String address, String email, String job, String date) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.totalSpend = totalSpend;
        this.address = address;
        this.email = email;
        this.job = job;
        this.date = date;
    }

    public Customer(String phoneNumber, String passWord, String name, int age, String gender, int totalSpend, String address, String email, String job, String date) {
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.totalSpend = totalSpend;
        this.address = address;
        this.email = email;
        this.job = job;
        this.date = date;
    }

    public Customer(String phoneNumber, String passWord) {
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
    }

    public Customer(String phoneNumber, String name, String gender, String address, String email, String job, String date) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.job = job;
        this.date = date;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
