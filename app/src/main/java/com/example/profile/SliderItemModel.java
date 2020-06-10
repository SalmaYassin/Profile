package com.example.profile;

public  class SliderItemModel {
    private int numOfShops;
    private int numOfReviews;
    private int numOfProducts;
    private String image;
    private String categoryname;

    public SliderItemModel() {
    }

    public SliderItemModel(int numOfShops, int numOfReviews, int numOfProducts, String image, String categoryname) {
        this.numOfShops = numOfShops;
        this.numOfReviews = numOfReviews;
        this.numOfProducts = numOfProducts;
        this.image = image;
        this.categoryname = categoryname;
    }

    public int getNumOfShops() {
        return numOfShops;
    }

    public void setNumOfShops(int numOfShops) {
        this.numOfShops = numOfShops;
    }

    public int getNumOfReviews() {
        return numOfReviews;
    }

    public void setNumOfReviews(int numOfReviews) {
        this.numOfReviews = numOfReviews;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public void setNumOfProducts(int numOfProducts) {
        this.numOfProducts = numOfProducts;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
