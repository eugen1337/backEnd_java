package app;

import domain.Model;
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

        response = String.valueOf(
                Model.calc(
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
