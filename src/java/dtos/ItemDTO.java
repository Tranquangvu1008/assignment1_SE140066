/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Date;

/**
 *
 * @author SE140066
 */
public class ItemDTO {
    private String userID;
    private String itemID;
    private String itemName;
    private boolean status;
    private String description;
    private String picture;
    private float price;
    private Date createDate;
    private String category;
    private int quantity;

    public ItemDTO() {
    }

    public ItemDTO(String userID, String itemID, String itemName, boolean status, String description, String picture, float price, Date createDate, String category, int quantity) {
        this.userID = userID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.status = status;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.createDate = createDate;
        this.category = category;
        this.quantity = quantity;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
}
