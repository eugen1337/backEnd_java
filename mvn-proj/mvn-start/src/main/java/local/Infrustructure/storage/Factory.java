package Infrustructure.storage;

import app.IDataBase;

public class Factory {
    private static IDataBase instance = null;

    public static IDataBase createMongoStorage() {
        if (instance == null) {
            instance = new MongoDB();
        }
        return instance;
    }
}
