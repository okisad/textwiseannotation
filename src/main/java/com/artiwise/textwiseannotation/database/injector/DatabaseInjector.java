package com.artiwise.textwiseannotation.database.injector;


import com.artiwise.textwiseannotation.database.mapper.Mapper;
import com.artiwise.textwiseannotation.database.repository.Repository;

/**
 * Created by oktaysadoglu on 09/01/2017.
 */
public interface DatabaseInjector {

    Repository getRepository(Mapper mapper);

}
