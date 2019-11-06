package vn.edu.poly.testduan2.view.fragment;

import android.os.Bundle;
import android.view.View;

import vn.edu.poly.testduan2.R;

public class EmptyTableFragment extends BaseFragment {

    public EmptyTableFragment() {
    }

    public static EmptyTableFragment newInstance() {
        EmptyTableFragment fragment = new EmptyTableFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_empty_table;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {

    }
}
