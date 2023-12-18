package Infrustructure.TokenManager;

import app.ITokenManager;

public class Factory {
    private static ITokenManager instance = null;

    public static ITokenManager createMongoStorage() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }
}
