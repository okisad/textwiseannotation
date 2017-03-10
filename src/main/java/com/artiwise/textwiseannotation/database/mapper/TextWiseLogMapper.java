package com.artiwise.textwiseannotation.database.mapper;

import com.artiwise.textwiseannotation.database.entity.Entity;
import com.artiwise.textwiseannotation.util.TextWiseLog;
import org.bson.Document;

/**
 * Created by oktaysadoglu on 10/03/2017.
 */
public class TextWiseLogMapper implements Mapper<Document,Entity> {
    @Override
    public Document mapToDocument(Entity source) {

        TextWiseLog textWiseLog = TextWiseLog.class.cast(source);

        Document document = new Document();

        document.put(TextWiseLog.Cols.LEVEL,textWiseLog.getLevel());
        document.put(TextWiseLog.Cols.DESCRIPTON,textWiseLog.getDescription());
        document.put(TextWiseLog.Cols.ERROR_STACK,textWiseLog.getErrorStack());
        document.put(TextWiseLog.Cols.DATE,textWiseLog.getDate());

        return document;
    }

    @Override
    public Entity mapToEntity(Document source) {

        TextWiseLog textWiseLog = new TextWiseLog();

        textWiseLog.setObjectId(source.getObjectId(Entity.Cols.ID));
        textWiseLog.setLevel(source.getString(TextWiseLog.Cols.LEVEL));
        textWiseLog.setDescription(source.getString(TextWiseLog.Cols.DESCRIPTON));
        textWiseLog.setErrorStack(source.getString(TextWiseLog.Cols.ERROR_STACK));
        textWiseLog.setDate(source.getDate(TextWiseLog.Cols.DATE));

        return textWiseLog;
    }

    @Override
    public Class<Entity> getClassType() {

        Entity entity = new TextWiseLog();

        Class<Entity> clazz = (Class<Entity>) entity.getClass();

        return clazz;
    }
}
