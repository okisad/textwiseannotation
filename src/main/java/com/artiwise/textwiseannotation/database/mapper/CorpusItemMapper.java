package com.artiwise.textwiseannotation.database.mapper;

import com.artiwise.textwiseannotation.database.entity.CorpusItem;
import com.artiwise.textwiseannotation.database.entity.CorpusItemAnnotation;
import com.artiwise.textwiseannotation.database.entity.CorpusOverview;
import com.artiwise.textwiseannotation.database.entity.Entity;
import com.artiwise.textwiseannotation.database.injector.DatabaseInjector;
import com.artiwise.textwiseannotation.database.injector.MongoDbInjector;
import com.artiwise.textwiseannotation.database.repository.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by oktaysadoglu on 09/03/2017.
 */
public class CorpusItemMapper implements Mapper<Document,Entity>  {

    @Override
    public Document mapToDocument(Entity source){

        ObjectMapper objectMapper = new ObjectMapper();

        Document document = new Document();

        CorpusItem corpusItem = CorpusItem.class.cast(source);

        document.put(CorpusItem.Cols.TEXT,corpusItem.getText());
        try {
            document.put(CorpusItem.Cols.ANNOTATIONS,objectMapper.writeValueAsString(corpusItem.getAnnotations()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        document.put(CorpusItem.Cols.LAST_STATUS_UPDATE,corpusItem.getLastStatusUpdate());
        document.put(CorpusItem.Cols.STATUS,corpusItem.getStatus());
        document.put(CorpusItem.Cols.CORPUS,corpusItem.getCorpus().getObjectId());

        return document;
    }

    @Override
    public Entity mapToEntity(Document source) {

        CorpusItem corpusItem = new CorpusItem();

        ObjectMapper objectMapper = new ObjectMapper();

        corpusItem.setObjectId(source.getObjectId(Entity.Cols.ID));
        corpusItem.setText(source.getString(CorpusItem.Cols.TEXT));
        corpusItem.setStatus(source.getInteger(CorpusItem.Cols.STATUS,-1));

        DatabaseInjector injector = new MongoDbInjector();
        Repository<Entity> repository = injector.getRepository(new CorpusOverviewMapper());

        corpusItem.setCorpus(CorpusOverview.class.cast(repository.getDocument(Entity.Cols.ID,source.getObjectId(CorpusItem.Cols.CORPUS))));
        corpusItem.setLastStatusUpdate(source.getDate(CorpusItem.Cols.LAST_STATUS_UPDATE));

        try {
            corpusItem.setAnnotations(objectMapper.readValue(source.getString(CorpusItem.Cols.ANNOTATIONS), objectMapper.getTypeFactory().constructCollectionType(List.class,CorpusItemAnnotation.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return corpusItem;
    }

    @Override
    public Class<Entity> getClassType() {

        Entity entity = new CorpusItem();

        Class<Entity> clazz = (Class<Entity>) entity.getClass();

        return clazz;
    }
}
