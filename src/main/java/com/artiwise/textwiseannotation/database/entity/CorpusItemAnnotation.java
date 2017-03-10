package com.artiwise.textwiseannotation.database.entity;

/**
 * Created by fatihsamet on 09/03/2017.
 */
public class CorpusItemAnnotation{

    private String aspectEntity;
    private String aspectFeature;
    private String term;
    private String polarCategory;

    public CorpusItemAnnotation() {
    }

    public CorpusItemAnnotation(String aspectEntity, String aspectFeature, String term, String polarCategory) {
        this.aspectEntity = aspectEntity;
        this.aspectFeature = aspectFeature;
        this.term = term;
        this.polarCategory = polarCategory;
    }

    public String getAspectEntity() {
        return aspectEntity;
    }

    public void setAspectEntity(String aspectEntity) {
        this.aspectEntity = aspectEntity;
    }

    public String getAspectFeature() {
        return aspectFeature;
    }

    public void setAspectFeature(String aspectFeature) {
        this.aspectFeature = aspectFeature;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getPolarCategory() {
        return polarCategory;
    }

    public void setPolarCategory(String polarCategory) {
        this.polarCategory = polarCategory;
    }

    @Override
    public String toString() {
        return "CorpusItemAnnotation{" +
                "aspectEntity='" + aspectEntity + '\'' +
                ", aspectFeature='" + aspectFeature + '\'' +
                ", term='" + term + '\'' +
                ", polarCategory='" + polarCategory + '\'' +
                '}';
    }
}
