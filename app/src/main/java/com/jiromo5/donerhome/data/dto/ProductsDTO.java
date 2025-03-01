package com.jiromo5.donerhome.data.dto;

import java.util.*;

/**
 * Data Transfer Object (DTO) representing product-related data.
 * This class holds information about products, including their ID, name, price, category, subcategory, and image URLs.
 */
public class ProductsDTO {

    private List<Integer> id = new ArrayList<>();  // List of product IDs
    private List<String> productName = new ArrayList<>();  // List of product names
    private List<Integer> price = new ArrayList<>();  // List of product prices
    private List<String> category = new ArrayList<>();  // List of product categories
    private List<String> subcategory = new ArrayList<>();  // List of product subcategories
    private List<String> imageURL = new ArrayList<>();  // List of product image URLs

    /**
     * Constructs a ProductsDTO object with the specified lists of product data.
     *
     * @param id List of product IDs
     * @param productName List of product names
     * @param price List of product prices
     * @param category List of product categories
     * @param subcategory List of product subcategories
     * @param imageURL List of product image URLs
     */
    public ProductsDTO(List<Integer> id, List<String> productName, List<Integer> price, List<String> category, List<String> subcategory, List<String> imageURL) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.subcategory = subcategory;
        this.imageURL = imageURL;
    }

    /**
     * Default constructor for ProductsDTO.
     */
    public ProductsDTO(){
    }

    /**
     * Gets the list of product IDs.
     *
     * @return List of product IDs
     */
    public List<Integer> getId() {
        return id;
    }

    /**
     * Sets the list of product IDs.
     *
     * @param id List of product IDs to set
     */
    public void setId(List<Integer> id) {
        this.id = id;
    }

    /**
     * Gets the list of product names.
     *
     * @return List of product names
     */
    public List<String> getProductName() {
        return productName;
    }

    /**
     * Sets the list of product names.
     *
     * @param productName List of product names to set
     */
    public void setProductName(List<String> productName) {
        this.productName = productName;
    }

    /**
     * Gets the list of product prices.
     *
     * @return List of product prices
     */
    public List<Integer> getPrice() {
        return price;
    }

    /**
     * Sets the list of product prices.
     *
     * @param price List of product prices to set
     */
    public void setPrice(List<Integer> price) {
        this.price = price;
    }

    /**
     * Gets the list of product categories.
     *
     * @return List of product categories
     */
    public List<String> getCategory() {
        return category;
    }

    /**
     * Sets the list of product categories.
     *
     * @param category List of product categories to set
     */
    public void setCategory(List<String> category) {
        this.category = category;
    }

    /**
     * Gets the list of product subcategories.
     *
     * @return List of product subcategories
     */
    public List<String> getSubcategory() {
        return subcategory;
    }

    /**
     * Sets the list of product subcategories.
     *
     * @param subcategory List of product subcategories to set
     */
    public void setSubcategory(List<String> subcategory) {
        this.subcategory = subcategory;
    }

    /**
     * Gets the list of product image URLs.
     *
     * @return List of product image URLs
     */
    public List<String> getImageURL() {
        return imageURL;
    }

    /**
     * Sets the list of product image URLs.
     *
     * @param imageURL List of product image URLs to set
     */
    public void setImageURL(List<String> imageURL) {
        this.imageURL = imageURL;
    }
}

