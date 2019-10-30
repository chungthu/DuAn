package vn.edu.poly.testduan2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.model.MilkTea;

public class MilkTeaAdapter extends RecyclerView.Adapter<MilkTeaAdapter.ViewHolder> {

    List<MilkTea> list;
    Context context;
    int resource;

    public MilkTeaAdapter(@NonNull Context context, int resource, @NonNull List<MilkTea> lsMilkt) {
        this.context = context;
        this.resource = resource;
        this.list = lsMilkt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_milk_tea, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MilkTea milkTea = list.get(position);
        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(milkTea.getImgMilk(), 0, milkTea.getImgMilk().length);
        holder.imgAnh.setImageBitmap(bmHinhDaiDien);
        holder.tvTen.setText(milkTea.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAnh;
        public TextView tvTen;


        public ViewHolder(View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.imgMilk);
            tvTen = itemView.findViewById(R.id.tvMilk);
        }
    }
}
