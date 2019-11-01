package vn.edu.poly.testduan2.controler;

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
import vn.edu.poly.testduan2.model.BreadFirebase;

public class BreadAdapterCh extends RecyclerView.Adapter<BreadAdapterCh.ViewHolder> {

    Context context;
    List<BreadFirebase> item;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public BreadAdapterCh(Context context, List<BreadFirebase> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bread,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvBread.setText(item.get(position).getName());
        Picasso.get().load(item.get(position).getImage()).into(holder.imgBread);
        holder.cvBread.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setPosition(holder.getPosition());
                return false;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private ImageView imgBread;
        private TextView tvBread;
        private CardView cvBread;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBread = itemView.findViewById(R.id.imgBread);
            tvBread = itemView.findViewById(R.id.tvBread);
            cvBread = itemView.findViewById(R.id.cv_bread);
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
