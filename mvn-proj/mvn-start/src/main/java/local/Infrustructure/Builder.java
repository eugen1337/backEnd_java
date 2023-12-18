package Infrustructure;

import app.ITokenManager;
import app.*;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;


public class Builder {
/*    @Inject
    @Default*/
    private IApp app = Factory.createApp() ;

    @Inject
    @Default
    private IDataBase db;
/*    @Inject
    @Default
    private ITokenManager manager;*/

/*    @Produces
    @Built*/
    public IApp buildApp() {

        ((IDataBaseUsing) app).useDB(db);
        System.out.println("Start login");
/*        ((ITokenManagerUsing) app).useTM(manager);
        System.out.println("Start login");*/

        return app;
    }
}