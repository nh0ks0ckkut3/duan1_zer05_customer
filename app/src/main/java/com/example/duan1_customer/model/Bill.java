package com.example.duan1_customer.model;

import java.io.Serializable;

public class Bill implements Serializable {
    private int id;
    private String phoneNumberCustomer;
    private String userNameEmployee;
    private int idService;
    private int idProduct;
    private String time;
    private String status;
    private int totalPrice;
    private String nameEmployee;
    private String nameService;
    private String nameProduct;

    public Bill(int id, String phoneNumberCustomer, String nameEmployee, String nameService, String nameProduct, String time, int totalPrice) {
        this.id = id;
        this.phoneNumberCustomer = phoneNumberCustomer;
        this.time = time;
        this.totalPrice = totalPrice;
        this.nameEmployee = nameEmployee;
        this.nameProduct = nameProduct;
        this.nameService = nameService;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumberCustomer() {
        return phoneNumberCustomer;
    }

    public void setPhoneNumberCustomer(String phoneNumberCustomer) {
        this.phoneNumberCustomer = phoneNumberCustomer;
    }

    public String getUserNameEmployee() {
        return userNameEmployee;
    }

    public void setUserNameEmployee(String userNameEmployee) {
        this.userNameEmployee = userNameEmployee;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
