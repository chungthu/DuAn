package vn.edu.poly.testduan2.common.evenBus;

public class EvenBill {

    public String action;
    public Object object;

    public EvenBill(String action, Object object) {
        this.action = action;
        this.object = object;
    }
}
