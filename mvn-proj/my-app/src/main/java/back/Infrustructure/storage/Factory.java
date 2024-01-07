package back.Infrustructure.storage;

import back.app.IDataBase;

public class Factory {
    private static IDataBase instance = null;

    public static IDataBase createMongoStorage() {
        if (instance == null) {
            instance = new MongoDB();
        }
        return instance;
    }
}
