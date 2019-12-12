package vn.edu.poly.testduan2.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.net.response.Product;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    Context context;
    List<Product> item;

    public BillAdapter(Context context, List<Product> item) {
        this.context = context;
        this.item = item;
    }

    public void update(List<Product> list){
        this.item = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvBill.setText(item.get(position).getName());
        holder.tvAmount.setText(item.get(position).getAmount());
        holder.tvPrice.setText(item.get(position).getPrice());
        holder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(item.get(position).getAmount()) != 1){
                    int i = Integer.parseInt(item.get(position).getAmount());
                    i--;
                    item.get(position).setAmount(String.valueOf(i));
                    ConstactChange.productList.get(position).setAmount(String.valueOf(i));
                    update(item);
//                    Log.e("AAA", "onClick: "+ ConstactChange.productList.get(i).getAmount());
                }
            }
        });
        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(item.get(position).getAmount());
                i++;
                item.get(position).setAmount(String.valueOf(i));
                ConstactChange.productList.get(position).setAmount(String.valueOf(i));
                update(item);
//                Log.e("AAA", "onClick: "+ ConstactChange.productList.get(i).getAmount());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (item == null) {
            return 0;
        }
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBill;
        private TextView tvPrice;
        private TextView tvAmount;
        private ImageView imgRemove;
        private ImageView imgAdd;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBill = itemView.findViewById(R.id.tvBill);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            imgRemove = itemView.findViewById(R.id.img_remove);
            imgAdd = itemView.findViewById(R.id.img_add);
        }
    }
}
