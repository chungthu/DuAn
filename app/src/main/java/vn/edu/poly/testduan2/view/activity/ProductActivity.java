package vn.edu.poly.testduan2.view.activity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.controller.TabProdcutAdapter;

public class ProductActivity extends BaseActivity {


    @BindView(R.id.tab_product)
    TabLayout tabProduct;
    @BindView(R.id.vp_product)
    ViewPager vpProduct;
    private TabProdcutAdapter adapter;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {
        setUpTabs();
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
        this.adapter = new TabProdcutAdapter(getSupportFragmentManager(), ProductActivity.this);
        this.vpProduct.setAdapter(adapter);
        this.tabProduct.setupWithViewPager(vpProduct);
    }
}
