package vn.edu.poly.testduan2.view.fragment;


import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import vn.edu.poly.testduan2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllProductFragment extends BaseFragment {


    public AllProductFragment() {
        // Required empty public constructor
    }

    public static AllProductFragment newInstance() {
        AllProductFragment fragment = new AllProductFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_product;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {

    }
}
