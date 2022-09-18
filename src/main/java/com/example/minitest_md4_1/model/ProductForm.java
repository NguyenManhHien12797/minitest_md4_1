package com.example.minitest_md4_1.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {

    private int id;
    private String name;

    private Long cost;
    private MultipartFile img;

    public ProductForm() {
    }

    public ProductForm(int id, String name,Long cost, MultipartFile img) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
