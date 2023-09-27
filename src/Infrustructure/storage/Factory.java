package Infrustructure.storage;

import app.IDataBase;

public class Factory {
    private static IDataBase instance = null;

    public static IDataBase createTestStorage() {
        if (instance == null) {
            instance = new TestStorage();
        }
        return instance;
    }
}
