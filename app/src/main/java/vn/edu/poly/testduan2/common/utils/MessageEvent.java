package vn.edu.poly.testduan2.common.utils;


public class MessageEvent {
    public String action;
    public Object object;
    public int position;

    public MessageEvent(String action, Object object, int position) {
        this.action = action;
        this.object = object;
        this.position = position;
    }
}
