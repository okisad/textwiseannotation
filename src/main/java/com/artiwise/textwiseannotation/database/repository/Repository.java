package com.artiwise.textwiseannotation.database.repository;

import java.util.List;

/**
 * Created by oktaysadoglu on 09/03/2017.
 */
public interface Repository<T> {

    void add(T entity);

    void update(T entity);

    T getDocument(String fieldName,Object value);

    void remove(T entity);

    List<T> getAllDocuments();
}
