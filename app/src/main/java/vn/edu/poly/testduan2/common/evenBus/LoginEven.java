package vn.edu.poly.testduan2.common.evenBus;

public class LoginEven {
    public String action;
    public Object object;

    public LoginEven(String action, Object object) {
        this.action = action;
        this.object = object;
    }
}
