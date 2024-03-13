package com.example.hogwarts.model.repository;

import java.util.List;

public interface DefaultRepo<T> {

    T findByID(Integer id);

    List<T> findAll();

    void save(T entity);

    void update(T entity);

    void delete(Integer id);
}
