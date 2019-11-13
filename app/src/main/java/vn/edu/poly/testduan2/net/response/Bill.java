package vn.edu.poly.testduan2.net.response;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Bill {
    String ID;
    List<Product> products;
    String time;
    String responsible;
    String total;
    boolean status_pay = false;

    public Bill() {
    }

    public Bill(String ID, List<Product> products, String time, String responsible, String total, boolean status_pay) {
        this.ID = ID;
        this.products = products;
        this.time = time;
        this.responsible = responsible;
        this.total = total;
        this.status_pay = status_pay;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
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
