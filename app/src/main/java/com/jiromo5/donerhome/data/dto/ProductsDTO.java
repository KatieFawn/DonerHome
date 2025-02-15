package com.jiromo5.donerhome.data.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductsDTO {

    private List<Integer> id = new ArrayList<>();
    private List<String> productName = new ArrayList<>();
    private List<Integer> price = new ArrayList<>();
    private List<String> category = new ArrayList<>();
    private List<String> subcategory = new ArrayList<>();
    private List<String> imageURL = new ArrayList<>();

    public ProductsDTO(List<Integer> id, List<String> productName, List<Integer> price, List<String> category, List<String> subcategory, List<String> imageURL) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.subcategory = subcategory;
        this.imageURL = imageURL;
    }

    public ProductsDTO(){

    }

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public List<String> getProductName() {
        return productName;
    }

    public void setProductName(List<String> productName) {
        this.productName = productName;
    }

    public List<Integer> getPrice() {
        return price;
    }

    public void setPrice(List<Integer> price) {
        this.price = price;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<String> subcategory) {
        this.subcategory = subcategory;
    }

    public List<String> getImageURL() {
        return imageURL;
    }

    public void setImageURL(List<String> imageURL) {
        this.imageURL = imageURL;
    }
}
