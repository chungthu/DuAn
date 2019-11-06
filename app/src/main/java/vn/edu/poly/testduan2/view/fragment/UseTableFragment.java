package vn.edu.poly.testduan2.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.poly.testduan2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UseTableFragment extends BaseFragment {


    public UseTableFragment() {
        // Required empty public constructor
    }

    public static UseTableFragment newInstance() {
        UseTableFragment fragment = new UseTableFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_use_table;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {

    }
}
