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
import vn.edu.poly.testduan2.controller.MilkTeaAdapterCh;
import vn.edu.poly.testduan2.firebase.FirebaseManager;
import vn.edu.poly.testduan2.interfaces.DataMilkteaStatus;
import vn.edu.poly.testduan2.model.MilkTeaFirebase;
import vn.edu.poly.testduan2.view.activity.AddProductCHActivity;
import vn.edu.poly.testduan2.view.activity.UpdateProductActivity;

public class MilkTeaFragment extends BaseFragment {

    @BindView(R.id.lsMilk)
    RecyclerView lsMilk;
    @BindView(R.id.fb_add1)
    FloatingActionButton fbAdd1;
    private MilkTeaAdapterCh adapter;
    FirebaseManager firebaseManager = new FirebaseManager();
    private List<MilkTeaFirebase> list = new ArrayList<>();

    public static MilkTeaFragment newInstance() {
        MilkTeaFragment fragment = new MilkTeaFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_milk_tea;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {
        setupData();
    }

    private void setupData() {
        firebaseManager.reaAllDataTea();
        registerForContextMenu(lsMilk);
        lsMilk.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
        lsMilk.setLayoutManager(manager);

        registerForContextMenu(lsMilk);

        firebaseManager.setDataMilkteaStatus(new DataMilkteaStatus() {
            @Override
            public void getData(List<MilkTeaFirebase> item) {
                list = item;
                if (adapter == null) {
                    adapter = new MilkTeaAdapterCh(getContext(), item);
                    lsMilk.setAdapter(adapter);
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
                ConstactChange.STATUS_ADD = 1;
                ConstactChange.MILKTEA = list.get(position);
                startActivity(new Intent(getActivity(), UpdateProductActivity.class));
                break;
            case 1:
                firebaseManager.deleteMilkTea(getContext(), list.get(position).getId());
                break;
        }
        return super.onContextItemSelected(item);
    }

    @OnClick(R.id.fb_add1)
    public void onViewClicked() {
        ConstactChange.STATUS_ADD = 1;
        startActivity(new Intent(getContext(), AddProductCHActivity.class));
    }
}
