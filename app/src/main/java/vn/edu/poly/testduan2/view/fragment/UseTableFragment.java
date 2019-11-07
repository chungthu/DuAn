package vn.edu.poly.testduan2.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.view.activity.ProductActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UseTableFragment extends BaseFragment {


    @BindView(R.id.cv_buyon)
    CardView cvBuyon;
    @BindView(R.id.rv_useTable)
    RecyclerView rvUseTable;

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

    @OnClick(R.id.cv_buyon)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), ProductActivity.class));
    }
}
