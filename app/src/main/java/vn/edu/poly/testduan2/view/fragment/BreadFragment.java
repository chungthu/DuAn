package vn.edu.poly.testduan2.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.controller.BreadAdapter;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;
import vn.edu.poly.testduan2.interfaces.DataBreadStatus;
import vn.edu.poly.testduan2.net.response.BreadFirebase;

public class BreadFragment extends BaseFragment {


    @BindView(R.id.lsBread)
    RecyclerView lsBread;
    private BreadAdapter adapter;
    private FirebaseManager firebaseManager = new FirebaseManager();
    private List<BreadFirebase> list = new ArrayList<>();


    public static BreadFragment newInstance() {
        BreadFragment fragment = new BreadFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bread;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {
        setupData();
    }


    private void setupData() {
        firebaseManager.readAllBread();
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 1);
        lsBread.setLayoutManager(manager);
        lsBread.setHasFixedSize(true);
        registerForContextMenu(lsBread);

        firebaseManager.setDataBreadStatus(new DataBreadStatus() {
            @Override
            public void getData(List<BreadFirebase> item) {
                list = item;
                if (adapter == null) {
                    adapter = new BreadAdapter(getContext(), item);
                    lsBread.setAdapter(adapter);
                }else {
                    adapter.update(item);
                }
            }
        });

    }

}
