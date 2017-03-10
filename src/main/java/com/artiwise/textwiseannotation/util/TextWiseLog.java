package com.artiwise.textwiseannotation.util;

import com.artiwise.textwiseannotation.database.entity.Entity;
import com.artiwise.textwiseannotation.database.injector.DatabaseInjector;
import com.artiwise.textwiseannotation.database.injector.MongoDbInjector;
import com.artiwise.textwiseannotation.database.mapper.TextWiseLogMapper;
import com.artiwise.textwiseannotation.database.repository.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

/**
 * Created by oktaysadoglu on 09/03/2017.
 */
public class TextWiseLog extends Entity{

    public static String ERROR = "error";
    public static String INFO = "info";

    private String level;
    private String description;
    private String errorStack;
    private Date date;

    public static class Cols{

        public static String LEVEL = "level";
        public static String DESCRIPTON = "description";
        public static String ERROR_STACK = "errorStack";
        public static String DATE = "date";

    }

    public TextWiseLog() {
    }


    public static String INFO(String description) throws JsonProcessingException {

        TextWiseLog textWiseLog = new TextWiseLog();

        textWiseLog.setLevel(INFO);
        textWiseLog.setDescription(description);
        textWiseLog.setDate(new Date());

        DatabaseInjector injector = new MongoDbInjector();

        Repository<Entity> repository = injector.getRepository(new TextWiseLogMapper());

        repository.add(textWiseLog);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(textWiseLog);

    }

    public static String ERROR(String errorDescription, String errorStack) throws JsonProcessingException {

        TextWiseLog textWiseLog = new TextWiseLog();

        textWiseLog.setLevel(ERROR);
        textWiseLog.setDescription(errorDescription);
        textWiseLog.setErrorStack(errorStack);
        textWiseLog.setDate(new Date());

        DatabaseInjector injector = new MongoDbInjector();

        Repository<Entity> repository = injector.getRepository(new TextWiseLogMapper());

        repository.add(textWiseLog);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(textWiseLog);

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
