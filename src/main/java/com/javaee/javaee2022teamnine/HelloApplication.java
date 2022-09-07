package com.javaee.javaee2022teamnine;

import com.javaee.javaee2022teamnine.util.DBConnectionService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends Application {
    DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }
}