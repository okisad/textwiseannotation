package com.artiwise.textwiseannotation.database.mapper;

/**
 * Created by oktaysadoglu on 09/03/2017.
 */
public interface Mapper <T,V>{

    T mapToDocument(V source);

    V mapToEntity(T source);

    Class<V> getClassType();

}
