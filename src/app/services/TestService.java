package app.services;

import app.IDataBase;
import app.IDataBaseUsing;
import app.ITestService;

import java.util.Map;

public class TestService implements ITestService, IDataBaseUsing {
    private IDataBase db;
    @Override
    public String login(int variant) {
        return "ok";
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
