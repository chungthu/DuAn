package vn.edu.poly.testduan2.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.controller.TableAdapter;
import vn.edu.poly.testduan2.interfaces.DataTableStatus;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;
import vn.edu.poly.testduan2.net.response.TableResponse;
import vn.edu.poly.testduan2.view.activity.ListProductActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UseTableFragment extends BaseFragment {


    @BindView(R.id.cv_buyon)
    CardView cvBuyon;
    @BindView(R.id.rv_useTable)
    RecyclerView rvUseTable;

    private TableAdapter adapter;
    private FirebaseManager firebaseManager = new FirebaseManager();
    private List<TableResponse> list = new ArrayList<>();

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
        setup();
    }

    @OnClick(R.id.cv_buyon)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), ListProductActivity.class));
    }

    private void setup() {
        rvUseTable.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvUseTable.setHasFixedSize(true);

        firebaseManager.readAllTable();

        firebaseManager.setDataTableStatus(new DataTableStatus() {
            @Override
            public void getData(List<TableResponse> item) {
                for ( int i = 0 ; i < item.size() ; i ++){
                    if (item.get(i).getStatus() == 1){
                        list.add(item.get(i));
                    }
                }
                if (adapter == null) {
                    adapter = new TableAdapter(getContext(), list);
                    rvUseTable.setAdapter(adapter);
                } else {
                    adapter.update(list);
                }
            }
        });
    }
}
