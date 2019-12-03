package vn.edu.poly.testduan2.view.activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import butterknife.BindView;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.common.evenBus.EvenUpdate;
import vn.edu.poly.testduan2.common.evenBus.EvenUpdateAction;
import vn.edu.poly.testduan2.common.evenBus.EventBusAction;
import vn.edu.poly.testduan2.common.evenBus.MessageEvent;
import vn.edu.poly.testduan2.controller.BillAdapter;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;
import vn.edu.poly.testduan2.net.response.Bill;

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
    private BillAdapter adapter;
    private Bill bill;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_bill;
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {
        setUptotal();
    }

    @Override
    protected String getNavigationTitle() {
//        if (bill != null){
//            return bill.getID();
//        }
        return "Bill";
    }

    @SuppressLint("NewApi")
    @Override
    protected Icon getRightButtonIcon() {
        return Icon.createWithResource(this, R.drawable.ic_more_horiz_white_24dp);
    }


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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN_ORDERED)
    public void handleEvent(MessageEvent event) {
        switch (event.action) {
            case EventBusAction.DATA_BILL:
                bill = (Bill) event.object;
                setUp(bill);
                break;
        }
        updateNavigationBar();
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
                FirebaseManager.insertBill(bill);
                ConstactChange.productList.clear();
                EventBus.getDefault().post(new EvenUpdate(EvenUpdateAction.UPDATE_LIST_BILL_SIZE));
                Toast.makeText(this, "Pay Success!", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.tv_Noti:
                break;
        }
    }

    private void setUp(Bill item) {
        adapter = new BillAdapter(this, item.getProducts());
        rvItemBill.setLayoutManager(new LinearLayoutManager(this));
        rvItemBill.setHasFixedSize(true);
        rvItemBill.setAdapter(adapter);

        if (bill != null) {
            int total = 0;
            for (int j = 0; j < bill.getProducts().size(); j++){
                total = total + Integer.parseInt(bill.getProducts().get(j).getTotal());
            }
            tvTotal.setText(String.valueOf(total));
        }

    }

    private void setUptotal() {
        tvTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (bill != null) {
                    int total = 0;
                    for (int j = 0; j < bill.getProducts().size(); j++){
                        total = total + Integer.parseInt(bill.getProducts().get(j).getTotal());
                    }
                    tvTotal.setText(String.valueOf(total));
                }
                updateNavigationBar();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
