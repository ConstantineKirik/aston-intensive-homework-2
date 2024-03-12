package com.example.hogwarts.model.repository;

import java.util.List;

public interface DefaultRepo<T> {

    T get(Integer id);

    List<T> getALl();

    void save(T entity);

    void update(T entity);

    void delete(Integer id);
}
