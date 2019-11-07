package vn.edu.poly.testduan2.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.controller.FruitAdapterCh;
import vn.edu.poly.testduan2.firebase.FirebaseManager;
import vn.edu.poly.testduan2.interfaces.DataFruitStatus;
import vn.edu.poly.testduan2.model.FruitFirebase;
import vn.edu.poly.testduan2.view.activity.AddProductCHActivity;
import vn.edu.poly.testduan2.view.activity.UpdateProductActivity;

public class FruitFragment extends BaseFragment {

    @BindView(R.id.lsFruit)
    RecyclerView lsFruit;
    @BindView(R.id.fb_add2)
    FloatingActionButton fbAdd2;
    private FruitAdapterCh adapter;
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
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
        lsFruit.setLayoutManager(manager);
        lsFruit.setHasFixedSize(true);

        registerForContextMenu(lsFruit);

        firebaseManager.setDataFruitStatus(new DataFruitStatus() {
            @Override
            public void getData(List<FruitFirebase> item) {
                list = item;
                if (adapter == null) {
                    adapter = new FruitAdapterCh(getContext(), item);
                    lsFruit.setAdapter(adapter);
                }else {
                    adapter.update(item);
                }
            }
        });
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = adapter.getPosition();
        } catch (Exception e) {
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case 0:
                ConstactChange.STATUS_ADD = 2;
                ConstactChange.FRUIT = list.get(position);
                startActivity(new Intent(getActivity(), UpdateProductActivity.class));
                break;
            case 1:
                firebaseManager.deleteFruit(getContext(), list.get(position).getId());
                break;
        }
        return super.onContextItemSelected(item);
    }

    @OnClick(R.id.fb_add2)
    public void onViewClicked() {
        ConstactChange.STATUS_ADD = 2;
        startActivity(new Intent(getContext(), AddProductCHActivity.class));
    }
}
