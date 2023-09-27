package app;

import app.services.TestService;

public class Factory {
    public static TestService createTestService() {
        return new TestService();
    }
}
