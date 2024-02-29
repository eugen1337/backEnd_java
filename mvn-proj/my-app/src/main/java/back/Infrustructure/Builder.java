package back.Infrustructure;

import back.Infrustructure.TokenManager.ITokenManager;
import back.Infrustructure.storage.IDataBase;
import back.app.*;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

interface IBuilder {
    public IApp buildApp();
}

public class Builder implements IBuilder {
    // @Inject
    // @Default
    // private IApp app;

    // @Inject
    // @Product
    // private IDataBase db;
    // @Inject
    // @Default
    // private ITokenManager tm;


    @Produces
    @Built
    public IApp buildApp() {
        IDataBase db = back.Infrustructure.storage.PostgreFactory.createPostgreStorage();

        ITokenManager tm = back.Infrustructure.TokenManager.TMFactory.createTokenManager();

        IApp app = back.app.Factory.createApp();

        ((IDataBaseUsing) app).useDB(db);
        ((ITokenManagerUsing) app).useTM(tm);

        return app;
    }
}