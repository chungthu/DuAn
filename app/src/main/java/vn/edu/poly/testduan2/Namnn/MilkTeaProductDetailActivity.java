package vn.edu.poly.testduan2.Namnn;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.model.MilkTea;
import vn.edu.poly.testduan2.Namnn.sqliteDAO.MilkTeaDAO;

public class MilkTeaProductDetailActivity extends AppCompatActivity {

    MilkTeaDAO milkTeaDAO;
    TextView tvType, tvName, tvPrice1, tvPrice2, tvTopping;
    ImageView imageMilkTea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        viewId();

        milkTeaDAO = new MilkTeaDAO(this);
        byte imgae = getIntent().getExtras().getByte("ImageMilkTea");
        MilkTea milkTea = new MilkTea();
        milkTea = milkTeaDAO.Image(imgae);

        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(milkTea.getImgMilk(), 0, milkTea.getImgMilk().length);
        imageMilkTea.setImageBitmap(bmHinhDaiDien);
        tvType.setText(milkTea.getType().toString());
        tvName.setText(milkTea.getTitle().toString());
        tvPrice1.setText(String.valueOf(milkTea.getPrice1()));
        tvPrice2.setText(String.valueOf(milkTea.getPrice2()));
        tvTopping.setText(milkTea.getTopping().toString());
    }

    public void viewId(){
        tvType = findViewById(R.id.type);
        tvName = findViewById(R.id.name);
        tvPrice1 = findViewById(R.id.price1);
        tvPrice2 = findViewById(R.id.price2);
        tvTopping = findViewById(R.id.topping);
    }
}
