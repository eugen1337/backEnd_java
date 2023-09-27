package domain;

public class SumFactory {
    public static ISum createCalculator() {
        return (ISum)new Model();
    }
}
