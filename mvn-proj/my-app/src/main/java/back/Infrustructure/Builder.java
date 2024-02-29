package back.Infrustructure;

import back.Infrustructure.TokenManager.ITokenManager;
import back.Infrustructure.storage.IDataBase;
import back.app.*;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class Builder {
    @Inject
    @Default
    private IApp app;

    @Inject
    @Product
    private IDataBase db;

    @Inject
    @Default
    private ITokenManager tm;


    @Produces
    @Built
    public IApp buildApp() {
        ((IDataBaseUsing) app).useDB(db);
        ((ITokenManagerUsing) app).useTM(tm);

        return app;
    }
}