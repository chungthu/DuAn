package vn.edu.poly.testduan2.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewFragment;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.common.Constants;
import vn.edu.poly.testduan2.common.evenBus.EvenTable;
import vn.edu.poly.testduan2.common.evenBus.EvenUpdate;
import vn.edu.poly.testduan2.common.evenBus.EvenUpdateAction;
import vn.edu.poly.testduan2.common.evenBus.EventBusAction;
import vn.edu.poly.testduan2.common.evenBus.MessageEvent;
import vn.edu.poly.testduan2.common.utils.Utils;
import vn.edu.poly.testduan2.controller.TabProdcutAdapter;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;
import vn.edu.poly.testduan2.net.response.BillResponse;
import vn.edu.poly.testduan2.net.response.TableResponse;
import vn.edu.poly.testduan2.view.fragment.SearchFragment;

public class ListProductActivity extends BaseActivity {

    @BindView(R.id.tab_product)
    TabLayout tabProduct;
    @BindView(R.id.vp_product)
    ViewPager vpProduct;
    @BindView(R.id.ll_reChoose)
    LinearLayout llReChoose;
    @BindView(R.id.tv_Amount)
    TextView tvAmount;
    @BindView(R.id.ll_finish)
    LinearLayout llFinish;
    private TabProdcutAdapter adapter;
    private TableResponse tableResponse;
    private FirebaseManager firebaseManager = new FirebaseManager();

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_list_product;
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {
        setUpTabs();
    }


    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        ConstactChange.productList.clear();
    }

    @Override
    protected String getNavigationTitle() {
        return getString(R.string.key_choose_food);
    }


    @Override
    protected void leftButtonClicked(View v) {
        super.leftButtonClicked(v);
    }

    private void setUpTabs() {
        this.adapter = new TabProdcutAdapter(getSupportFragmentManager(), ListProductActivity.this);
        this.vpProduct.setOffscreenPageLimit(3);
        this.vpProduct.setAdapter(adapter);
        this.tabProduct.setupWithViewPager(vpProduct);
        if (ConstactChange.productList != null) {
            tvAmount.setText(String.valueOf(ConstactChange.productList.size()));
        }
        tvAmount.setText(String.valueOf(ConstactChange.productList.size()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void handleEvent(EvenUpdate event) {
        switch (event.action) {
            case EvenUpdateAction.UPDATE_LIST_BILL_SIZE:
                tvAmount.setText(String.valueOf(ConstactChange.productList.size()));
                break;
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN_ORDERED)
    public void handleEventTable(EvenTable event) {
        switch (event.action) {
            case EventBusAction.TABLE_SELECT:
                tableResponse = (TableResponse) event.object;
                break;
        }
    }

    @OnClick({R.id.ll_reChoose, R.id.ll_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_reChoose:
                tvAmount.setText(String.valueOf(0));
                ConstactChange.productList.clear();
                break;
            case R.id.ll_finish:
                addBill();
                break;
        }
    }

    private void addBill() {
        if (ConstactChange.Status_Table == 0){
            if (ConstactChange.productList.size() > 0) {
                startActivity(new Intent(this, BillActivity.class));
                String key = FirebaseManager.mDatabaseBill.push().getKey();
                Date currentTime = Calendar.getInstance().getTime();
                String time = new SimpleDateFormat(Constants.DEFAULT_FORMAT_DATETIME, Locale.getDefault()).format(currentTime);
                int total = 0;
                for (int i = 0; i < ConstactChange.productList.size(); i++) {
                    total = total + Integer.parseInt(ConstactChange.productList.get(i).getTotal());
                }
                String totals = String.valueOf(total);
                String namebill = Utils.nameBill();
                if (tableResponse != null) {
                    BillResponse billResponse = new BillResponse(key,
                            namebill,
                            tableResponse.getId(),
                            ConstactChange.productList,
                            time,
                            ConstactChange.USER_RESPONSE.getId(),
                            totals,
                            false);
                    EventBus.getDefault().postSticky(new MessageEvent(EventBusAction.DATA_BILL,billResponse, 0));
                    int i = ConstactChange.USER_RESPONSE.getBill_position();
                    i++;
                    ConstactChange.USER_RESPONSE.setBill_position(i);
                    firebaseManager.updateUser(ConstactChange.USER_RESPONSE.getId(), ConstactChange.USER_RESPONSE);
                } else {
                    BillResponse billResponse = new BillResponse(key,
                            namebill,
                            "",
                            ConstactChange.productList,
                            time,
                            ConstactChange.USER_RESPONSE.getId(),
                            totals,
                            false);
                    EventBus.getDefault().postSticky(new MessageEvent(EventBusAction.DATA_BILL, billResponse, 0));
                    int i = ConstactChange.USER_RESPONSE.getBill_position();
                    i++;
                    ConstactChange.USER_RESPONSE.setBill_position(i);
                    firebaseManager.updateUser(ConstactChange.USER_RESPONSE.getId(), ConstactChange.USER_RESPONSE);
                }
            } else {
                Toast.makeText(this, "You need to choose at least 1 product!", Toast.LENGTH_SHORT).show();
            }
        }else {
            finish();
        }
    }

    private void Search() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_container1, new SearchFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
