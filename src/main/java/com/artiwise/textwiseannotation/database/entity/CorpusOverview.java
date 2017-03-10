package com.artiwise.textwiseannotation.database.entity;

/**
 * Created by fatihsamet on 09/03/2017.
 */
public class CorpusOverview  extends Entity{
    public String name;
    public String definition;

    public static class Cols{

        public static String NAME = "name";
        public static String DEFINITION = "definition";
    }

    public CorpusOverview(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }

    public CorpusOverview() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "CorpusOverview{" +
                "name='" + name + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}
