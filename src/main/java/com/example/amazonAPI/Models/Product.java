package com.example.amazonAPI.Models;

import org.springframework.data.annotation.Id;

public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private String price;
    private String poster;
    private String category;
    private String bestSeller;

    public Product() {
    }

    public Product(String id, String title, String description, String price,String poster,String category, String bestSeller) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.poster=poster;
        this.category = category;
        this.bestSeller = bestSeller;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(String bestSeller) {
        this.bestSeller = bestSeller;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", poster='" + poster + '\'' +
                ", category='" + category + '\'' +
                ", bestSeller='" + bestSeller + '\'' +
                '}';
    }
}
