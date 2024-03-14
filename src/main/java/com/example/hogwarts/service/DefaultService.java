package com.example.hogwarts.service;

import java.util.List;

public interface DefaultService<T> {

    T getByID(Integer id);

    List<T> getALl();

    boolean create(T dto);

    boolean update(T dto);

    boolean remove(Integer id);
}
