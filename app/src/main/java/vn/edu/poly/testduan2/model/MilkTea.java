package vn.edu.poly.testduan2.model;

public class MilkTea {

    private String type;
    private byte[] imgMilk;
    private String title;
    private int price1;
    private int price2;
    private String topping;

    public MilkTea(){

    }

    public MilkTea(String type, byte[] imgMilk, String title, int price1, int price2, String topping) {
        this.type = type;
        this.imgMilk = imgMilk;
        this.title = title;
        this.price1 = price1;
        this.price1 = price2;
        this.topping = topping;
    }

    public int getPrice1() {
        return price1;
    }

    public void setPrice1(int price1) {
        this.price1 = price1;
    }

    public int getPrice2() {
        return price2;
    }

    public void setPrice2(int price2) {
        this.price2 = price2;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImgMilk(byte[] imgMilk) {
        this.imgMilk = imgMilk;
    }

    public byte[] getImgMilk() {
        return imgMilk;
    }
}
