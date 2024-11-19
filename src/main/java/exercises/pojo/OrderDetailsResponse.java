package exercises.pojo;

import java.util.List;
import java.util.Map;

public class OrderDetailsResponse {

    private Map<String, String> data;
    private String message;

    public Map<String, String> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
