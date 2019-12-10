package vn.edu.poly.testduan2.interfaces;

import java.util.List;

import vn.edu.poly.testduan2.net.response.BillResponse;

public interface DataBillTable {
    void getData(List<BillResponse> item);
}
