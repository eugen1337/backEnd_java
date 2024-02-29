package back.Infrustructure.storage;

public class MongoFactory {
    private static IDataBase instance = null;

    public static IDataBase createMongoStorage() {
        if (instance == null) {
            instance = new MongoDB();
        }
        return instance;
    }
}
