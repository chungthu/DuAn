package vn.edu.poly.testduan2.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.common.Constacts;
import vn.edu.poly.testduan2.common.ImageFirebaseUlits;
import vn.edu.poly.testduan2.firebase.FirebaseManager;
import vn.edu.poly.testduan2.model.BreadFirebase;
import vn.edu.poly.testduan2.model.Fruit;
import vn.edu.poly.testduan2.model.FruitFirebase;
import vn.edu.poly.testduan2.model.MilkTeaFirebase;

public class UpdateProductActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.spnType)
    Spinner spnType;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.edt_category)
    EditText edtCategory;
    @BindView(R.id.tvImageProduct)
    TextView tvImageProduct;
    @BindView(R.id.imgProduct)
    ImageView imgProduct;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvPrice1)
    TextView tvPrice1;
    @BindView(R.id.edtPrice1)
    EditText edtPrice1;
    @BindView(R.id.tvPrice2)
    TextView tvPrice2;
    @BindView(R.id.edtPrice2)
    EditText edtPrice2;
    @BindView(R.id.rdPrice1)
    RadioButton rdPrice1;
    @BindView(R.id.rdPrice2)
    RadioButton rdPrice2;
    @BindView(R.id.rdTopping)
    RadioGroup rdTopping;
    @BindView(R.id.btnAddProduct)
    Button btnAddProduct;
    @BindView(R.id.btnCancelAdd)
    Button btnCancelAdd;

    private Uri uriImage;
    private String image;
    private FirebaseManager firebaseManager = new FirebaseManager();

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_update_product;
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {
        setUpdata();
    }

    @OnClick({R.id.imgProduct, R.id.btnAddProduct, R.id.btnCancelAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgProduct:
                filebasechooseImage();
                break;
            case R.id.btnAddProduct:
                if (ConstactChange.STATUS_ADD == 1) {
                    updateMilkTea();
                }
                if (ConstactChange.STATUS_ADD == 2) {
                    updateFruit();
                }
                if (ConstactChange.STATUS_ADD == 3) {
                    updateBread();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Update Success")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).create();
                builder.show();
                break;
            case R.id.btnCancelAdd:
                finish();
                break;
        }
    }

    public void setUpdata() {
        if (ConstactChange.STATUS_ADD == 1) {
            tvTitle.setText("Update Milk Tea");
            assert ConstactChange.MILKTEA != null;

            edtName.setText(ConstactChange.MILKTEA.getName());
            edtCategory.setText(ConstactChange.MILKTEA.getId_catgory());
            edtPrice1.setText(ConstactChange.MILKTEA.getPriceM());
            edtPrice2.setText(ConstactChange.MILKTEA.getPriceL());
            Picasso.get().load(ConstactChange.MILKTEA.getImage()).into(imgProduct);
        }
        if (ConstactChange.STATUS_ADD == 2) {
            tvTitle.setText("Update Fruit");
            tvPrice2.setVisibility(View.GONE);
            edtPrice2.setVisibility(View.GONE);

            assert ConstactChange.FRUIT != null;
            edtName.setText(ConstactChange.FRUIT.getName());
            edtPrice1.setText(ConstactChange.FRUIT.getPrice());
            Picasso.get().load(ConstactChange.FRUIT.getImage()).into(imgProduct);
        }
        if (ConstactChange.STATUS_ADD == 3) {
            tvTitle.setText("Update Bread");
            tvPrice2.setVisibility(View.GONE);
            edtPrice2.setVisibility(View.GONE);

            assert ConstactChange.BREAD != null;

            edtName.setText(ConstactChange.BREAD.getName());
            edtPrice1.setText(ConstactChange.BREAD.getPrice());
            Picasso.get().load(ConstactChange.BREAD.getImage()).into(imgProduct);
        }
    }

    private void filebasechooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, Constacts.REQUEST_CODE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constacts.REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriImage = data.getData();
            imgProduct.setImageURI(uriImage);
            image = ImageFirebaseUlits.fileUploader(this, uriImage);
        }
    }

    private void updateMilkTea() {
        if (image == null ||image.equals("")){
            image = ConstactChange.MILKTEA.getImage();
        }
        String nameproduct = edtName.getText().toString();
        String idcategory = edtCategory.getText().toString();
        String priceM = edtPrice1.getText().toString();
        String priceL = edtPrice2.getText().toString();
        String description = "";
        MilkTeaFirebase milkTeaFirebase = new MilkTeaFirebase(ConstactChange.MILKTEA.getId(), nameproduct, idcategory, "Chung",
                image, priceM, priceL, description);
        firebaseManager.updateMilkTea(this, ConstactChange.MILKTEA.getId(), milkTeaFirebase);
    }

    private void updateFruit() {
        if (image == null ||image.equals("")){
            image = ConstactChange.FRUIT.getImage();
        }
        String nameproduct = edtName.getText().toString();
        String image = ImageFirebaseUlits.fileUploader(this, uriImage);
        String price = edtPrice1.getText().toString();
        String description = "";
        FruitFirebase fruitFirebase = new FruitFirebase(ConstactChange.FRUIT.getId(), nameproduct, image, price, description);
        firebaseManager.updateFruit(this, ConstactChange.FRUIT.getId(), fruitFirebase);
    }

    private void updateBread() {
        if (image == null ||image.equals("")){
            image = ConstactChange.BREAD.getImage();
        }
        String nameproduct = edtName.getText().toString();
        String image = ImageFirebaseUlits.fileUploader(this, uriImage);
        String price = edtPrice1.getText().toString();
        String description = "";
        BreadFirebase breadFirebase = new BreadFirebase(ConstactChange.BREAD.getId(), nameproduct, image, price, description);
        firebaseManager.updateBread(this, ConstactChange.BREAD.getId(), breadFirebase);
    }

}
