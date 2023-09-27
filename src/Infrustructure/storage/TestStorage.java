package Infrustructure.storage;

import app.IDataBase;

public class TestStorage implements IDataBase {

    @Override
    public String save(String query) {
        System.out.println("Save method");
        return "ok";
    }

    @Override
    public String load(String query) {
        System.out.println("load method");
        return "ok";
    }
}
