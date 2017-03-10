package com.artiwise.textwiseannotation.database.repository;

import com.artiwise.textwiseannotation.database.connection.Connection;
import com.artiwise.textwiseannotation.database.connection.MongoConnection;
import com.artiwise.textwiseannotation.database.entity.Collection;
import com.artiwise.textwiseannotation.database.entity.Entity;
import com.artiwise.textwiseannotation.database.mapper.Mapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by oktaysadoglu on 09/03/2017.
 */
public class MongoDbRepository implements Repository<Entity> {

    private Mapper<Document,Entity> mapper;

    private MongoClient mongoClient;

    private MongoDatabase mongoDatabase;

    private MongoCollection mongoCollection;

    public MongoDbRepository(Connection connection ,Mapper<Document, Entity> mapper) {
        this.mapper = mapper;

        MongoConnection mongoConnection = MongoConnection.class.cast(connection);

        this.mongoClient = mongoConnection.getClient();

        this.mongoDatabase = this.mongoClient.getDatabase(mongoConnection.getDatabaseName());

        this.mongoCollection = getCollection(mapper.getClassType());
    }

    @Override
    public void add(Entity entity) {

        Document document = mapper.mapToDocument(entity);

        mongoCollection.insertOne(document);

    }

    @Override
    public void update(Entity entity) {
        mongoCollection.updateOne(eq(Entity.Cols.ID,entity.getObjectId()),new Document("$set",mapper.mapToDocument(entity)));
    }

    @Override
    public Entity getDocument(String fieldName, Object value) {

        Document document = (Document) mongoCollection.find(eq(fieldName,value)).first();

        return mapper.mapToEntity(document);

    }

    @Override
    public void remove(Entity entity) {

        mongoCollection.deleteOne(eq(Entity.Cols.ID,entity.getObjectId()));

    }

    @Override
    public List<Entity> getAllDocuments() {
        FindIterable findIterable = mongoCollection.find();

        Iterator iterator = findIterable.iterator();

        List<Entity> entities = new ArrayList<>();

        while (iterator.hasNext()){

            Document document = (Document) iterator.next();

            entities.add(mapper.mapToEntity(document));

        }

        return entities;
    }

    private MongoCollection<Document> getCollection(Class<Entity> clazz){

        MongoCollection<Document> collection;

        Collection annotation = clazz.getAnnotation(Collection.class);

        String name = clazz.getSimpleName();

        if (annotation != null){

            name = annotation.name();

        }

        collection = this.mongoDatabase.getCollection(name);

        return collection;

    }
}
