package vn.edu.poly.testduan2.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.controller.MilkTeaAdapter;
import vn.edu.poly.testduan2.interfaces.DataMilkteaStatus;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;
import vn.edu.poly.testduan2.net.response.MilkTeaFirebase;

public class MilkTeaFragment extends BaseFragment {

    @BindView(R.id.lsMilk)
    RecyclerView lsMilk;
    private MilkTeaAdapter adapter;
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
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 1);
        lsMilk.setLayoutManager(manager);

        registerForContextMenu(lsMilk);

        firebaseManager.setDataMilkteaStatus(new DataMilkteaStatus() {
            @Override
            public void getData(List<MilkTeaFirebase> item) {
                list = item;
                if (adapter == null) {
                    adapter = new MilkTeaAdapter(getContext(), item);
                    lsMilk.setAdapter(adapter);
                }else {
                    adapter.update(item);
                }
            }
        });
    }
}
