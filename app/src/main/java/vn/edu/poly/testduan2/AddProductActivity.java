package vn.edu.poly.testduan2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testduan2.fragment.BreadFragment;
import vn.edu.poly.testduan2.fragment.FruitFragment;
import vn.edu.poly.testduan2.fragment.MilkTeaFragment;
import vn.edu.poly.testduan2.image.ImagePickerActivity;
import vn.edu.poly.testduan2.model.Bread;
import vn.edu.poly.testduan2.model.Fruit;
import vn.edu.poly.testduan2.model.MilkTea;
import vn.edu.poly.testduan2.sqliteDAO.BreadDAO;
import vn.edu.poly.testduan2.sqliteDAO.FruitDAO;
import vn.edu.poly.testduan2.sqliteDAO.MilkTeaDAO;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtPrice1, edtPrice2;
    private Spinner spType;
    private String type = "";
    private ImageView imgProduct;
    private RadioGroup rdTopping;
    private int topping;
    private Button btnAdd, btnCancel;
    private Toolbar toolbar;
    MilkTeaDAO milkTeaDAO;
    List<MilkTea> lsMilk;
    BreadDAO breadDAO;
    List<Bread> lsBread;
    FruitDAO fruitDAO;
    List<Fruit> lsFruit;

    private static final int REQUEST_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        viewID();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Thêm món mới");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(this);

        loadProfileDefault();

        milkTeaDAO = new MilkTeaDAO(this);
        lsMilk = new ArrayList<MilkTea>();
        breadDAO = new BreadDAO(this);
        lsBread = new ArrayList<Bread>();
        fruitDAO = new FruitDAO(this);
        lsFruit = new ArrayList<Fruit>();

        final List<String> arrayListType = new ArrayList<>();
        arrayListType.add("Trà Sữa");
        arrayListType.add("Bánh Mỳ");
        arrayListType.add("Nước Ép");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(arrayAdapter);
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                type = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImagePickerOptions();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtName.getText().toString().trim();
                String price1 = edtPrice1.getText().toString().trim();
                String price2 = edtPrice2.getText().toString().trim();
                topping = rdTopping.getCheckedRadioButtonId();
                RadioButton rdCheck = findViewById(topping);
                String Topping = rdCheck.getText().toString();

                try {
//                    if (title.isEmpty() || price1.isEmpty() || price2.isEmpty()) {
//                        if (title.isEmpty())
//                            edtName.setError(getString(R.string.notify_empty_title_product));
//                        if (price1.isEmpty())
//                            edtPrice1.setError(getString(R.string.notify_empty_price1));
//                        if (price2.isEmpty())
//                            edtPrice2.setError(getString(R.string.notify_empty_price1));
//                    }else {
                        if (type.equals("Trà Sữa")) {
                            MilkTea milkTea = new MilkTea();
                            milkTea.setTitle(edtName.getText().toString());
                            milkTea.setImgMilk(getByteArrayFromImageView(imgProduct));
                            milkTea.setPrice1(Integer.parseInt(edtPrice1.getText().toString()));
                            milkTea.setPrice2(Integer.parseInt(edtPrice2.getText().toString()));
                            milkTea.setTopping(Topping);
                            milkTea.setType(type);
                            milkTeaDAO.Insert(milkTea);
                            Toast.makeText(AddProductActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddProductActivity.this, MenuActivity.class);
                            startActivity(intent);
                        } else if (type.equals("Bánh Mỳ")) {
                            Bread bread = new Bread();
                            bread.setTitle(edtName.getText().toString());
                            bread.setImgBread(getByteArrayFromImageView(imgProduct));
                            bread.setPrice1(Integer.parseInt(edtPrice1.getText().toString()));
                            bread.setPrice2(Integer.parseInt(edtPrice2.getText().toString()));
                            bread.setTopping(Topping);
                            bread.setType(type);
                            breadDAO.Insert(bread);
                            Toast.makeText(AddProductActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddProductActivity.this, MenuActivity.class);
                            startActivity(intent);
                        } else {
                            Fruit fruit = new Fruit();
                            fruit.setTitle(edtName.getText().toString());
                            fruit.setImgFruit(getByteArrayFromImageView(imgProduct));
                            fruit.setPrice1(Integer.parseInt(edtPrice1.getText().toString()));
                            fruit.setPrice2(Integer.parseInt(edtPrice2.getText().toString()));
                            fruit.setTopping(Topping);
                            fruit.setType(type);
                            fruitDAO.Insert(fruit);
                            Toast.makeText(AddProductActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddProductActivity.this, MenuActivity.class);
                            startActivity(intent);
//                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(AddProductActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProductActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loadProfileDefault() {
        Glide.with(this).load(R.drawable.mrcay)
                .into(imgProduct);
    }

    public void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onCameraSelected() {
                launchCamera();
            }

            @Override
            public void onGallerySelected() {
                launchGallery();
            }
        });
    }

    public void launchCamera() {
        Intent intent = new Intent(AddProductActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    public void launchGallery() {
        Intent intent = new Intent(AddProductActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    public void loadImageProfile(String url) {
        Glide.with(this).load(url)
                .into(imgProduct);
        imgProduct.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
//                try {
//                    Uri imageUri = data.getData();
//                    InputStream is = getContentResolver().openInputStream(imageUri);
//                    Bitmap bitmap = BitmapFactory.decodeStream(is);
//                    imgProduct.setImageBitmap(bitmap);
//                }catch (FileNotFoundException e){
//                    e.printStackTrace();
//                }
                Uri uri = data.getParcelableExtra("path");
                loadImageProfile(uri.toString());
            }
        }
    }

    private byte[] getByteArrayFromImageView(ImageView imgv) {

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    public void viewID() {
        edtName = findViewById(R.id.edtName);
        edtPrice1 = findViewById(R.id.edtPrice1);
        edtPrice2 = findViewById(R.id.edtPrice2);
        spType = findViewById(R.id.spnType);
        rdTopping = findViewById(R.id.rdTopping);
        imgProduct = findViewById(R.id.imgProduct);
        btnAdd = findViewById(R.id.btnAddProduct);
        btnCancel = findViewById(R.id.btnCancelAdd);
    }
}
