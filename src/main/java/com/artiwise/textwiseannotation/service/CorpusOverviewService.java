package com.artiwise.textwiseannotation.service;

import com.artiwise.textwiseannotation.database.entity.CorpusOverview;
import com.artiwise.textwiseannotation.database.entity.Entity;
import com.artiwise.textwiseannotation.database.injector.DatabaseInjector;
import com.artiwise.textwiseannotation.database.injector.MongoDbInjector;
import com.artiwise.textwiseannotation.database.mapper.CorpusOverviewMapper;
import com.artiwise.textwiseannotation.database.repository.Repository;
import com.artiwise.textwiseannotation.util.TextWiseLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoWriteException;
import org.codehaus.jettison.json.JSONException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by oktaysadoglu on 09/03/2017.
 */
@Path("/corpus")
public class CorpusOverviewService {


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCorpusOverview(CorpusOverview corpusOverview) throws JsonProcessingException, JSONException {

        Repository<Entity> repository = getRepository();

        String response;

        try {

            repository.add(corpusOverview);

            response = TextWiseLog.INFO("Kayıt başarılıyla oluşturuldu.");

            return Response.status(200).entity(response).build();

        }catch (MongoWriteException e){

            if (e.getError().getCode() == 11000)
                response = TextWiseLog.ERROR("Modül İsmi Alınmış Lütfen başka modül ismi kullanınız.", e.toString());
            else
                response = TextWiseLog.ERROR("Modül Kaydedilerken bir hata oluştu",e.toString());

            e.printStackTrace();

            return Response.status(200).entity(response).build();

        }

    }

    @GET
    @Path("/getAll")
    public Response getAllCorpusOverviews() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Repository<Entity> repository = getRepository();

        List<Entity> corpusOverviews = repository.getAllDocuments();

        return Response.status(200).entity(objectMapper.writeValueAsString(corpusOverviews)).build();

    }

    @GET
    @Path("/get")
    public Response getCorpusOverview(@QueryParam("name") String name) throws JsonProcessingException {

        Repository<Entity> repository = getRepository();

        Entity entity;

        try {

            entity= repository.getDocument(CorpusOverview.Cols.NAME,name);

            return Response.status(200).entity(entity.getJson()).build();

        }catch (NullPointerException e){

            String response = TextWiseLog.ERROR("Böyle bir kayıt bulunamadı",e.toString());

            return Response.status(200).entity(response).build();

        }

    }

    @GET
    @Path("/delete")
    public Response deleteCorpusOverview(@QueryParam("name") String name) throws JsonProcessingException {

        Repository<Entity> repository = getRepository();

        Entity entity;

        String response;

        try {

            entity = repository.getDocument(CorpusOverview.Cols.NAME,name);

            repository.remove(entity);

            response = TextWiseLog.INFO("Kayıt başarıyla silindi");

            return Response.status(200).entity(response).build();

        }catch (NullPointerException e){

            response = TextWiseLog.ERROR("Böyle bir kayıt bulunamadı",e.toString());

            return Response.status(200).entity(response).build();

        }

    }

    private Repository<Entity> getRepository(){

        DatabaseInjector injector = new MongoDbInjector();

        Repository<Entity> repository = injector.getRepository(new CorpusOverviewMapper());

        return repository;

    }

}
