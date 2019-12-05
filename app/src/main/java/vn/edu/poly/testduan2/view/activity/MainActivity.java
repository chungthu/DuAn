package vn.edu.poly.testduan2.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.controller.FragmentMainPagerAdapter;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_main)
    ViewPager mViewPager;
    private TabLayout mTabs;
    private ImageView imgFooter;
    private FragmentMainPagerAdapter mViewPagerAdapter;


    @Override
    protected NavigationStyle getNavigationStyle() {
        return NavigationStyle.None;
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {
        this.setUpViewPager();
        this.setUpTabs();
    }

    @SuppressLint("WrongConstant")
    private void setUpTabs() {
        mTabs.setTabMode(TabLayout.MODE_FIXED);
        mTabs.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0, false);
        for (int i = 0; i < mTabs.getTabCount(); i++) {
            TabLayout.Tab tab = mTabs.getTabAt(i);
            assert tab != null;
            tab.setCustomView(mViewPagerAdapter.getItemView(this, i));
            View view = tab.getCustomView();
            assert view != null;
            imgFooter = view.findViewById(R.id.imgTabIcon);
            switch (i) {
                case 0:
                    imgFooter.setImageResource(R.drawable.logo);
                    break;
                case 1:
                    imgFooter.setImageResource(R.drawable.ic_account_circle_black_24dp);
                    break;
                default:
                    break;
            }
        }

        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                imgFooter = view.findViewById(R.id.imgTabIcon);
                mViewPager.setCurrentItem(tab.getPosition(), false);
                switch (tab.getPosition()) {
                    case 0:
                        imgFooter.setImageResource(R.drawable.logo);
                        break;
                    case 1:
                        imgFooter.setImageResource(R.drawable.ic_account_white_24dp);
                        break;
                    default:
                        break;
                }
            }

            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                imgFooter = view != null ? view.findViewById(R.id.imgTabIcon) : null;
                switch (tab.getPosition()) {
                    case 0:
                        imgFooter.setImageResource(R.drawable.logo);
                        break;
                    case 1:
                        imgFooter.setImageResource(R.drawable.ic_account_circle_black_24dp);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    @SuppressLint("WrongViewCast")
    private void setUpViewPager() {
        this.mTabs = findViewById(R.id.tab_main);
        this.mViewPagerAdapter = new FragmentMainPagerAdapter(getSupportFragmentManager());
        this.mViewPager.setAdapter(mViewPagerAdapter);
        this.mViewPager.setCurrentItem(0, false);
        this.mViewPager.setOffscreenPageLimit(2);
    }

}
