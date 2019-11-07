package vn.edu.poly.testduan2.controller;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.view.fragment.AllProductFragment;
import vn.edu.poly.testduan2.view.fragment.BreadFragment;
import vn.edu.poly.testduan2.view.fragment.FruitFragment;
import vn.edu.poly.testduan2.view.fragment.MilkTeaFragment;

public class TabProdcutAdapter extends FragmentPagerAdapter {

    private static final int[] TAB_TITLES = new int[]{R.string.key_all_product, R.string.title_milk_tea, R.string.title_fruit, R.string.title_bread};
    private final Context mContext;

    public TabProdcutAdapter(@NonNull FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position) {
            case 0:
                fragment = AllProductFragment.newInstance();
                break;
            case 1:
                fragment = MilkTeaFragment.newInstance();
                break;
            case 2:
                fragment = FruitFragment.newInstance();
                break;
            case 3:
                fragment = BreadFragment.newInstance();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
}
