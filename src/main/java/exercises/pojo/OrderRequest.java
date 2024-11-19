package exercises.pojo;

import java.util.List;
import java.util.Map;

public class OrderRequest {

    private List<Map<String, String>> orders;

    public OrderRequest(List<Map<String, String>> orders) {
        this.orders = orders;
    }

    public void setOrders(List<Map<String, String>> orders) {
        this.orders = orders;
    }

}
