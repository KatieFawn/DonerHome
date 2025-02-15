package com.jiromo5.donerhome.data.state;

import java.util.ArrayList;
import java.util.List;

public class ProductsData {

    public static List<Integer> id = new ArrayList<>();
    public static List<String> productName = new ArrayList<>();
    public static List<Integer> price = new ArrayList<>();
    public static List<String> category = new ArrayList<>();
    public static List<String> subcategory = new ArrayList<>();
    public static List<String> imageURL = new ArrayList<>();


    public static long getId(String productName){
        for (int i = 0; i < id.size(); i ++){
            if (ProductsData.productName.get(i).equals(productName)){
                return ProductsData.id.get(i);
            }
        }
        return -1;
    }
}
