package vn.edu.poly.testduan2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.model.MilkTea;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<MilkTea> list;

    public RecyclerViewAdapter(Context context, List<MilkTea> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_products, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView_product.setImageResource(list.get(position).getImgMilk());
        holder.textView_product.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView_product;
        TextView textView_product;

        public MyViewHolder(View itemView){
            super(itemView);

            imageView_product = (ImageView) itemView.findViewById(R.id.imgbread);
            textView_product = (TextView) itemView.findViewById(R.id.tvbread);
        }
    }
}
