package com.example.hogwarts.service;

import java.util.List;

public interface DefaultService<T> {

    T getByID(Integer id);

    List<T> getALl();

    void save(T dto);

    void update(T dto);

    void delete(Integer id);
}
