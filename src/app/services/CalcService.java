package app.services;

import domain.ISum;
import domain.SumFactory;

import java.util.Map;

public class CalcService implements IService {
    private String path;
    public CalcService() {
        path = "/calc";
        System.out.println("Calc is ready");
    };
    @Override
    public String execute(Map<String, String> params) {
        String response = "";

        ISum model = SumFactory.createCalculator();

        response = String.valueOf(
                model.sum(
                        Integer.valueOf(params.get("a")),
                        Integer.valueOf(params.get("b"))
                )
        );

        return response;
    }

    @Override
    public String getPath() {
        return path;
    }
}
