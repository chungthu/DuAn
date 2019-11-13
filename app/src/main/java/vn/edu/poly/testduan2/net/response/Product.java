package vn.edu.poly.testduan2.net.response;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {

    String id;
    String name;
    String image;
    String amount;
    String price;
    String total;

    public Product(String id, String name, String image, String amount, String price, String total) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.price = price;
        this.total = total;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
