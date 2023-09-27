package Infrustructure;

import app.*;
import app.services.TestService;

public class Builder {
    public static ITestService buildTestService() {

        IDataBase db = new TestStorage();

        ITestService service = Factory.createTestService();
        ((IDataBaseUsing)service).useDB(db);

        return service;
    }
    public static IApp build(IDataBase db)
    {
        if (db != null) return new App(db);
        else return new App();
    }
}