package back.Infrustructure.storage;

import back.app.IDataBase;

public class PostgreFactory {
    private static IDataBase instance = null;

    public static IDataBase createPostgreStorage() {
        if (instance == null) {
            instance = new PostgreDB();
        }
        return instance;
    }
}
