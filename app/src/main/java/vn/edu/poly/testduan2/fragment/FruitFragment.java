package vn.edu.poly.testduan2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.adapter.BreadAdapter;
import vn.edu.poly.testduan2.adapter.FruitAdapter;
import vn.edu.poly.testduan2.adapter.MilkTeaAdapter;
import vn.edu.poly.testduan2.model.Bread;
import vn.edu.poly.testduan2.model.Fruit;
import vn.edu.poly.testduan2.model.MilkTea;
import vn.edu.poly.testduan2.sqliteDAO.BreadDAO;
import vn.edu.poly.testduan2.sqliteDAO.FruitDAO;

public class FruitFragment extends Fragment {

    List<Fruit> lsFruit;
    RecyclerView listFruit;
    FruitAdapter fruitAdapter;
    FruitDAO fruitDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_fruit, container, false);
        lsFruit = new ArrayList<Fruit>();
        listFruit = view.findViewById(R.id.lsFruit);
        registerForContextMenu(listFruit);
        listFruit.setHasFixedSize(true);

        fruitDAO = new FruitDAO(getContext());

        lsFruit = new ArrayList<Fruit>();
        lsFruit = fruitDAO.getAllFruit();
        fruitAdapter = new FruitAdapter(getContext(), R.layout.content_main, lsFruit);
        RecyclerView lsFruit = view.findViewById(R.id.lsFruit);
        lsFruit.setAdapter(fruitAdapter);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
        lsFruit.setLayoutManager(manager);
        return view;
    }
}
