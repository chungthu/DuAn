package vn.edu.poly.testduan2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testduan2.adapter.RecyclerViewAdapter;
import vn.edu.poly.testduan2.model.MilkTea;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<MilkTea> listMilkt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        listMilkt = new ArrayList<>();
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));
        listMilkt.add(new MilkTea(R.drawable.bread, "Trà sữa"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(this, listMilkt);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(viewAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_add_product);
            dialog.setTitle("Thêm sản phẩm");

            Button btnCancel;

            btnCancel = findViewById(R.id.btnCancel);

            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_information) {
            // Handle the camera action
        } else if (id == R.id.nav_history) {
            Intent intent = new Intent(this, ListBillActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_money) {

        } else if (id == R.id.nav_statistical) {

        } else if (id == R.id.nav_add) {

        } else if (id == R.id.nav_print) {

        } else if (id == R.id.nav_logout) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
