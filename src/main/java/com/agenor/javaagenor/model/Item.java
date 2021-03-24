package com.agenor.javaagenor.model;

import com.agenor.javaagenor.repository.ImageRepository;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    @Column(name = "inStock")
    private boolean inStock;

    @Column(name = "mainImage")
    private String mainImage;

    @Column(name = "smallImage1")
    private String smallImage1;

    @Column(name = "smallImage2")
    private String smallImage2;

    @Column(name = "smallImage3")
    private String smallImage3;

    @Column(name = "type")
    private String type;

    public Item() {
    }

    public Item(String title, float price, String description, boolean published, boolean inStock, String mainImage, String smallImage1, String smallImage2, String smallImage3, String type) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.published = published;
        this.inStock = inStock;
        this.mainImage = mainImage;
        this.smallImage1 = smallImage1;
        this.smallImage2 = smallImage2;
        this.smallImage3 = smallImage3;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isInStock() { return inStock; }

    public void setInStock(boolean inStock) { this.inStock = inStock; }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getSmallImage1() {
        return smallImage1;
    }

    public void setSmallImage1(String smallImage1) {
        this.smallImage1 = smallImage1;
    }

    public String getSmallImage2() {
        return smallImage2;
    }

    public void setSmallImage2(String smallImage2) {
        this.smallImage2 = smallImage2;
    }

    public String getSmallImage3() {
        return smallImage3;
    }

    public void setSmallImage3(String smallImage3) {
        this.smallImage3 = smallImage3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item[" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", published=" + published +
                ", inStock=" + inStock +
                ", mainImage='" + mainImage + '\'' +
                ", smallImage1='" + smallImage1 + '\'' +
                ", smallImage2='" + smallImage2 + '\'' +
                ", smallImage3='" + smallImage3 + '\'' +
                ", type='" + type + '\'' +
                ']';
    }
}
