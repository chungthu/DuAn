package vn.edu.poly.testduan2.controller;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.evenBus.EventBusAction;
import vn.edu.poly.testduan2.common.evenBus.MessageEvent;
import vn.edu.poly.testduan2.net.response.MilkTeaFirebase;
import vn.edu.poly.testduan2.view.activity.ProductDetailActivity;

public class MilkTeaAdapter extends RecyclerView.Adapter<MilkTeaAdapter.Viewholder> {

    Context context;
    List<MilkTeaFirebase> list;
    private int position;

    public MilkTeaAdapter(Context context, List<MilkTeaFirebase> list) {
        this.context = context;
        this.list = list;
    }

    public void update(List<MilkTeaFirebase> item) {
        this.list = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.tvMilk.setText(list.get(position).getName());
        holder.tvprice.setText(list.get(position).getPrice());
        if (list.get(position).getImage() != null && !list.get(position).getImage().equals("")) {
            Picasso.get().load(list.get(position).getImage()).into(holder.imgMilk);
        }

        holder.cardMilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ProductDetailActivity.class));
                EventBus.getDefault().postSticky(new MessageEvent(EventBusAction.MILKTEA_DETAIL, list.get(position), position));

            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private CardView cardMilk;
        private ImageView imgMilk;
        private TextView tvMilk;
        private TextView tvprice;

        private Viewholder(@NonNull View itemView) {
            super(itemView);
            cardMilk = itemView.findViewById(R.id.cardMilk);
            imgMilk = itemView.findViewById(R.id.imgMilk);
            tvMilk = itemView.findViewById(R.id.tvMilk);
            tvprice = itemView.findViewById(R.id.tv_price);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            menu.setHeaderTitle("Select The Action");
            menu.add(0, 0, 0, "Update");//groupId, itemId, order, title
            menu.add(0, 1, 0, "Delete");
        }
    }


}
