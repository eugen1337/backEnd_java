package Infrustructure;

import app.App;
import app.IApp;
import app.IDataBase;

public class Builder {
    //public String dataBase;
    public static IApp build(IDataBase db)
    {
        if (db != null) return new App(db);
        else return new App();

    }
}