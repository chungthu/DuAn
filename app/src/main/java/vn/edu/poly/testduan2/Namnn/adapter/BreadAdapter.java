package vn.edu.poly.testduan2.Namnn.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.model.Bread;

public class BreadAdapter extends RecyclerView.Adapter<BreadAdapter.ViewHolder>  {

    List<Bread> list;
    Context context;
    int resource;

    public BreadAdapter(@NonNull Context context, int resource, @NonNull List<Bread> lsBread) {
        this.context = context;
        this.resource = resource;
        this.list = lsBread;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_bread, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bread bread = list.get(position);
        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(bread.getImgBread(), 0, bread.getImgBread().length);
        holder.imgAnh.setImageBitmap(bmHinhDaiDien);
        holder.tvTen.setText(bread.getTitle());
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
            imgAnh = itemView.findViewById(R.id.imgBread);
            tvTen = itemView.findViewById(R.id.tvBread);
        }
    }
}
