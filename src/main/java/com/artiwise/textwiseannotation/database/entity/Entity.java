package com.artiwise.textwiseannotation.database.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;

/**
 * Created by oktaysadoglu on 09/03/2017.
 */
public abstract class Entity {

    protected ObjectId objectId;

    public static class Cols{
        public static String ID = "_id";
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getJson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(this);

    }
}
