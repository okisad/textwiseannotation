package com.artiwise.textwiseannotation.exec;

import com.artiwise.textwiseannotation.database.entity.CorpusItem;
import com.artiwise.textwiseannotation.database.entity.CorpusItemAnnotation;
import com.artiwise.textwiseannotation.database.entity.CorpusOverview;
import com.artiwise.textwiseannotation.database.entity.Entity;
import com.artiwise.textwiseannotation.database.injector.DatabaseInjector;
import com.artiwise.textwiseannotation.database.injector.MongoDbInjector;
import com.artiwise.textwiseannotation.database.mapper.CorpusItemMapper;
import com.artiwise.textwiseannotation.database.mapper.CorpusOverviewMapper;
import com.artiwise.textwiseannotation.database.repository.Repository;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by oktaysadoglu on 09/03/2017.
 */
public class Main {

    public static void main(String[] args){

        DatabaseInjector databaseInjector = new MongoDbInjector();

        Repository<Entity> repository = databaseInjector.getRepository(new CorpusOverviewMapper());

        CorpusOverview corpusOverview = new CorpusOverview();
        corpusOverview.setDefinition("Modül 1");
        corpusOverview.setName("Modül 1");
        repository.add(corpusOverview);

/*        CorpusOverview corpusOverview = CorpusOverview.class.cast(repository.getDocument(CorpusOverview.Cols.NAME,"Modül 1"));

        repository =databaseInjector.getRepository(new CorpusItemMapper());

        *//*List<CorpusItemAnnotation> corpusItemAnnotations = new ArrayList<>();
        corpusItemAnnotations.add(new CorpusItemAnnotation("a","a","a","a"));
        corpusItemAnnotations.add(new CorpusItemAnnotation("b","b","b","b"));*//*

        CorpusItem corpusItem = CorpusItem.class.cast(repository.getDocument("status",2));*//*new CorpusItem(corpusOverview,"deneme",2,corpusItemAnnotations,new Date());*//*

        System.out.print(corpusItem.toString());*/

        /*repository.add(corpusItem);*/

    }

}
