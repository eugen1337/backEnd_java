package Infrustructure;

import app.*;
public class Builder {
    public static IApp buildApp() {

        IDataBase db = Infrustructure.storage.Factory.createTestStorage();

        IApp service = app.Factory.createApp();
        ((IDataBaseUsing)service).useDB(db);

        return service;
    }
}