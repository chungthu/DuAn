package vn.edu.poly.testduan2.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import vn.edu.poly.testduan2.R;

public class AllTableFragment extends BaseFragment {

    public AllTableFragment() {
        // Required empty public constructor
    }

    public static AllTableFragment newInstance() {
        AllTableFragment fragment = new AllTableFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_table;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {

    }

}
