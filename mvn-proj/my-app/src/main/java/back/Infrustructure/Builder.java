package back.Infrustructure;

import back.app.*;
// import jakarta.enterprise.inject.Default;
// import jakarta.inject.Inject;

public class Builder {
    // @Inject
    // @Default
    // private IApp app;

    // @Inject
    // @Default
    // private IDataBase db;

    public IApp buildApp() {

        IDataBase db = back.Infrustructure.storage.PostgreFactory.createPostgreStorage();
        ITokenManager tm = back.Infrustructure.TokenManager.TMFactory.createTokenManager();

        IApp app = back.app.Factory.createApp();

        ((IDataBaseUsing) app).useDB(db);
        ((ITokenManagerUsing) app).useTM(tm);

        return app;
    }
}