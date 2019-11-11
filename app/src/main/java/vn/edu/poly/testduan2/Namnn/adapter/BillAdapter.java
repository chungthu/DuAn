package vn.edu.poly.testduan2.Namnn.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.model.Bill;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillHolder> {

    private List<Bill> lsbill;

    public BillAdapter(List<Bill> lsbill) {
        this.lsbill = lsbill;
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.item_bill, parent, false);
        return new BillHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder holder, int position) {
//        holder.textView_bill.setText(lsbill.get(position).getBill());
//        holder.textView_price.setText(lsbill.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return lsbill.size();
    }

    public static class BillHolder extends RecyclerView.ViewHolder{

        TextView textView_bill;
        TextView textView_price;

        public BillHolder(@NonNull View itemView) {
            super(itemView);

            textView_bill = (TextView) itemView.findViewById(R.id.tvBill);
            textView_price = (TextView) itemView.findViewById(R.id.tvPrice);
        }
    }
}
