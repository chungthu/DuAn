package vn.edu.poly.testduan2.Namnn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.model.Bread;
import vn.edu.poly.testduan2.Namnn.sqliteDAO.BreadDAO;

public class BreadProductDetailActivity extends AppCompatActivity {

    BreadDAO breadDAO;
    TextView tvType, tvName, tvPrice1, tvPrice2, tvTopping;
    ImageView imageBread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        viewId();

        breadDAO = new BreadDAO(this);
        byte imgae = getIntent().getExtras().getByte("ImageBread");
        Bread bread = new Bread();
        bread = breadDAO.Image(imgae);

        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(bread.getImgBread(), 0, bread.getImgBread().length);
        imageBread.setImageBitmap(bmHinhDaiDien);
        tvType.setText(bread.getType().toString());
        tvName.setText(bread.getTitle().toString());
        tvPrice1.setText(String.valueOf(bread.getPrice1()));
        tvPrice2.setText(String.valueOf(bread.getPrice2()));
        tvTopping.setText(bread.getTopping().toString());
    }

    public void viewId(){
        tvType = findViewById(R.id.type);
        tvName = findViewById(R.id.name);
        tvPrice1 = findViewById(R.id.price1);
        tvPrice2 = findViewById(R.id.price2);
        tvTopping = findViewById(R.id.topping);
    }
}
