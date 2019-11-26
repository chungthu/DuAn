package vn.edu.poly.testduan2.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.controller.FruitAdapter;
import vn.edu.poly.testduan2.interfaces.DataFruitStatus;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;
import vn.edu.poly.testduan2.net.response.FruitFirebase;

public class FruitFragment extends BaseFragment {

    @BindView(R.id.lsFruit)
    RecyclerView lsFruit;
    private FruitAdapter adapter;
    FirebaseManager firebaseManager = new FirebaseManager();
    private List<FruitFirebase> list = new ArrayList<>();

    public static FruitFragment newInstance() {
        FruitFragment fragment = new FruitFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fruit;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {
        setupData();
    }


    private void setupData() {
        firebaseManager.reaAllFruit();
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 1);
        lsFruit.setLayoutManager(manager);
        lsFruit.setHasFixedSize(true);

        registerForContextMenu(lsFruit);

        firebaseManager.setDataFruitStatus(new DataFruitStatus() {
            @Override
            public void getData(List<FruitFirebase> item) {
                list = item;
                if (adapter == null) {
                    adapter = new FruitAdapter(getContext(), item);
                    lsFruit.setAdapter(adapter);
                }else {
                    adapter.update(item);
                }
            }
        });
    }


}
