package com.example.hogwarts.model.repository;

import java.util.List;

public interface DefaultRepo<T> {

    T findByID(Integer id);

    List<T> findAll();

    boolean save(T entity);

    boolean update(T entity);

    boolean delete(Integer id);
}
