package app;

public class Factory {
    public static IApp createApp() {
        return new App();
    }
}
