package vn.edu.poly.testduan2.adapter;

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
import vn.edu.poly.testduan2.model.Fruit;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>  {

    List<Fruit> list;
    Context context;
    int resource;

    public FruitAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> lsFruit) {
        this.context = context;
        this.resource = resource;
        this.list = lsFruit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_fruit, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = list.get(position);
        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(fruit.getImgFruit(), 0, fruit.getImgFruit().length);
        holder.imgAnh.setImageBitmap(bmHinhDaiDien);
        holder.tvTen.setText(fruit.getTitle());
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
            imgAnh = itemView.findViewById(R.id.imgFruit);
            tvTen = itemView.findViewById(R.id.tvFruit);
        }
    }
}
