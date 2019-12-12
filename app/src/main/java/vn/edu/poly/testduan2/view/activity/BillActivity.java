package vn.edu.poly.testduan2.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.common.evenBus.EvenBill;
import vn.edu.poly.testduan2.common.evenBus.EvenTable;
import vn.edu.poly.testduan2.common.evenBus.EvenUpdate;
import vn.edu.poly.testduan2.common.evenBus.EvenUpdateAction;
import vn.edu.poly.testduan2.common.evenBus.EventBusAction;
import vn.edu.poly.testduan2.common.evenBus.MessageEvent;
import vn.edu.poly.testduan2.common.utils.Utils;
import vn.edu.poly.testduan2.controller.BillAdapter;
import vn.edu.poly.testduan2.interfaces.DataBillTable;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;
import vn.edu.poly.testduan2.net.response.BillResponse;
import vn.edu.poly.testduan2.net.response.TableResponse;

public class BillActivity extends BaseActivity {

    @BindView(R.id.tv_status_order)
    TextView tvStatusOrder;
    @BindView(R.id.ll_status_order)
    LinearLayout llStatusOrder;
    @BindView(R.id.rv_item_bill)
    RecyclerView rvItemBill;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_Pay)
    TextView tvPay;
    @BindView(R.id.tv_Noti)
    TextView tvNoti;
    @BindView(R.id.tv_table)
    TextView tv_Table;
    private BillAdapter adapter;
    private BillResponse billResponse;
    private TableResponse tableResponse;
    private List<BillResponse> list = new ArrayList<>();
    private FirebaseManager firebaseManager = new FirebaseManager();
    private String title = "";

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_bill;
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected String getNavigationTitle() {
        if (ConstactChange.Status_Table == 1){
            return title;
        }
        return Utils.nameBill();
    }

//    @SuppressLint("NewApi")
//    @Override
//    protected Icon getRightButtonIcon() {
//        return Icon.createWithResource(this, R.drawable.ic_more_horiz_white_24dp);
//    }


    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ConstactChange.Status_Table == 1){
            ConstactChange.productList.clear();
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN_ORDERED)
    public void handleEvent(MessageEvent event) {
        switch (event.action) {
            case EventBusAction.DATA_BILL:
                billResponse = (BillResponse) event.object;
                setUp(billResponse);
                break;
        }
        updateNavigationBar();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN_ORDERED)
    public void handleEventTable(EvenTable event) {
        switch (event.action) {
            case EventBusAction.TABLE_SELECT:
                tableResponse = (TableResponse) event.object;
                if (tableResponse != null) {
                    tv_Table.setText(tableResponse.getName());
                    setUpIfOrder();
                } else {
                    tv_Table.setText(getString(R.string.key_buy_on));
                }
                break;
        }
    }


    @Override
    protected void rightButtonClicked(View v) {
        super.rightButtonClicked(v);
    }

    @OnClick({R.id.ll_status_order, R.id.tv_Pay, R.id.tv_Noti})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_status_order:
                break;
            case R.id.tv_Pay:
                if (ConstactChange.Status_Table == 0) {
                    firebaseManager.insertBill(billResponse);
                    if (tableResponse != null) {
                        tableResponse.setStatus(1);
                        firebaseManager.updateTable(tableResponse.getId(), tableResponse);
                    }
                    ConstactChange.productList.clear();
                    EventBus.getDefault().post(new EvenUpdate(EvenUpdateAction.UPDATE_LIST_BILL_SIZE));
                    Toast.makeText(this, "Order Success!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BillActivity.this, MainActivity.class));
                    finish();
                } else {
                    firebaseManager.updateBill(list.get(0).getId(), list.get(0));
                    Toast.makeText(this, "Update Success!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BillActivity.this, MainActivity.class));
                    finish();
                }
                break;
            case R.id.tv_Noti:
                break;
        }
    }

    private void setUp(BillResponse item) {
        adapter = new BillAdapter(this, item.getProducts());
        rvItemBill.setLayoutManager(new LinearLayoutManager(this));
        rvItemBill.setHasFixedSize(true);
        rvItemBill.setAdapter(adapter);

        if (billResponse != null) {
            int total = 0;
            for (int j = 0; j < billResponse.getProducts().size(); j++) {
                total = total + Integer.parseInt(billResponse.getProducts().get(j).getTotal());
            }
            tvTotal.setText(String.valueOf(total));
        }
    }

    private void setUpIfOrder() {
        if (ConstactChange.Status_Table == 1) {
            firebaseManager.bill(tableResponse.getId());
            tvPay.setText(getString(R.string.key_update));

            firebaseManager.setDataBillTable(new DataBillTable() {
                @Override
                public void getData(List<BillResponse> item) {
                    list = item;
                    if (list != null){
                        title = list.get(0).getName();
                        updateNavigationBar();
                        billResponse = list.get(0);
                        setUp(billResponse);
                        ConstactChange.productList.clear();
                        ConstactChange.productList = billResponse.getProducts();
                        tvTotal.setText(billResponse.getTotal());
                    }
                }
            });
        }
    }


}
