package vn.edu.poly.testduan2.net.response;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class BillResponse {
    String id;
    String name;
    String id_table;
    List<Product> products;
    String time;
    String id_responsible;
    String total;
    boolean status_pay = false;

    public BillResponse() {
    }

    public BillResponse(String id, String name, String id_table, List<Product> products, String time, String id_responsible, String total, boolean status_pay) {
        this.id = id;
        this.name = name;
        this.id_table = id_table;
        this.products = products;
        this.time = time;
        this.id_responsible = id_responsible;
        this.total = total;
        this.status_pay = status_pay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_table() {
        return id_table;
    }

    public void setId_table(String id_table) {
        this.id_table = id_table;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId_responsible() {
        return id_responsible;
    }

    public void setId_responsible(String id_responsible) {
        this.id_responsible = id_responsible;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public boolean isStatus_pay() {
        return status_pay;
    }

    public void setStatus_pay(boolean status_pay) {
        this.status_pay = status_pay;
    }
}
