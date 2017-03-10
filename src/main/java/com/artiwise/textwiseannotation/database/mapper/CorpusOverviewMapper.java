package com.artiwise.textwiseannotation.database.mapper;

import com.artiwise.textwiseannotation.database.entity.CorpusOverview;
import com.artiwise.textwiseannotation.database.entity.Entity;
import org.bson.Document;

/**
 * Created by oktaysadoglu on 09/03/2017.
 */
public class CorpusOverviewMapper implements Mapper<Document,Entity> {

    @Override
    public Document mapToDocument(Entity source) {

        CorpusOverview corpusOverview  = CorpusOverview.class.cast(source);

        Document document = new Document();

        document.put(CorpusOverview.Cols.NAME,corpusOverview.getName());
        document.put(CorpusOverview.Cols.DEFINITION,corpusOverview.getDefinition());

        return document;

    }

    @Override
    public Entity mapToEntity(Document source) {

        CorpusOverview corpusOverview = new CorpusOverview();

        corpusOverview.setObjectId(source.getObjectId(Entity.Cols.ID));
        corpusOverview.setName(source.getString(CorpusOverview.Cols.NAME));
        corpusOverview.setDefinition(source.getString(CorpusOverview.Cols.DEFINITION));

        return corpusOverview;
    }

    @Override
    public Class<Entity> getClassType() {

        Entity entity = new CorpusOverview();

        Class<Entity> clazz = (Class<Entity>) entity.getClass();

        return clazz;

    }
}
