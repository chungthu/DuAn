package vn.edu.poly.testduan2.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testduan2.MilkTeaProductDetailActivity;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.UpdateActivity;
import vn.edu.poly.testduan2.adapter.MilkTeaAdapter;
import vn.edu.poly.testduan2.model.MilkTea;
import vn.edu.poly.testduan2.sqliteDAO.MilkTeaDAO;

public class MilkTeaFragment extends Fragment implements View.OnClickListener {

    CardView cardView;
    ImageView imageMilkTea;
    List<MilkTea> listMilkt;
    RecyclerView lsMilkt;
    MilkTeaAdapter milkTeaAdapter;
    MilkTeaDAO milkTeaDAO;
    int position;
    public static final int Request_image_Edit = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_milk_tea, container, false);

        listMilkt = new ArrayList<MilkTea>();
        lsMilkt = view.findViewById(R.id.lsMilk);
        imageMilkTea = view.findViewById(R.id.imgMilk);
        registerForContextMenu(lsMilkt);
        lsMilkt.setHasFixedSize(true);

        milkTeaDAO = new MilkTeaDAO(getContext());

        listMilkt = new ArrayList<MilkTea>();
        listMilkt = milkTeaDAO.getAllMilkTea();
        milkTeaAdapter = new MilkTeaAdapter(getContext(), R.layout.content_main, listMilkt);
        lsMilkt.setAdapter(milkTeaAdapter);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
        lsMilkt.setLayoutManager(manager);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Functions:");
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemUpdate:
                Intent intent = new Intent(getContext(), UpdateActivity.class);
                byte[] ImageProduct = listMilkt.get(position).getImgMilk();
                intent.putExtra("", ImageProduct);
                startActivityForResult(intent, Request_image_Edit);
                break;
            case R.id.itemDelete:
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn có muốn xóa thông tin sản phẩm?");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        MilkTea milkTea = listMilkt.get(menuInfo.position);
                        milkTeaDAO.delete(milkTea);
//                        milkTeaAdapter.remove(milkTea);
                        milkTeaAdapter.notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}
