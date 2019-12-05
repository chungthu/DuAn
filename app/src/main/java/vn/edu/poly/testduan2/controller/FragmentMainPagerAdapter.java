package vn.edu.poly.testduan2.controller;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.TabItemFragment;
import vn.edu.poly.testduan2.view.fragment.SetupTableFragment;
import vn.edu.poly.testduan2.view.fragment.UserFragment;

public class FragmentMainPagerAdapter extends FragmentPagerAdapter {

    public FragmentMainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        switch (i) {
            case 0:
                fragment = new TabItemFragment(SetupTableFragment.newInstance());
                break;
            case 1:
                fragment = new TabItemFragment(UserFragment.newInstance());
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public View getItemView(Context mContext, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fixed_tabs, null);
        ImageView imgIcon = view.findViewById(R.id.imgTabIcon);
        switch (position) {
            case 0:
                imgIcon.setImageResource(R.drawable.logo);
                break;
            case 1:
                imgIcon.setImageResource(R.drawable.ic_account_circle_black_24dp);
                break;
            default:
                break;
        }
        return view;
    }
}
