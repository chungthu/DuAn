package vn.edu.poly.testduan2;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testduan2.adapter.BillAdapter;
import vn.edu.poly.testduan2.model.Bill;

public class ListBillActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    List<Bill> list;
    private RecyclerView recyclerView;
    private BillAdapter billAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_bill_activity);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Danh sách hóa đơn");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(this);
        recyclerView = findViewById(R.id.lsBill);

        list = new ArrayList<>();

        for (int i = 1; i < 40; i++) {
            list.add(new Bill("Hóa đơn " + i,"","","100.000 đ"));
        }

        billAdapter = new BillAdapter(list);
        recyclerView.setAdapter(billAdapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bill, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            final Dialog dialog = new Dialog(this);
            dialog.setTitle("Thêm hóa đơn");

            Button btnCancel;

            btnCancel = findViewById(R.id.btnCancel);

            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
