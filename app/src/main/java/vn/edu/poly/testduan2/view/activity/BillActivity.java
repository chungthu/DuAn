package vn.edu.poly.testduan2.view.activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.controller.BillAdapter;

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

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_bill;
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {

    }

    @SuppressLint("NewApi")
    @Override
    protected Icon getRightButtonIcon() {
        return Icon.createWithResource(this, R.drawable.ic_more_horiz_white_24dp);
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
                break;
            case R.id.tv_Noti:
                break;
        }
    }

    private void setUp(){

        adapter = new BillAdapter(this, ConstactChange.productList);
        rvItemBill.setLayoutManager(new LinearLayoutManager(this));
        rvItemBill.setHasFixedSize(true);

    }
}
