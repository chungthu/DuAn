package vn.edu.poly.testduan2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.controller.BreadAdapterCh;
import vn.edu.poly.testduan2.firebase.FirebaseManager;
import vn.edu.poly.testduan2.interfaces.DataBreadStatus;
import vn.edu.poly.testduan2.model.BreadFirebase;
import vn.edu.poly.testduan2.view.activity.AddProductCHActivity;
import vn.edu.poly.testduan2.view.activity.UpdateProductActivity;

public class BreadFragment extends Fragment {

//    List<Bread> lsBread;
//    RecyclerView listBread;
//    BreadAdapter breadAdapter;
//    BreadDAO breadDAO;
//    @BindView(R.id.fb_add3)
//    FloatingActionButton fbAdd3;

    private RecyclerView lsBread;
    private FloatingActionButton fbAdd3;
//    private List<BreadFirebase> list;
    private BreadAdapterCh adapter;
    private FirebaseManager firebaseManager = new FirebaseManager();
    private List<BreadFirebase> list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_bread, container, false);

        firebaseManager.readAllBread();
//        lsBread = new ArrayList<Bread>();
//        listBread = view.findViewById(R.id.lsBread);
//        registerForContextMenu(listBread);
//        listBread.setHasFixedSize(true);
//
//        breadDAO = new BreadDAO(getContext());
//
//        lsBread = new ArrayList<Bread>();
//        lsBread = breadDAO.getAllBread();
//        breadAdapter = new BreadAdapter(getContext(), R.layout.content_main, lsBread);
//        RecyclerView lsBread = view.findViewById(R.id.lsBread);
//        lsBread.setAdapter(breadAdapter);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
        lsBread = view.findViewById(R.id.lsBread);
        fbAdd3 = view.findViewById(R.id.fb_add3);
        lsBread.setLayoutManager(manager);
        lsBread.setHasFixedSize(true);

        registerForContextMenu(lsBread);


        fbAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstactChange.STATUS_ADD = 3;
                startActivity(new Intent(getActivity(), AddProductCHActivity.class));
            }
        });

        firebaseManager.setDataBreadStatus(new DataBreadStatus() {
            @Override
            public void getData(List<BreadFirebase> item) {
                list = item;
                adapter = new BreadAdapterCh(getContext(),item);
                lsBread.setAdapter(adapter);
            }
        });


        return view;
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
                ConstactChange.STATUS_ADD = 3;
                ConstactChange.BREAD = list.get(position);
                startActivity(new Intent(getActivity(), UpdateProductActivity.class));
                break;
            case 1:
                firebaseManager.deleteBread(getContext(),list.get(position).getId());
                break;
        }
        return super.onContextItemSelected(item);
    }
}
