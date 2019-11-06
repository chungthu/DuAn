package vn.edu.poly.testduan2.controller;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.view.fragment.AllTableFragment;
import vn.edu.poly.testduan2.view.fragment.EmptyTableFragment;
import vn.edu.poly.testduan2.view.fragment.UseTableFragment;

public class AllTableAdapter extends FragmentPagerAdapter {

    private static final int[] TAB_TITLES = new int[]{R.string.key_all_table, R.string.key_use_table, R.string.empty_table};
    private final Context mContext;

    public AllTableAdapter(@NonNull FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new Fragment();
        switch (i) {
            case 0:
                fragment = AllTableFragment.newInstance();
                break;
            case 1:
                fragment = UseTableFragment.newInstance();
                break;
            case 2:
                fragment = EmptyTableFragment.newInstance();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
}
