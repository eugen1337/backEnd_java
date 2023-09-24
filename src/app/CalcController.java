package app;

import domain.Model;
import java.util.Map;

public class CalcController implements IBackController {
    public CalcController() {
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
        System.out.println(response);

        return response;
    }
}
