package vn.edu.poly.testduan2.view.fragment;


import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.controller.AllTableAdapter;

/**
 * A simple {@link Fragment} subclass.
 */

public class SetupTableFragment extends BaseFragment {


    @BindView(R.id.tab_contact)
    TabLayout tabContact;
    @BindView(R.id.vp_table)
    ViewPager vpTable;
    private AllTableAdapter allTableAdapter;

    public SetupTableFragment() {
        // Required empty public constructor
    }

    public static SetupTableFragment newInstance() {
        SetupTableFragment fragment = new SetupTableFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setup_table;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {
        setUpTabs();
    }


    //setup taplayout and vp
    private void setUpTabs() {
        this.allTableAdapter = new AllTableAdapter(getFragmentManager(), getContext());
        this.vpTable.setOffscreenPageLimit(2);
        this.vpTable.setAdapter(allTableAdapter);
        this.tabContact.setupWithViewPager(vpTable);
    }

}
