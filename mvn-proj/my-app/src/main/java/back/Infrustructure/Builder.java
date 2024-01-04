package back.Infrustructure;

import back.app.*;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;

interface IBuilder {
    public IApp buildApp();
}

public class Builder {
    @Inject
    @Default
    private IApp app;

    @Inject
    @Default
    private IDataBase db;

    @Inject
    @Default
    private ITokenManager manager;

    @Produces
    @Built
    public IApp buildApp() {
        ((IDataBaseUsing) app).useDB(db);
        ((ITokenManagerUsing) app).useTM(manager);

        return app;
    }
}