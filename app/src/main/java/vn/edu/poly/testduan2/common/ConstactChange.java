package vn.edu.poly.testduan2.common;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testduan2.net.response.BreadFirebase;
import vn.edu.poly.testduan2.net.response.FruitFirebase;
import vn.edu.poly.testduan2.net.response.MilkTeaFirebase;
import vn.edu.poly.testduan2.net.response.Product;
import vn.edu.poly.testduan2.net.response.UserResponse;

public class ConstactChange {

    public static int STATUS_ADD = 1;
    public static MilkTeaFirebase MILKTEA;
    public static FruitFirebase FRUIT;
    public static BreadFirebase BREAD;
    public static List<Product> productList = new ArrayList<>();
    public static int id_position = 1;
    public static UserResponse USER_RESPONSE;
    public static int Status_Table = 0;
    public static List<MilkTeaFirebase> listMilkTeaSearch = new ArrayList<>();
    public static List<FruitFirebase> listFruitSearch = new ArrayList<>();
    public static List<BreadFirebase> listBreadSearch = new ArrayList<>();

}
