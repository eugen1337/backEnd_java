package Infrustructure.storage;

import app.IDataBase;

public class TestStorage  {

    public String save(String query) {
        System.out.println("Save method");
        return "ok";
    }

    public String load(String query) {
        System.out.println("load method");
        return "ok";
    }
}
