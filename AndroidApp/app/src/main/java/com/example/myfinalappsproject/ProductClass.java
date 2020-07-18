package com.example.myfinalappsproject;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

public class ProductClass {
    private int ProductID;
    private String ProductName;
    private LocalDateTime ProductStartTime;
    private int ProductAmmount;

    public ProductClass() {
    }

    public int getProductID() {
        return ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public LocalDateTime getProductStartTime() {
        return ProductStartTime;
    }

    public int getProductAmmount() {
        return ProductAmmount;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public void setProductStartTime(LocalDateTime productStartTime) {
        ProductStartTime = productStartTime;
    }

    public void setProductAmmount(int productAmmount) {
        ProductAmmount = productAmmount;
    }
}
