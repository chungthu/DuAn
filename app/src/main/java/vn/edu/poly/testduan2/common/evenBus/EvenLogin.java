package vn.edu.poly.testduan2.common.evenBus;

public class EvenLogin {

    public String action;
    public Object object;

    public EvenLogin(String action, Object object) {
        this.action = action;
        this.object = object;
    }
}
