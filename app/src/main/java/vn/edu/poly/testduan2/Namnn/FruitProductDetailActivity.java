package vn.edu.poly.testduan2.Namnn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.model.Fruit;
import vn.edu.poly.testduan2.Namnn.sqliteDAO.FruitDAO;

public class FruitProductDetailActivity extends AppCompatActivity {

    FruitDAO fruitDAO;
    TextView tvType, tvName, tvPrice1, tvPrice2, tvTopping;
    ImageView imageFruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        viewId();

        fruitDAO = new FruitDAO(this);
        byte imgae = getIntent().getExtras().getByte("ImageFruit");
        Fruit fruit = new Fruit();
        fruit = fruitDAO.Image(imgae);

        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(fruit.getImgFruit(), 0, fruit.getImgFruit().length);
        imageFruit.setImageBitmap(bmHinhDaiDien);
        tvType.setText(fruit.getType().toString());
        tvName.setText(fruit.getTitle().toString());
        tvPrice1.setText(String.valueOf(fruit.getPrice1()));
        tvPrice2.setText(String.valueOf(fruit.getPrice2()));
        tvTopping.setText(fruit.getTopping().toString());
    }

    public void viewId(){
        tvType = findViewById(R.id.type);
        tvName = findViewById(R.id.name);
        tvPrice1 = findViewById(R.id.price1);
        tvPrice2 = findViewById(R.id.price2);
        tvTopping = findViewById(R.id.topping);
    }
}
