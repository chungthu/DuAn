package vn.edu.poly.testduan2.controller;

import android.content.Context;
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

import java.util.List;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.model.MilkTeaFirebase;

public class MilkTeaAdapterCh extends RecyclerView.Adapter<MilkTeaAdapterCh.Viewholder> {

    Context context;
    List<MilkTeaFirebase> list;
    private int position;

    public MilkTeaAdapterCh(Context context, List<MilkTeaFirebase> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_milk_tea, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.tvMilk.setText(list.get(position).getName());
        if (list.get(position).getImage() != null && !list.get(position).getImage().equals("")){
            Picasso.get().load(list.get(position).getImage()).into(holder.imgMilk);
        }

        holder.cardMilk.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setPosition(holder.getPosition());
                return false;
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

        public CardView cardMilk;
        public ImageView imgMilk;
        public TextView tvMilk;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            cardMilk = itemView.findViewById(R.id.cardMilk);
            imgMilk = itemView.findViewById(R.id.imgMilk);
            tvMilk = itemView.findViewById(R.id.tvMilk);
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
