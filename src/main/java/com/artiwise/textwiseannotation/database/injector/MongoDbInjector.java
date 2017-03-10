package com.artiwise.textwiseannotation.database.injector;

import com.artiwise.textwiseannotation.database.connection.MongoConnection;
import com.artiwise.textwiseannotation.database.mapper.Mapper;
import com.artiwise.textwiseannotation.database.repository.MongoDbRepository;
import com.artiwise.textwiseannotation.database.repository.Repository;

/**
 * Created by oktaysadoglu on 09/03/2017.
 */
public class MongoDbInjector implements DatabaseInjector{

    private MongoConnection connection = new MongoConnection("localhost",27017,"textwiseannotation");

    @Override
    public Repository getRepository(Mapper mapper) {
        return new MongoDbRepository(connection,mapper);
    }
}
