package com.artiwise.textwiseannotation.database.connection;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * Created by oktaysadoglu on 09/03/2017.
 */
public class MongoConnection implements Connection{

    private String host;
    private int port;
    private String databaseName;
    private MongoClient client;

    public MongoConnection(String host, int port, String databaseName) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.client = new MongoClient(new ServerAddress(host, port));
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public MongoClient getClient() {
        return client;
    }

    public void setClient(MongoClient client) {
        this.client = client;
    }

}
