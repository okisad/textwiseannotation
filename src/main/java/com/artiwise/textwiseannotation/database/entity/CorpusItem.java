package com.artiwise.textwiseannotation.database.entity;


import com.sun.media.jfxmedia.track.Track;

import java.util.Date;
import java.util.List;

/**
 * Created by fatihsamet on 09/03/2017.
 */
public class CorpusItem extends Entity {

    public static int statusEmpty = 0;
    public static int statusFinished = 2;
    public static int statusReserved = 1;

    public static class Cols{

        public static String CORPUS = "corpus";
        public static String TEXT = "text";
        public static String STATUS = "status";
        public static String ANNOTATIONS = "annotations";
        public static String LAST_STATUS_UPDATE = "lastStatusUpdate";
    }

    private CorpusOverview corpus;
    private String text;
    private int status;
    private List<CorpusItemAnnotation> annotations;
    private Date lastStatusUpdate;

    public CorpusItem() {
    }

    public CorpusItem(CorpusOverview corpus, String text, int status, List<CorpusItemAnnotation> annotations, Date lastStatusUpdate) {
        this.corpus = corpus;
        this.text = text;
        this.status = status;
        this.annotations = annotations;
        this.lastStatusUpdate = lastStatusUpdate;
    }

    public CorpusOverview getCorpus() {
        return corpus;
    }

    public void setCorpus(CorpusOverview corpus) {
        this.corpus = corpus;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CorpusItemAnnotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<CorpusItemAnnotation> annotations) {
        this.annotations = annotations;
    }

    public Date getLastStatusUpdate() {
        return lastStatusUpdate;
    }

    public void setLastStatusUpdate(Date lastStatusUpdate) {
        this.lastStatusUpdate = lastStatusUpdate;
    }

    @Override
    public String toString() {
        return "CorpusItem{" +
                "corpus=" + corpus +
                ", text='" + text + '\'' +
                ", status=" + status +
                ", annotations=" + annotations +
                ", lastStatusUpdate=" + lastStatusUpdate +
                '}';
    }
}
