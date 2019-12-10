package vn.edu.poly.testduan2.view.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Objects;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.sv_Search)
    EditText svSearch;
    @BindView(R.id.ll_click_search)
    LinearLayout llClickSearch;
    @BindView(R.id.nav_bar)
    RelativeLayout navBar;
    @BindView(R.id.view)
    View view;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {

    }

    @OnClick({R.id.img_back, R.id.view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                getActivity().getFragmentManager().popBackStack();
                Utils.hideKeyboard(Objects.requireNonNull(getActivity()));
                break;
            case R.id.view:
                break;
        }
    }
}
