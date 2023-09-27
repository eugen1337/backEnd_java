package app;

import app.IDataBase;
import app.IDataBaseUsing;
import app.IApp;
import app.services.CalcService;
import app.services.IService;
import app.services.LoginService;

import java.util.Map;

public class App implements IApp, IDataBaseUsing {
    private IDataBase db;
    @Override
    public String login(int variant) {
        IService loginServ = new LoginService();
        return db.load("test");
    }

    @Override
    public String calc(Map<String, String> params) {
        IService calcServ = new CalcService();
        return calcServ.execute(params);
    }

    @Override
    public void useDB(IDataBase db) {
        this.db = db;
    }
}
