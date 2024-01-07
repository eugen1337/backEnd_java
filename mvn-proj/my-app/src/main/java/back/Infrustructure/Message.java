package back.Infrustructure;

public class Message {
    private String text;

    public Object setText(String message) {
        this.text = message;
        return this;
    }

    public String getText() {
        return text;
    }
}
