package vn.edu.poly.testduan2.common.evenBus;

public class EvenTable {

    public String action;
    public Object object;

    public EvenTable(String action, Object object) {
        this.action = action;
        this.object = object;
    }
}
