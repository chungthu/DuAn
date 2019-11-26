package vn.edu.poly.testduan2.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.evenBus.EventBusAction;
import vn.edu.poly.testduan2.common.evenBus.MessageEvent;
import vn.edu.poly.testduan2.net.response.FruitFirebase;
import vn.edu.poly.testduan2.view.activity.ProductDetailActivity;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    Context context;
    List<FruitFirebase> item;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public FruitAdapter(Context context, List<FruitFirebase> item) {
        this.context = context;
        this.item = item;
    }

    public void update(List<FruitFirebase> item){
        this.item = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMilk.setText(item.get(position).getName());
        holder.tvPrice.setText(item.get(position).getPrice());
        if (item.get(position).getImage() != null && !item.get(position).getImage().equals("")){
            Picasso.get().load(item.get(position).getImage()).into(holder.imgMilk);
        }
        holder.cardMilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ProductDetailActivity.class));
                EventBus.getDefault().postSticky(new MessageEvent(EventBusAction.FRUIT_DETAIL, item.get(position), position));

            }
        });
    }

    @Override
    public int getItemCount() {
        if (item == null){
            return 0;
        }
        return item.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardMilk;
        private ImageView imgMilk;
        private TextView tvMilk;
        private TextView tvPrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardMilk = itemView.findViewById(R.id.cardMilk);
            imgMilk = itemView.findViewById(R.id.imgMilk);
            tvMilk = itemView.findViewById(R.id.tvMilk);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}
