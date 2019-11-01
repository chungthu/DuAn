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
import vn.edu.poly.testduan2.controller.FruitAdapterCh;
import vn.edu.poly.testduan2.firebase.FirebaseManager;
import vn.edu.poly.testduan2.interfaces.DataFruitStatus;
import vn.edu.poly.testduan2.model.FruitFirebase;
import vn.edu.poly.testduan2.view.activity.AddProductCHActivity;
import vn.edu.poly.testduan2.view.activity.UpdateProductActivity;

public class FruitFragment extends Fragment {

//    List<Fruit> lsFruit;
//    RecyclerView listFruit;
//    FruitAdapter fruitAdapter;
//    FruitDAO fruitDAO;

    private RecyclerView lsFruit;
    private FloatingActionButton fbAdd2;
    private FruitAdapterCh adapter;
    FirebaseManager firebaseManager = new FirebaseManager();
    private List<FruitFirebase> list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_fruit, container, false);
        firebaseManager.reaAllFruit();
//        lsFruit = new ArrayList<Fruit>();
//        listFruit = view.findViewById(R.id.lsFruit);
//        registerForContextMenu(listFruit);
//        listFruit.setHasFixedSize(true);
//
//        fruitDAO = new FruitDAO(getContext());
//
//        lsFruit = new ArrayList<Fruit>();
//        lsFruit = fruitDAO.getAllFruit();
//        fruitAdapter = new FruitAdapter(getContext(), R.layout.content_main, lsFruit);
//        RecyclerView lsFruit = view.findViewById(R.id.lsFruit);
//        lsFruit.setAdapter(fruitAdapter);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);

        lsFruit = view.findViewById(R.id.lsFruit);
        fbAdd2 = view.findViewById(R.id.fb_add2);
        lsFruit.setLayoutManager(manager);
        lsFruit.setHasFixedSize(true);

        registerForContextMenu(lsFruit);

        fbAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstactChange.STATUS_ADD = 2;
                startActivity(new Intent(getContext(), AddProductCHActivity.class));
            }
        });

        firebaseManager.setDataFruitStatus(new DataFruitStatus() {
            @Override
            public void getData(List<FruitFirebase> item) {
                list = item;
                adapter = new FruitAdapterCh(getContext(),item);
                lsFruit.setAdapter(adapter);
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
                ConstactChange.STATUS_ADD = 2;
                ConstactChange.FRUIT = list.get(position);
                startActivity(new Intent(getActivity(), UpdateProductActivity.class));
                break;
            case 1:
                firebaseManager.deleteFruit(getContext(),list.get(position).getId());
                break;
        }
        return super.onContextItemSelected(item);
    }
}
