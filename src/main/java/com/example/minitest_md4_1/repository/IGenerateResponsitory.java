package com.example.minitest_md4_1.repository;

import com.example.minitest_md4_1.model.Product;

import java.util.List;

public interface IGenerateResponsitory<T> {

    List<T> findAll();
    T findById(int id);
    boolean insert(T t);
    void remove(int id);

    boolean update(T t);

}
