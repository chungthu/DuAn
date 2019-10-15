package vn.edu.poly.testduan2.model;

public class Bill {
    private String bill;
    private String price;

    public Bill(String bill, String kind, String size, String price) {
        this.bill = bill;
        this.price = price;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
