package vn.edu.poly.testduan2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.adapter.MilkTeaAdapter;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.controler.MilkTeaAdapterCh;
import vn.edu.poly.testduan2.firebase.FirebaseManager;
import vn.edu.poly.testduan2.interfaces.DataMilkteaStatus;
import vn.edu.poly.testduan2.model.MilkTea;
import vn.edu.poly.testduan2.model.MilkTeaFirebase;
import vn.edu.poly.testduan2.sqliteDAO.MilkTeaDAO;
import vn.edu.poly.testduan2.view.activity.AddProductCHActivity;
import vn.edu.poly.testduan2.view.activity.UpdateProductActivity;

public class MilkTeaFragment extends Fragment {

    CardView cardView;
    ImageView imageMilkTea;
    List<MilkTea> listMilkt;
    RecyclerView lsMilkt;
    MilkTeaAdapter milkTeaAdapter;
    MilkTeaDAO milkTeaDAO;
    int position;
    public static final int Request_image_Edit = 1;
    private MilkTeaAdapterCh adapter;
    private FloatingActionButton fbAdd1;
    FirebaseManager firebaseManager = new FirebaseManager();
    private List<MilkTeaFirebase> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_milk_tea, container, false);

        firebaseManager.reaAllDataTea();
        lsMilkt = view.findViewById(R.id.lsMilk);
        imageMilkTea = view.findViewById(R.id.imgMilk);
        registerForContextMenu(lsMilkt);
        lsMilkt.setHasFixedSize(true);
        fbAdd1 = view.findViewById(R.id.fb_add1);
//
//        milkTeaDAO = new MilkTeaDAO(getContext());
//
//        listMilkt = new ArrayList<MilkTea>();
//        listMilkt = milkTeaDAO.getAllMilkTea();
//        milkTeaAdapter = new MilkTeaAdapter(getContext(), R.layout.content_main, listMilkt);
//        lsMilkt.setAdapter(milkTeaAdapter);
//        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
//        lsMilkt.setLayoutManager(manager);
        lsMilkt.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
        lsMilkt.setLayoutManager(manager);

        registerForContextMenu(lsMilkt);

        firebaseManager.setDataMilkteaStatus(new DataMilkteaStatus() {
            @Override
            public void getData(List<MilkTeaFirebase> item) {
                list = item;
                adapter = new MilkTeaAdapterCh(getContext(),item);
                lsMilkt.setAdapter(adapter);
            }
        });

        fbAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstactChange.STATUS_ADD = 1;
                startActivity(new Intent(getContext(), AddProductCHActivity.class));
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
                ConstactChange.STATUS_ADD = 1;
                ConstactChange.MILKTEA = list.get(position);
                startActivity(new Intent(getActivity(), UpdateProductActivity.class));
                break;
            case 1:
                firebaseManager.deleteMilkTea(getContext(),list.get(position).getId());
                break;
        }
        return super.onContextItemSelected(item);
    }

    //    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
//            menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.setHeaderTitle("Functions:");
//        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(final MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.itemUpdate:
//                Intent intent = new Intent(getContext(), UpdateActivity.class);
//                byte[] ImageProduct = listMilkt.get(position).getImgMilk();
//                intent.putExtra("", ImageProduct);
//                startActivityForResult(intent, Request_image_Edit);
//                break;
//            case R.id.itemDelete:
//                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setMessage("Bạn có muốn xóa thông tin sản phẩm?");
//                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//                        MilkTea milkTea = listMilkt.get(menuInfo.position);
//                        milkTeaDAO.delete(milkTea);
////                        milkTeaAdapter.remove(milkTea);
//                        milkTeaAdapter.notifyDataSetChanged();
//                    }
//                });
//                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builder.show();
//        }
//        return super.onContextItemSelected(item);
//    }
//
//    @Override
//    public void onClick(View view) {
//
//    }
}
