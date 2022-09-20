package com.example.minitest_md4_1.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductForm {

    private int id;
    private List<String> name;

    private List<Long> cost;
    private List<MultipartFile> img;

    public ProductForm() {
    }

    public ProductForm(int id, List<String> name,List<Long> cost, List<MultipartFile> img) {
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

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<Long> getCost() {
        return cost;
    }

    public void setCost(List<Long> cost) {
        this.cost = cost;
    }

    public List<MultipartFile> getImg() {
        return img;
    }

    public void setImg(List<MultipartFile> img) {
        this.img = img;
    }
}
