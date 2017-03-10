package com.artiwise.textwiseannotation.service;

import com.artiwise.textwiseannotation.database.entity.CorpusItem;
import com.artiwise.textwiseannotation.database.entity.CorpusItemAnnotation;
import com.artiwise.textwiseannotation.database.entity.Entity;
import com.artiwise.textwiseannotation.database.injector.DatabaseInjector;
import com.artiwise.textwiseannotation.database.injector.MongoDbInjector;
import com.artiwise.textwiseannotation.database.mapper.CorpusItemMapper;
import com.artiwise.textwiseannotation.database.repository.Repository;
import com.artiwise.textwiseannotation.util.ReturnJSONS;
import com.artiwise.textwiseannotation.util.TextWiseLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by oktaysadoglu on 10/03/2017.
 */

@Path("corpusItem")
public class CorpusItemService {

    @Path("get")
    @GET
    public Response getCorpusItem(@QueryParam("id") String id) throws JsonProcessingException {

        Repository<Entity> repository = getRepository();

        CorpusItem corpusItem;

        String response;

        try {

            corpusItem = CorpusItem.class.cast(repository.getDocument(Entity.Cols.ID,new ObjectId(id)));

            return Response.status(200).entity(corpusItem.getJson()).build();

        }catch (NullPointerException e){

            response = TextWiseLog.ERROR("Böyle bir kayıt bulunamadı",e.toString());

            return Response.status(200).entity(response).build();

        }

    }

    @Path("update/status")
    @POST
    public Response updateCorpusItemStatus(@FormParam("id") String id,@FormParam("status") int status) throws JsonProcessingException {

        Repository<Entity> repository = getRepository();

        CorpusItem corpusItem;

        String response;

        try {

            corpusItem = CorpusItem.class.cast(repository.getDocument(Entity.Cols.ID,new ObjectId(id)));

            corpusItem.setStatus(status);

            corpusItem.setLastStatusUpdate(new Date());

            repository.update(corpusItem);

            response = TextWiseLog.INFO("Kayıt başarıyla güncellendi");

            return Response.status(200).entity(response).build();

        }catch (NullPointerException e){

            response = TextWiseLog.ERROR("Böyle bir kayıt bulunamadı",e.toString());

            return Response.status(200).entity(response).build();

        }


    }

    @Path("/update/annotations")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCorpusItemAnnotations(@FormParam("id") String id, List<CorpusItemAnnotation> corpusItemAnnotations){

        Repository<Entity> repository = getRepository();

        CorpusItem corpusItem;

        try {

            corpusItem = CorpusItem.class.cast(repository.getDocument(Entity.Cols.ID,new ObjectId(id)));

            corpusItem.setAnnotations(corpusItemAnnotations);

            return Response.status(200).build();

        }catch (NullPointerException e){
            return Response.status(200).build();
        }

    }

    private Repository<Entity> getRepository(){

        DatabaseInjector injector = new MongoDbInjector();

        return injector.getRepository(new CorpusItemMapper());

    }

}
