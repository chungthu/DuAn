package vn.edu.poly.testduan2.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.evenBus.EvenTable;
import vn.edu.poly.testduan2.common.evenBus.EventBusAction;
import vn.edu.poly.testduan2.controller.TableAdapter;
import vn.edu.poly.testduan2.interfaces.DataTableStatus;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;
import vn.edu.poly.testduan2.net.response.TableResponse;
import vn.edu.poly.testduan2.view.activity.ListProductActivity;

public class EmptyTableFragment extends BaseFragment {

    @BindView(R.id.cv_buyon)
    CardView cvBuyon;
    @BindView(R.id.rv_emptyTable)
    RecyclerView rvEmptyTable;

    private TableAdapter adapter;
    private FirebaseManager firebaseManager = new FirebaseManager();
    private List<TableResponse> list = new ArrayList<>();

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
        setup();
    }

    @OnClick(R.id.cv_buyon)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), ListProductActivity.class));
        EventBus.getDefault().postSticky(new EvenTable(EventBusAction.TABLE_SELECT,null));
    }

    private void setup() {
        rvEmptyTable.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvEmptyTable.setHasFixedSize(true);

        firebaseManager.readAllTable();

        firebaseManager.setDataTableStatus(new DataTableStatus() {
            @Override
            public void getData(List<TableResponse> item) {
                for ( int i = 0 ; i < item.size() ; i ++){
                    if (item.get(i).getStatus() == 0){
                        list.add(item.get(i));
                    }
                }
                if (adapter == null) {
                    adapter = new TableAdapter(getContext(), list);
                    rvEmptyTable.setAdapter(adapter);
                } else {
                    adapter.update(list);
                }
            }
        });
    }
}
